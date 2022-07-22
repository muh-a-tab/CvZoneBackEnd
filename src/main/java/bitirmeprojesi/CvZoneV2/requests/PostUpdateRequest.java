package bitirmeprojesi.CvZoneV2.requests;

import java.util.List;

import lombok.Data;

@Data
public class PostUpdateRequest {

	private String baslik;
	private String konum;
	private String calismaTuru;
	private String icerik;
	private List<String> etiketler;
	private int begeniler;
	private int yorumlar;
	private int goruntulenme;
 	
}
