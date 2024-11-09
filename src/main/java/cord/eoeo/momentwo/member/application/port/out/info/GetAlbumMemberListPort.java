package cord.eoeo.momentwo.member.application.port.out.info;

import java.util.List;

public interface GetAlbumMemberListPort {
    List<String> getAlbumMemberList(long albumId);
}
