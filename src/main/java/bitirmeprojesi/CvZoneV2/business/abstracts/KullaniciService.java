package bitirmeprojesi.CvZoneV2.business.abstracts;

import java.util.List;


import bitirmeprojesi.CvZoneV2.entities.concretes.Kullanici;
import bitirmeprojesi.CvZoneV2.requests.KullaniciResimUpdateRequest;
import bitirmeprojesi.CvZoneV2.responses.KullaniciAuthResponse;
import bitirmeprojesi.CvZoneV2.responses.KullaniciResponse;


public interface KullaniciService {
	List<Kullanici> getAll();
	Kullanici getOne(int kullaniciId);
	Kullanici findKullanici(int kullaniciId);
	void Add(Kullanici kullanici);
	void Update(int kullaniciId,Kullanici kullanici);
	void Delete(int kullaniciId);
	KullaniciAuthResponse getAuth(String eMail,String sifre);
	void UpdateResim(KullaniciResimUpdateRequest kullanici);
	List<KullaniciResponse> getTakipEdenler(int kullaniciId);
	List<KullaniciResponse> getTakipEdilenler(int kullaniciId);
	List<KullaniciResponse> getFirmalar();
}
