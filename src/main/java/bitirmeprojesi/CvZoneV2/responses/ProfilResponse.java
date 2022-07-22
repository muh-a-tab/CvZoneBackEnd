package bitirmeprojesi.CvZoneV2.responses;

import bitirmeprojesi.CvZoneV2.entities.concretes.Profil;
import lombok.Data;

@Data
public class ProfilResponse {

	private int profilId;
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
	private int kullaniciId;
	
	public ProfilResponse(Profil entity) {
		this.profilId = entity.getProfilId();
		this.kullaniciIsmi = entity.getKullaniciIsmi();
		this.unvan = entity.getUnvan();
		this.takipEden = entity.getTakipEden();
		this.takipEdilen = entity.getTakipEdilen();
		this.sosyalMedya = entity.getSosyalMedya();
		this.onerilenler = entity.getOnerilenler();
		this.hakkinda = entity.getHakkinda();
		this.tecrubeler = entity.getTecrubeler();
		this.egitimGecmisi = entity.getEgitimGecmisi();
		this.konum = entity.getKonum();
		this.yetenekler = entity.getYetenekler();
		this.kaydedilenler = entity.getKaydedilenler();
		this.kullaniciId = entity.getKullanici().getKullaniciId();
	}
	
}
