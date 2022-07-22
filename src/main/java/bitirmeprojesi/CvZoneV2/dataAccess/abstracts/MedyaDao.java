package bitirmeprojesi.CvZoneV2.dataAccess.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bitirmeprojesi.CvZoneV2.entities.concretes.Medya;


public interface MedyaDao  extends JpaRepository<Medya, Integer>{

	List<Medya> findByKullanici_KullaniciId(int kullaniciId);

}
