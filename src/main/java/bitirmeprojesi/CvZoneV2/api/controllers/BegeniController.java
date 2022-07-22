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

import bitirmeprojesi.CvZoneV2.business.abstracts.BegeniService;
import bitirmeprojesi.CvZoneV2.entities.concretes.Begeni;
import bitirmeprojesi.CvZoneV2.requests.BegeniCreateRequest;
import bitirmeprojesi.CvZoneV2.responses.BegeniResponse;


@RestController
@RequestMapping("/api/begeni")
public class BegeniController {

	private BegeniService begeniService;

	@Autowired
	public BegeniController(BegeniService begeniService) {
		this.begeniService = begeniService;
	}
	
	@GetMapping
	public List<BegeniResponse> getAllBegeni(@RequestParam Optional<Integer> postId,@RequestParam Optional<Integer> kullaniciId){
		return this.begeniService.getAllBegeni(postId,kullaniciId);
	}
	
	@GetMapping("/{begeniId}")
	public BegeniResponse getOne(@PathVariable int begeniId){
		return  new BegeniResponse(this.begeniService.getOne(begeniId));
	}
	
	@PostMapping
	public void add(@RequestBody BegeniCreateRequest begeniCreateRequest)
	{
		this.begeniService.Add(begeniCreateRequest);
	}
	
	@DeleteMapping("/{begeniId}")
	public void Delete(@PathVariable int begeniId)
	{
		this.begeniService.Delete(begeniId);
	}
	
	
}
