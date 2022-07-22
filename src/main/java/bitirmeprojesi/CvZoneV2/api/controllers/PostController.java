package bitirmeprojesi.CvZoneV2.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bitirmeprojesi.CvZoneV2.business.abstracts.PostService;
import bitirmeprojesi.CvZoneV2.entities.concretes.Post;
import bitirmeprojesi.CvZoneV2.requests.PostCreateRequest;
import bitirmeprojesi.CvZoneV2.requests.PostUpdateRequest;
import bitirmeprojesi.CvZoneV2.responses.PostResponse;

@RestController
@RequestMapping("/api/post")
public class PostController {
	
	private PostService postService;
	
	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping
	public List<PostResponse> getAllPosts(@RequestParam("kullaniciId") Optional<Integer> kullaniciId){
		return this.postService.getAllPosts(kullaniciId);
	}
	
	@GetMapping("/{postId}")
	public PostResponse getOne(@PathVariable int postId){
		return new PostResponse(this.postService.getOne(postId),this.postService.getOne(postId).getKullanici());
	}
	
	@GetMapping("/all/{kullaniciId}")
	public List<PostResponse> getAllPostArkadaslar(@PathVariable int kullaniciId){
		return this.postService.getAllPostArkadaslar(kullaniciId);
	}
	
	@PostMapping
	public void add(@RequestBody PostCreateRequest newPostRequest)
	{
		this.postService.Add(newPostRequest);
	}
	
	@DeleteMapping("/{postId}")
	public void Delete(@PathVariable int postId)
	{
		this.postService.Delete(postId);
	}
	
	
	@PutMapping("/{postId}")
	public void Update(@PathVariable int postId, @RequestBody PostUpdateRequest postUpdateRequest)
	{
		this.postService.Update(postId, postUpdateRequest);
	}
	
}
