package bitirmeprojesi.CvZoneV2.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bitirmeprojesi.CvZoneV2.entities.concretes.Begeni;

public interface BegeniDao extends JpaRepository<Begeni,Integer>{

	List<Begeni> findByPost_PostId(int postId);

	List<Begeni> findByPost_PostIdAndKullanici_KullaniciId(int postId, int kullaniciId);

	List<Begeni> findByKullanici_KullaniciId(int kullaniciId);

}
