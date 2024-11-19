package cord.eoeo.momentwo.elasticsearch.application.port.out.tag.photo;

public interface PhotoTagCountByAlbumIdRepo extends PhotoTagElasticsearchErRepo {
    long countByAlbumId(long albumId);
}
