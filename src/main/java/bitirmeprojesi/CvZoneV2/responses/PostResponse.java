package bitirmeprojesi.CvZoneV2.responses;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import bitirmeprojesi.CvZoneV2.business.abstracts.BegeniService;
import bitirmeprojesi.CvZoneV2.business.abstracts.YorumService;
import bitirmeprojesi.CvZoneV2.entities.concretes.Begeni;
import bitirmeprojesi.CvZoneV2.entities.concretes.Kullanici;
import bitirmeprojesi.CvZoneV2.entities.concretes.Post;
import lombok.Data;

@Data
public class PostResponse {
	
	
	private int postId;
	private String baslik;
	private String konum;
	private String calismaTuru;
	private String icerik;
	private List<String> etiketler;
	private int begeniler;
	private int yorumlar;
	private int goruntulenme;
	private int kullaniciId; 
	private Date CreatedDate;
	private String resim;
	private String kullaniciAdi;
	
	public PostResponse(Post entity ,BegeniService begeniService, YorumService yorumService,Kullanici kullanici) {
		this.postId = entity.getPostId();
		this.kullaniciId = entity.getKullanici().getKullaniciId();
		this.baslik = entity.getBaslik();
		this.konum = entity.getKonum();
		this.calismaTuru = entity.getCalismaTuru();
		this.icerik = entity.getIcerik();
		this.etiketler = entity.getEtiketler();
		this.begeniler = begeniService.getAllBegeni(Optional.of(postId) , Optional.ofNullable(null)).size();
		this.yorumlar = yorumService.getAllYorum(Optional.of(postId) , Optional.ofNullable(null)).size();
		this.goruntulenme = entity.getGoruntulenme();
		this.CreatedDate = entity.getCreatedDate();
		this.resim = kullanici.getResim();
		this.kullaniciAdi = kullanici.getAdSoyad();
	}
	
	public PostResponse(Post entity,Kullanici kullanici ) {
		this.postId = entity.getPostId();
		this.kullaniciId = entity.getKullanici().getKullaniciId();
		this.baslik = entity.getBaslik();
		this.konum = entity.getKonum();
		this.calismaTuru = entity.getCalismaTuru();
		this.icerik = entity.getIcerik();
		this.etiketler = entity.getEtiketler();
		this.begeniler = entity.getBegeniler();
		this.yorumlar = entity.getYorumlar();
		this.goruntulenme = entity.getGoruntulenme();
		this.CreatedDate = entity.getCreatedDate();
		this.resim = kullanici.getResim();
		this.kullaniciAdi = kullanici.getAdSoyad();
	
	}

	
	
}
