package bitirmeprojesi.CvZoneV2.responses;

import bitirmeprojesi.CvZoneV2.entities.concretes.Begeni;
import lombok.Data;

@Data
public class BegeniResponse {

	private int begeniId;
	private int kullaniciId;
	private int postId;
	
	public BegeniResponse(Begeni entity) {
		this.begeniId = entity.getBegeniId();
		this.kullaniciId = entity.getKullanici().getKullaniciId();
		this.postId = entity.getPost().getPostId();
		
	}
	
}
