package bitirmeprojesi.CvZoneV2.business.concretes;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import bitirmeprojesi.CvZoneV2.business.abstracts.BegeniService;
import bitirmeprojesi.CvZoneV2.business.abstracts.KullaniciService;
import bitirmeprojesi.CvZoneV2.business.abstracts.PostService;
import bitirmeprojesi.CvZoneV2.dataAccess.abstracts.BegeniDao;
import bitirmeprojesi.CvZoneV2.entities.concretes.Begeni;
import bitirmeprojesi.CvZoneV2.entities.concretes.Kullanici;
import bitirmeprojesi.CvZoneV2.entities.concretes.Post;
import bitirmeprojesi.CvZoneV2.requests.BegeniCreateRequest;
import bitirmeprojesi.CvZoneV2.responses.BegeniResponse;

@Service
public class BegeniManager implements BegeniService{

	BegeniDao begeniDao;
	KullaniciService kullaniciService;
	PostService postService;
	
	@Autowired
	public BegeniManager(BegeniDao begeniDao,KullaniciService kullaniciService,PostService postService) {
		this.begeniDao = begeniDao;
		this.kullaniciService = kullaniciService;
		this.postService = postService;
	}
	
	@Override
	public List<BegeniResponse> getAllBegeni(Optional<Integer> postId,Optional<Integer> kullaniciId) {
		
		List<Begeni> list; 
		if(postId.isPresent() && kullaniciId.isPresent())
			list = this.begeniDao.findByPost_PostIdAndKullanici_KullaniciId(postId.get(),kullaniciId.get());
		else if(kullaniciId.isPresent())	
			list = this.begeniDao.findByKullanici_KullaniciId(kullaniciId.get());
		else if(postId.isPresent())   
			list =  this.begeniDao.findByPost_PostId(postId.get());
		else	
			list = this.begeniDao.findAll();
		
		return list.stream().map(begeni -> new BegeniResponse(begeni)).collect(Collectors.toList());
	}
	

	@Override
	public Begeni getOne(int begeniId) {
		return this.begeniDao.findById(begeniId).orElse(null);
	}

	@Override
	public void Add(BegeniCreateRequest begeniCreateRequest) {
		Kullanici kullanici = kullaniciService.getOne(begeniCreateRequest.getKullaniciId());
		Post post = postService.getOne(begeniCreateRequest.getPostId());
		if(kullanici != null && post != null) {
			Begeni toSave = new Begeni();
			toSave.setBegeniId(begeniCreateRequest.getBegeniId());
			toSave.setKullanici(kullanici);
			toSave.setPost(post);
			this.begeniDao.save(toSave);	
		}
	}


	@Override
	public void Delete(int begeniId) {
		this.begeniDao.deleteById(begeniId);
	}

	

}
