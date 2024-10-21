package cord.eoeo.momentwo.user.application.service;

import cord.eoeo.momentwo.album.application.port.out.AlbumManager;
import cord.eoeo.momentwo.config.security.jwt.TokenProvider;
import cord.eoeo.momentwo.config.security.jwt.adapter.out.TokenResponseDto;
import cord.eoeo.momentwo.elasticsearch.application.port.out.LikesElasticSearchManager;
import cord.eoeo.momentwo.elasticsearch.application.port.out.UserElasticSearchManager;
import cord.eoeo.momentwo.member.advice.exception.AdminAlbumOutException;
import cord.eoeo.momentwo.member.application.port.out.GetAlbumInfo;
import cord.eoeo.momentwo.member.domain.Member;
import cord.eoeo.momentwo.user.adapter.dto.in.SignOutRequestDto;
import cord.eoeo.momentwo.user.adapter.dto.in.UserLoginRequestDto;
import cord.eoeo.momentwo.user.advice.exception.NotFoundUserException;
import cord.eoeo.momentwo.user.advice.exception.PasswordMisMatchException;
import cord.eoeo.momentwo.user.application.port.in.UserStatusUseCase;
import cord.eoeo.momentwo.user.application.port.out.*;
import cord.eoeo.momentwo.config.security.jwt.port.out.JWTBlackList;
import cord.eoeo.momentwo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
@RequiredArgsConstructor
public class UserStatusService implements UserStatusUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final JWTBlackList jwtBlackList;
    private final GetAuthentication getAuthentication;
    private final GetAlbumInfo getAlbumInfo;
    private final AlbumManager albumManager;
    private final UserElasticSearchManager userElasticSearchManager;
    private final LikesElasticSearchManager likesElasticSearchManager;

    @Transactional(readOnly = true)
    @Override
    @Async
    public CompletableFuture<TokenResponseDto> signIn(UserLoginRequestDto userLoginRequestDto) {
        return CompletableFuture.supplyAsync(() -> {
            // 아이디 확인
            User user = userRepository.findByUsername(userLoginRequestDto.getUsername()).orElseThrow(
                    () -> new CompletionException(new NotFoundUserException()));
            // 비밀번호 확인
            if(!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())) {
                throw new CompletionException(new PasswordMisMatchException());
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userLoginRequestDto.getUsername(),
                    userLoginRequestDto.getPassword()
            );
            Authentication authentication = authenticationManager.getAuthentication(authenticationToken);

            return tokenProvider.createToken(authentication, "");
        });
    }

    @Override
    public void blackListToken(String token) {
        jwtBlackList.blackListToken(token);
    }

    // 회원 탈퇴
    @Override
    @Transactional
    public void signOut(SignOutRequestDto signOutRequestDto) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(NotFoundUserException::new);
        // 비밀번호 확인
        if(!passwordEncoder.matches(signOutRequestDto.getPassword(), user.getPassword())) {
            throw new PasswordMisMatchException();
        }

        // 회원 탈퇴 시 앨범 관리자이면서 멤버를 보유한 경우 회원탈퇴 불가
        // 관리자 권한을 넘기면 탈퇴 가능
        getAlbumInfo.getAlbumIdByAdminUser(user).forEach(albumId -> {
            Member member = getAlbumInfo.getAlbumMemberInfo(albumId, user.getId());
            // 멤버가 존재하는 앨범에 관리자로 있는 경우 삭제 불가 예외
            if(getAlbumInfo.isCheckAlbumAdmin(member) && !getAlbumInfo.isCheckAlbumOneMember(albumId)) {
                throw new AdminAlbumOutException();
            }
            // 관리자 일 때, 회원탈퇴를 할 경우 앨범 테이블 정보 삭제
            if(getAlbumInfo.isCheckAlbumAdmin(member) && getAlbumInfo.isCheckAlbumOneMember(albumId)) {
                albumManager.albumDelete(member);
            }
        });

        userRepository.delete(user);
        userElasticSearchManager.deleteById(user.getId());
        likesElasticSearchManager.deleteByWildNickname(user.getNickname());
    }
}
