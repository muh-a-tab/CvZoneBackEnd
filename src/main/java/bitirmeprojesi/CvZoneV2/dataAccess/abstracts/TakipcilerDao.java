package bitirmeprojesi.CvZoneV2.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import bitirmeprojesi.CvZoneV2.entities.concretes.Takipciler;

public interface TakipcilerDao  extends JpaRepository<Takipciler,Integer>{

	List<Takipciler> findByTakipEden_KullaniciIdAndTakipEdilen_KullaniciId(int takipEdenId, int takipEdilenId);
	
	List<Takipciler> findByTakipEden_KullaniciId(int takipEdenId);
	
	List<Takipciler> findByTakipEdilen_KullaniciId(int takipEdilenId);
	
	
}
