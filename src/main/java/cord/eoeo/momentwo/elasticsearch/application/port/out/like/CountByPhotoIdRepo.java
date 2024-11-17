package cord.eoeo.momentwo.elasticsearch.application.port.out.like;

public interface CountByPhotoIdRepo extends LikesElasticsearchErRepo {
    long countByPhotoId(long photoId);
}
