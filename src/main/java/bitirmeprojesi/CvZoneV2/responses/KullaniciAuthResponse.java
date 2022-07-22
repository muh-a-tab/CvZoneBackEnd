package bitirmeprojesi.CvZoneV2.responses;

import java.util.Date;

import bitirmeprojesi.CvZoneV2.entities.concretes.Kullanici;
import lombok.Data;

@Data
public class KullaniciAuthResponse {

	private int kullaniciId;
	
	
	public KullaniciAuthResponse(Kullanici entity) {
		this.kullaniciId = entity.getKullaniciId();
		
	}
	
	public KullaniciAuthResponse(int kullaniciId) {
		this.kullaniciId = kullaniciId;
		
	}
	
}
