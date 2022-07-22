package bitirmeprojesi.CvZoneV2.responses;

import java.util.Date;

import bitirmeprojesi.CvZoneV2.entities.concretes.Kullanici;
import bitirmeprojesi.CvZoneV2.entities.concretes.Yorum;
import lombok.Data;

@Data
public class YorumResponse {

	private int yorumId;
	private int kullaniciId;
	private int postId;
	private String yorum;
	private Date CreatedDate;
	private String resim;
	private String kullaniciAdi;
	public YorumResponse(Yorum entity,Kullanici kullanici) {
		
		this.yorumId = entity.getYorumId();
		this.kullaniciId = entity.getKullanici().getKullaniciId();
		this.postId = entity.getPost().getPostId();
		this.yorum = entity.getYorum();
		this.CreatedDate = entity.getCreatedDate();
		this.resim = kullanici.getResim();
		this.kullaniciAdi = kullanici.getAdSoyad();
		
	}
}
