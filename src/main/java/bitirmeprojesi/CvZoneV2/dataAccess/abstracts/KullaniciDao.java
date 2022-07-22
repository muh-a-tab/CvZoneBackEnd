package bitirmeprojesi.CvZoneV2.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bitirmeprojesi.CvZoneV2.entities.concretes.Kullanici;

public interface KullaniciDao extends JpaRepository<Kullanici,Integer>{

	Kullanici findByeMailAndSifre(String eMail, String sifre);
	
	List<Kullanici> findByKullaniciTip(int kullaniciTip);
	
}
