package bitirmeprojesi.CvZoneV2.business.concretes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import bitirmeprojesi.CvZoneV2.business.abstracts.BegeniService;
import bitirmeprojesi.CvZoneV2.business.abstracts.KullaniciService;
import bitirmeprojesi.CvZoneV2.business.abstracts.PostService;
import bitirmeprojesi.CvZoneV2.business.abstracts.TakipcilerService;
import bitirmeprojesi.CvZoneV2.business.abstracts.YorumService;
import bitirmeprojesi.CvZoneV2.dataAccess.abstracts.PostDao;
import bitirmeprojesi.CvZoneV2.entities.concretes.Begeni;
import bitirmeprojesi.CvZoneV2.entities.concretes.Kullanici;
import bitirmeprojesi.CvZoneV2.entities.concretes.Post;
import bitirmeprojesi.CvZoneV2.requests.PostCreateRequest;
import bitirmeprojesi.CvZoneV2.requests.PostUpdateRequest;
import bitirmeprojesi.CvZoneV2.responses.BegeniResponse;
import bitirmeprojesi.CvZoneV2.responses.PostResponse;
import bitirmeprojesi.CvZoneV2.responses.TakipcilerResponse;

@Service
public class PostManager implements PostService {

	private PostDao postDao;
	private BegeniService begeniService;
	private YorumService yorumService;
	private KullaniciService kullaniciService;
	private TakipcilerService takipcilerService;

	@Autowired
	PostManager(PostDao postDao, KullaniciService kullaniciService, @Lazy BegeniService begeniService,@Lazy YorumService yorumService,@Lazy TakipcilerService takipcilerService) {
		this.postDao = postDao;
		this.kullaniciService = kullaniciService;
		this.begeniService = begeniService;
		this.yorumService = yorumService;
		this.takipcilerService = takipcilerService;
		
	}

	@Override
	public List<PostResponse> getAllPosts(Optional<Integer> kullaniciId) {
		List<Post> list;
		
		if(kullaniciId.isPresent())
			list = this.postDao.findByKullanici_KullaniciId(kullaniciId.get());
		else 
			list = this.postDao.findAll();
		
		return list.stream().map(p -> new PostResponse(p,begeniService,yorumService,p.getKullanici())).collect(Collectors.toList());
	
	}

	@Override
	public Post getOne(int postId) {
		Post post = this.postDao.findById(postId).orElse(null);
		post.setBegeniler(begeniService.getAllBegeni(Optional.of(postId) , Optional.ofNullable(null)).size());
		post.setYorumlar(yorumService.getAllYorum(Optional.of(postId) , Optional.ofNullable(null)).size());
		return post;
	}

	@Override
	public void Add(PostCreateRequest newPostRequest) {
		Kullanici kullanici = kullaniciService.getOne(newPostRequest.getKullaniciId());
		if (kullanici != null) {
			Post toSave = new Post();
			toSave.setPostId(newPostRequest.getPostId());
			toSave.setBaslik(newPostRequest.getBaslik());
			toSave.setBegeniler(newPostRequest.getBegeniler());
			toSave.setCalismaTuru(newPostRequest.getCalismaTuru());
			toSave.setEtiketler(newPostRequest.getEtiketler());
			toSave.setGoruntulenme(newPostRequest.getGoruntulenme());
			toSave.setIcerik(newPostRequest.getIcerik());
			toSave.setKonum(newPostRequest.getKonum());
			toSave.setKullanici(kullanici);
			toSave.setYorumlar(newPostRequest.getYorumlar());
			postDao.save(toSave);
		}
	}

	@Override
	public void Update(int postId, PostUpdateRequest postUpdateRequest) {
		Optional<Post> post = postDao.findById(postId);
		if (post.isPresent()) {
			Post toUpdate = post.get();

			toUpdate.setBaslik(postUpdateRequest.getBaslik());
			toUpdate.setBegeniler(postUpdateRequest.getBegeniler());
			toUpdate.setCalismaTuru(postUpdateRequest.getCalismaTuru());
			toUpdate.setEtiketler(postUpdateRequest.getEtiketler());
			toUpdate.setGoruntulenme(postUpdateRequest.getGoruntulenme());
			toUpdate.setIcerik(postUpdateRequest.getIcerik());
			toUpdate.setKonum(postUpdateRequest.getKonum());
			toUpdate.setYorumlar(postUpdateRequest.getYorumlar());
			this.postDao.save(toUpdate);
		}

	}

	@Override
	public void Delete(int postId) {
		this.postDao.deleteById(postId);
	}

	@Override
	public List<PostResponse> getAllPostArkadaslar(int kullaniciId) {
		List<TakipcilerResponse> arkadaslar = takipcilerService.getAllTakipciler(Optional.of(kullaniciId) ,Optional.ofNullable(null));
		List<Integer> onerilenId = new ArrayList<>();
		for(int i = 0;i<arkadaslar.size();i++) {
			onerilenId.add(kullaniciService.getOne(arkadaslar.get(i).getTakipEdilen()).getKullaniciId());
		}
		
		List<PostResponse> posts = new ArrayList<>();
		
		if(!posts.containsAll(this.getAllPosts(Optional.of(kullaniciId))))
			posts.addAll(this.getAllPosts(Optional.of(kullaniciId)));
		for(int i = 0;i<onerilenId.size();i++) {
			
			if(!posts.containsAll(this.getAllPosts(Optional.of(onerilenId.get(i)))))
				posts.addAll(this.getAllPosts(Optional.of(onerilenId.get(i))));
			
		}
		
		Collections.sort(posts, new Comparator<PostResponse>() {
			@Override
			public int compare(PostResponse o1,PostResponse o2) {
				if(o1.getCreatedDate().compareTo(o2.getCreatedDate()) < 0)
					return 1;
				else if(o1.getCreatedDate().compareTo(o2.getCreatedDate()) > 0)
					return -1;
				else
					return 0;
			}
		});
		
		
		return posts;
	}

}
