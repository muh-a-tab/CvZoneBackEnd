package bitirmeprojesi.CvZoneV2.dataAccess.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bitirmeprojesi.CvZoneV2.entities.concretes.Yorum;

public interface YorumDao extends JpaRepository<Yorum,Integer>{

	List<Yorum> findByPost_PostIdAndKullanici_KullaniciId(int postId,int kullaniciId);

	List<Yorum> findByKullanici_KullaniciId(int kullaniciId);

	List<Yorum> findByPost_PostId(int postId);
	
}
