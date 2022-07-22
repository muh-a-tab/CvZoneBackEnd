package bitirmeprojesi.CvZoneV2.requests;

import java.util.Date;

import lombok.Data;

@Data
public class YorumCreateRequest {

	private int yorumId;
	private int kullanici;
	private int post;
	private String yorum;
	private Date createdDate;
}
