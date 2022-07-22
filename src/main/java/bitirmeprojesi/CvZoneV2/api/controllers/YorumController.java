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

import bitirmeprojesi.CvZoneV2.business.abstracts.YorumService;
import bitirmeprojesi.CvZoneV2.entities.concretes.Yorum;
import bitirmeprojesi.CvZoneV2.requests.YorumCreateRequest;
import bitirmeprojesi.CvZoneV2.requests.YorumUpdateRequest;
import bitirmeprojesi.CvZoneV2.responses.YorumResponse;


@RestController
@RequestMapping("/api/yorum")
public class YorumController {
	
	private YorumService yorumService;
	
	@Autowired
	public YorumController(YorumService yorumService) {
		this.yorumService = yorumService;
	}

	@GetMapping
	public List<YorumResponse> getAllYorum(@RequestParam Optional<Integer> postId,@RequestParam Optional<Integer> kullaniciId){
		return this.yorumService.getAllYorum(postId,kullaniciId);
	}
	
	@GetMapping("/{yorumId}")
	public YorumResponse getOne(@PathVariable int yorumId){
		return new YorumResponse(this.yorumService.getOne(yorumId),this.yorumService.getOne(yorumId).getKullanici());
	}
	
	@PostMapping
	public void add(@RequestBody YorumCreateRequest yorumCreateRequest)
	{
		this.yorumService.Add(yorumCreateRequest);
	}
	
	@DeleteMapping("/{yorumId}")
	public void Delete(@PathVariable int yorumId)
	{
		this.yorumService.Delete(yorumId);
	}
	
	@PutMapping("/{yorumId}")
	public void Update(@PathVariable int yorumId,@RequestBody YorumUpdateRequest yorumUpdateRequest)
	{
		this.yorumService.Update(yorumId,yorumUpdateRequest);
	}
	

	

}
