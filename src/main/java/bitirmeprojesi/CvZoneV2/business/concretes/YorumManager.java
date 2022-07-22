package bitirmeprojesi.CvZoneV2.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitirmeprojesi.CvZoneV2.business.abstracts.KullaniciService;
import bitirmeprojesi.CvZoneV2.business.abstracts.PostService;
import bitirmeprojesi.CvZoneV2.business.abstracts.YorumService;
import bitirmeprojesi.CvZoneV2.dataAccess.abstracts.YorumDao;
import bitirmeprojesi.CvZoneV2.entities.concretes.Kullanici;
import bitirmeprojesi.CvZoneV2.entities.concretes.Post;
import bitirmeprojesi.CvZoneV2.entities.concretes.Yorum;
import bitirmeprojesi.CvZoneV2.requests.YorumCreateRequest;
import bitirmeprojesi.CvZoneV2.requests.YorumUpdateRequest;
import bitirmeprojesi.CvZoneV2.responses.BegeniResponse;
import bitirmeprojesi.CvZoneV2.responses.YorumResponse;

@Service
public class YorumManager implements YorumService{

	private YorumDao yorumDao;
	private KullaniciService kullaniciService;
	private PostService postService;
	
	
	@Autowired
	public YorumManager(YorumDao yorumDao,KullaniciService kullaniciService,PostService postService) {
		this.yorumDao = yorumDao;
		this.kullaniciService = kullaniciService;
		this.postService = postService;
	}

	@Override
	public Yorum getOne(int yorumId) {
		return this.yorumDao.findById(yorumId).orElse(null);
	}

	@Override
	public void Add(YorumCreateRequest yorumCreateRequest) {
		Kullanici kullanici = kullaniciService.getOne(yorumCreateRequest.getKullanici()); 
		Post post = postService.getOne(yorumCreateRequest.getPost());
				
		if(post != null && kullanici != null) {
			Yorum toSave = new Yorum();
			toSave.setYorum(yorumCreateRequest.getYorum());
			toSave.setYorumId(yorumCreateRequest.getYorumId());
			toSave.setKullanici(kullanici);
			toSave.setPost(post);
			
			this.yorumDao.save(toSave);
		}
	}

	@Override
	public void Update(int yorumId,YorumUpdateRequest yorumUpdateRequest) {
		Optional<Yorum> yorum = this.yorumDao.findById(yorumId);
		if(yorum.isPresent()) {
			Yorum toUpdate = yorum.get();
			toUpdate.setYorum(yorumUpdateRequest.getYorum());
			this.yorumDao.save(toUpdate);
		}
	}

	@Override
	public void Delete(int yorumId) {
		this.yorumDao.deleteById(yorumId);
	}

	@Override
	public List<YorumResponse> getAllYorum(Optional<Integer> postId,Optional<Integer> kullaniciId) {
		List<Yorum> list;
		if(postId.isPresent() && kullaniciId.isPresent()) 
			list =  this.yorumDao.findByPost_PostIdAndKullanici_KullaniciId(postId.get(),kullaniciId.get());
		else if(kullaniciId.isPresent())	
			list =  this.yorumDao.findByKullanici_KullaniciId(kullaniciId.get());
		else if(postId.isPresent())
			list =  this.yorumDao.findByPost_PostId(postId.get());
		else
			list =  this.yorumDao.findAll();
		
		return list.stream().map(yorum -> new YorumResponse(yorum,yorum.getKullanici())).collect(Collectors.toList());
	}

}
