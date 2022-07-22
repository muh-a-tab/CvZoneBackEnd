package bitirmeprojesi.CvZoneV2.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bitirmeprojesi.CvZoneV2.business.abstracts.TakipcilerService;
import bitirmeprojesi.CvZoneV2.entities.concretes.Takipciler;
import bitirmeprojesi.CvZoneV2.requests.TakipcilerCreateRequest;
import bitirmeprojesi.CvZoneV2.responses.TakipcilerResponse;


@RestController
@RequestMapping("/api/takipciler")
public class TakipcilerController {

	private TakipcilerService takipcilerService;
	
	@Autowired
	public TakipcilerController(TakipcilerService takipcilerService) {
		this.takipcilerService = takipcilerService;
	}

	@GetMapping
	public List<TakipcilerResponse> getAllTakipciler(@RequestParam Optional<Integer> takipEdenId,@RequestParam Optional<Integer> takipEdilenId){
		return this.takipcilerService.getAllTakipciler(takipEdenId,takipEdilenId);
	}	
	
	@PostMapping
	public void add(@RequestBody TakipcilerCreateRequest takipcilerCreateRequest)
	{
		this.takipcilerService.Add(takipcilerCreateRequest);
	}
	
	@DeleteMapping("/{takipId}")
	public void Delete(@PathVariable int takipId)
	{
		this.takipcilerService.Delete(takipId);
	}
	
	
	
	
}
