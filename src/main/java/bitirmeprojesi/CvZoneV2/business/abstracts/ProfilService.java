package bitirmeprojesi.CvZoneV2.business.abstracts;

import bitirmeprojesi.CvZoneV2.entities.concretes.Profil;
import bitirmeprojesi.CvZoneV2.requests.ProfilCreateRequest;
import bitirmeprojesi.CvZoneV2.requests.ProfilUpdateRequest;

public interface ProfilService {
	
	Profil getOne(int kullaniciId);
	void Add(ProfilCreateRequest profilCreateRequest);
	void Update(int profilId,ProfilUpdateRequest profilUpdateRequest);
	void Delete(int profilId);

}
