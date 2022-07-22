package bitirmeprojesi.CvZoneV2.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bitirmeprojesi.CvZoneV2.business.abstracts.ProfilService;
import bitirmeprojesi.CvZoneV2.entities.concretes.Profil;
import bitirmeprojesi.CvZoneV2.requests.ProfilCreateRequest;
import bitirmeprojesi.CvZoneV2.requests.ProfilUpdateRequest;
import bitirmeprojesi.CvZoneV2.responses.ProfilResponse;


@RestController
@RequestMapping("/api/profil")
public class ProfilController {

	private ProfilService profilService;

	@Autowired
	public ProfilController(ProfilService profilService) {
		this.profilService = profilService;
	}
	
	@GetMapping("/{kullaniciId}")
	public ProfilResponse getOne(@PathVariable int kullaniciId){
		return new ProfilResponse(this.profilService.getOne(kullaniciId));
	}
	
	@PostMapping
	public void add(@RequestBody ProfilCreateRequest profilCreateRequest)
	{
		this.profilService.Add(profilCreateRequest);
	}
	
	@DeleteMapping("/{profilId}")
	public void Delete(@PathVariable int profilId)
	{
		this.profilService.Delete(profilId);
	}
	
	@PutMapping("/{profilId}")
	public void Update(@PathVariable int profilId, @RequestBody ProfilUpdateRequest profilUpdateRequest)
	{
		this.profilService.Update(profilId,profilUpdateRequest);
	}
	
}
