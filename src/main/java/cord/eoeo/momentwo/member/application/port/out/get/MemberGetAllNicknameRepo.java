package cord.eoeo.momentwo.member.application.port.out.get;

import java.util.List;

public interface MemberGetAllNicknameRepo {
    List<String> getAllNicknameList(long albumId);
}
