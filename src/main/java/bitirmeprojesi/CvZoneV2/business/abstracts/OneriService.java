package bitirmeprojesi.CvZoneV2.business.abstracts;

import java.util.List;

import bitirmeprojesi.CvZoneV2.entities.concretes.Kullanici;
import bitirmeprojesi.CvZoneV2.responses.KullaniciResponse;

public interface OneriService {
	List<Kullanici> oner(int kullaniciId);
}
