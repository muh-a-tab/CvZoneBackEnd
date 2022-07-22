package bitirmeprojesi.CvZoneV2.responses;


import bitirmeprojesi.CvZoneV2.entities.concretes.Medya;
import lombok.Data;
@Data
public class MedyaResponse {

	private int medyaId;
	private int kullaniciId;
	private String belge;
	private String belgeAdi;

	public MedyaResponse(Medya entity) {
	
		this.medyaId = entity.getMedyaId();
		this.kullaniciId = entity.getKullanici().getKullaniciId();
		this.belge = entity.getBelge();
		this.belgeAdi = entity.getBelgeAdi();
	}

}
