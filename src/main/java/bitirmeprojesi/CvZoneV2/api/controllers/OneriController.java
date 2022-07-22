package bitirmeprojesi.CvZoneV2.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import bitirmeprojesi.CvZoneV2.business.abstracts.OneriService;
import bitirmeprojesi.CvZoneV2.entities.concretes.Kullanici;
import bitirmeprojesi.CvZoneV2.responses.BegeniResponse;
import bitirmeprojesi.CvZoneV2.responses.KullaniciResponse;

@RestController
@RequestMapping("/api/oneri")
public class OneriController {
	
	private OneriService oneriService;
	
	@Autowired
	public OneriController(OneriService oneriService) {
		this.oneriService = oneriService;
	}

	
	@GetMapping
	public List<Kullanici> getOneri(@RequestParam int kullaniciId){
		return this.oneriService.oner(kullaniciId);
	}
}
