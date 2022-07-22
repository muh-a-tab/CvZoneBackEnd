package bitirmeprojesi.CvZoneV2.responses;

import java.util.Date;

import bitirmeprojesi.CvZoneV2.entities.concretes.Kullanici;
import lombok.Data;

@Data
public class KullaniciResponse {

	private int kullaniciId;
	private int kullaniciTip;
	private String adSoyad;
	private String eMail;
	private String sehir;
	private String sifre;
	private String resim;
	private Date createdDate;
	
	public KullaniciResponse(Kullanici entity) {
		this.kullaniciId = entity.getKullaniciId();
		this.kullaniciTip = entity.getKullaniciTip();
		this.adSoyad = entity.getAdSoyad();
		this.eMail = entity.getEMail();
		this.sehir = entity.getSehir();
		this.sifre = entity.getSifre();
		this.resim = entity.getResim();
		this.createdDate = entity.getCreatedDate();
	}
	
	
}
