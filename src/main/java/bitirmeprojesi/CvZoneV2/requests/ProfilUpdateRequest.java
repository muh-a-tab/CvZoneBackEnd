package bitirmeprojesi.CvZoneV2.requests;

import lombok.Data;

@Data
public class ProfilUpdateRequest {

	
	private String kullaniciIsmi;
	private String unvan;
	private int takipEdilen;
	private int takipEden;
	private String sosyalMedya;
	private String onerilenler;
	private String hakkinda;
	private String tecrubeler;
	private String egitimGecmisi;
	private String konum;
	private String yetenekler;
	private String kaydedilenler;
	
}
