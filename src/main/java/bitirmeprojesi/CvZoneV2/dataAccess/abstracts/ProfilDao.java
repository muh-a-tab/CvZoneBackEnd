package bitirmeprojesi.CvZoneV2.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bitirmeprojesi.CvZoneV2.entities.concretes.Profil;

public interface ProfilDao extends JpaRepository<Profil,Integer>{

	Profil findByKullanici_KullaniciId(int kullaniciId);

	
	
}
