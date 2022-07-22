package bitirmeprojesi.CvZoneV2.responses;

import bitirmeprojesi.CvZoneV2.entities.concretes.Takipciler;
import lombok.Data;

@Data
public class TakipcilerResponse {
	private int takipId;
	private int takipEden;
	private int takipEdilen;
	
	public TakipcilerResponse(Takipciler entity) {
		this.takipId = entity.getTakipId();
		this.takipEden = entity.getTakipEden().getKullaniciId();
		this.takipEdilen = entity.getTakipEdilen().getKullaniciId();	
	}
}
