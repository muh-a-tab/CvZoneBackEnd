package bitirmeprojesi.CvZoneV2.dataAccess.abstracts;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import bitirmeprojesi.CvZoneV2.entities.concretes.Post;

public interface PostDao extends JpaRepository<Post, Integer> {

	List<Post> findByKullanici_KullaniciId(int kullaniciId);

}
