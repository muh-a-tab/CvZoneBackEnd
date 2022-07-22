package bitirmeprojesi.CvZoneV2.requests;

import java.util.List;


import lombok.Data;

@Data
public class PostCreateRequest {
	
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
}
