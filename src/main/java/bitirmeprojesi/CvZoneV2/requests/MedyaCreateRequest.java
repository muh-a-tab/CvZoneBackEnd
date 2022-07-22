package bitirmeprojesi.CvZoneV2.requests;

import lombok.Data;

@Data
public class MedyaCreateRequest {

	private int medyaId;
	private int kullaniciId;
	private String belge;
	private String belgeAdi;
	
}
