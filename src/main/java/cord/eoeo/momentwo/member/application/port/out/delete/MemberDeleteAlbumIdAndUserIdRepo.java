package cord.eoeo.momentwo.member.application.port.out.delete;

public interface MemberDeleteAlbumIdAndUserIdRepo {
    int deleteByAlbumIdAndUserId(long albumId, long userId);
}
