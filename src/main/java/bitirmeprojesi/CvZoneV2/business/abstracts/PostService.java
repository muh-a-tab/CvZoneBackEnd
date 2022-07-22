package bitirmeprojesi.CvZoneV2.business.abstracts;

import java.util.List;
import java.util.Optional;


import bitirmeprojesi.CvZoneV2.entities.concretes.Post;
import bitirmeprojesi.CvZoneV2.requests.PostCreateRequest;
import bitirmeprojesi.CvZoneV2.requests.PostUpdateRequest;
import bitirmeprojesi.CvZoneV2.responses.PostResponse;

public interface PostService {
	List<PostResponse> getAllPosts(Optional<Integer> kullaniciId);
	Post getOne(int postId);
	void Add(PostCreateRequest newPostRequest);
	void Update(int postId, PostUpdateRequest postUpdateRequest);
	void Delete(int postId);
	List<PostResponse> getAllPostArkadaslar(int kullaniciId);
	
}
