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

import bitirmeprojesi.CvZoneV2.business.abstracts.MedyaService;
import bitirmeprojesi.CvZoneV2.entities.concretes.Medya;
import bitirmeprojesi.CvZoneV2.requests.MedyaCreateRequest;
import bitirmeprojesi.CvZoneV2.responses.MedyaResponse;

@RestController
@RequestMapping("/api/medya")
public class MedyaController {
	
	private MedyaService medyaService;
	
	@Autowired
	public MedyaController(MedyaService medyaService) {
		this.medyaService = medyaService;
	}

	@GetMapping
	public List<MedyaResponse> getAllMedya(@RequestParam("kullaniciId") Optional<Integer> kullaniciId){
		return this.medyaService.getAllMedya(kullaniciId);
	}
	
	@GetMapping("/{medyaId}")
	public MedyaResponse getOne(@PathVariable int medyaId) {
		return new MedyaResponse(this.medyaService.getOne(medyaId));
	}
	
	@PostMapping
	public void Add(@RequestBody MedyaCreateRequest newMedyaRequest) {
		this.medyaService.Add(newMedyaRequest);
	}
	
	@DeleteMapping("/{medyaId}")
	public void Delete(@PathVariable int medyaId) {
		this.medyaService.Delete(medyaId);
	}
}
