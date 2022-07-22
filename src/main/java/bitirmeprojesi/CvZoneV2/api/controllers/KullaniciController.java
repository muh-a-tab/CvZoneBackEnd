package bitirmeprojesi.CvZoneV2.api.controllers;

import java.util.List;


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

import bitirmeprojesi.CvZoneV2.business.abstracts.KullaniciService;
import bitirmeprojesi.CvZoneV2.entities.concretes.Kullanici;
import bitirmeprojesi.CvZoneV2.requests.KullaniciResimUpdateRequest;
import bitirmeprojesi.CvZoneV2.responses.KullaniciAuthResponse;
import bitirmeprojesi.CvZoneV2.responses.KullaniciResponse;


@RestController
@RequestMapping("/api/kullanici")
public class KullaniciController {
	
	private KullaniciService kullaniciService;
	
	@Autowired
	public KullaniciController(KullaniciService kullaniciService) {
		this.kullaniciService=kullaniciService;
	}
	
	@GetMapping("/getall")
	public List<Kullanici> getAll(){
		return this.kullaniciService.getAll();
	}
	
	@GetMapping
	public KullaniciAuthResponse getAuth(@RequestParam String eMail ,@RequestParam String sifre){
		return this.kullaniciService.getAuth(eMail,sifre) ;
	}
	
	
	@GetMapping("/{kullaniciId}")
	public Kullanici getOne(@PathVariable int kullaniciId){
		return this.kullaniciService.getOne(kullaniciId);
	}
	
	@PostMapping
	public void add(@RequestBody Kullanici kullanici)
	{
		this.kullaniciService.Add(kullanici);
	}
	
	@DeleteMapping("/{kullaniciId}")
	public void Delete(@PathVariable int kullaniciId)
	{
		this.kullaniciService.Delete(kullaniciId);
	}
	
	@PutMapping("/updateResim")
	public void Update(@RequestBody KullaniciResimUpdateRequest kullanici)
	{
		this.kullaniciService.UpdateResim(kullanici);
	}
	
	@PutMapping("/{kullaniciId}")
	public void Update(@PathVariable int kullaniciId, @RequestBody Kullanici kullanici)
	{
		this.kullaniciService.Update(kullaniciId,kullanici);
	}
	
	@GetMapping("/takipEdenler")
	public List<KullaniciResponse> getTakipEdenler(@RequestParam int kullaniciId){
		return this.kullaniciService.getTakipEdenler(kullaniciId);
	}
	@GetMapping("/takipEdilenler")
	public List<KullaniciResponse> getTakipEdilenler(@RequestParam int kullaniciId){
		return this.kullaniciService.getTakipEdilenler(kullaniciId);
	}
	
	@GetMapping("/Firmalar")
	public List<KullaniciResponse> getFirmalar(){
		return this.kullaniciService.getFirmalar();
	}
}
