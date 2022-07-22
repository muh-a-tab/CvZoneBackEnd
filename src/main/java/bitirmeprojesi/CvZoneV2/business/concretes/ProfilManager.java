package bitirmeprojesi.CvZoneV2.business.concretes;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitirmeprojesi.CvZoneV2.business.abstracts.KullaniciService;
import bitirmeprojesi.CvZoneV2.business.abstracts.ProfilService;
import bitirmeprojesi.CvZoneV2.business.abstracts.TakipcilerService;
import bitirmeprojesi.CvZoneV2.dataAccess.abstracts.ProfilDao;
import bitirmeprojesi.CvZoneV2.entities.concretes.Kullanici;
import bitirmeprojesi.CvZoneV2.entities.concretes.Profil;
import bitirmeprojesi.CvZoneV2.requests.ProfilCreateRequest;
import bitirmeprojesi.CvZoneV2.requests.ProfilUpdateRequest;

@Service
public class ProfilManager implements ProfilService{

	private ProfilDao profilDao;
	private KullaniciService kullaniciService;
	private TakipcilerService takipcilerService;
	
	@Autowired
	public ProfilManager(ProfilDao profilDao,KullaniciService kullaniciService,TakipcilerService takipcilerService) {
		this.profilDao = profilDao;
		this.kullaniciService=kullaniciService;
		this.takipcilerService = takipcilerService;
	}

	@Override
	public Profil getOne(int kullaniciId) {
		Profil profil = this.profilDao.findByKullanici_KullaniciId(kullaniciId);
	
		if(profil != null) {
			
			profil.setTakipEdilen(takipcilerService.getAllTakipciler(Optional.of(profil.getKullanici().getKullaniciId()),
																	Optional.ofNullable(null)).size());
			
			profil.setTakipEden(takipcilerService.getAllTakipciler(Optional.ofNullable(null),
					Optional.of(profil.getKullanici().getKullaniciId())).size());
			
			return profil;
		}
			
		else {
			return null;
		}
			
	}

	@Override
	public void Add(ProfilCreateRequest profilCreateRequest) {
		Kullanici kullanici =  kullaniciService.getOne(profilCreateRequest.getKullaniciId());
		if(kullanici != null) {
			Profil toSave = new Profil();
			toSave.setProfilId(profilCreateRequest.getProfilId());
			toSave.setKullaniciIsmi(profilCreateRequest.getKullaniciIsmi());
			toSave.setHakkinda(profilCreateRequest.getHakkinda());
			toSave.setEgitimGecmisi(profilCreateRequest.getEgitimGecmisi());
			toSave.setKaydedilenler(profilCreateRequest.getKaydedilenler());
			toSave.setKonum(profilCreateRequest.getKonum());
			toSave.setOnerilenler(profilCreateRequest.getOnerilenler());
			toSave.setSosyalMedya(profilCreateRequest.getSosyalMedya());
			toSave.setTakipEden(profilCreateRequest.getTakipEden());
			toSave.setTakipEdilen(profilCreateRequest.getTakipEdilen());
			toSave.setTecrubeler(profilCreateRequest.getTecrubeler());
			toSave.setUnvan(profilCreateRequest.getUnvan());
			toSave.setYetenekler(profilCreateRequest.getYetenekler());
			toSave.setKullanici(kullanici);
			this.profilDao.save(toSave);
		}
		
	}

	@Override
	public void Update(int profilId, ProfilUpdateRequest profilUpdateRequest) {
		Optional<Profil> profil = profilDao.findById(profilId);
		if(profil.isPresent()) {
			
			Profil toUpdate = profil.get();
			toUpdate.setKullaniciIsmi(profilUpdateRequest.getKullaniciIsmi());
			toUpdate.setHakkinda(profilUpdateRequest.getHakkinda());
			toUpdate.setEgitimGecmisi(profilUpdateRequest.getEgitimGecmisi());
			toUpdate.setKaydedilenler(profilUpdateRequest.getKaydedilenler());
			toUpdate.setKonum(profilUpdateRequest.getKonum());
			toUpdate.setOnerilenler(profilUpdateRequest.getOnerilenler());
			toUpdate.setSosyalMedya(profilUpdateRequest.getSosyalMedya());
			toUpdate.setTakipEden(profilUpdateRequest.getTakipEden());
			toUpdate.setTakipEdilen(profilUpdateRequest.getTakipEdilen());
			toUpdate.setTecrubeler(profilUpdateRequest.getTecrubeler());
			toUpdate.setUnvan(profilUpdateRequest.getUnvan());
			toUpdate.setYetenekler(profilUpdateRequest.getYetenekler());

			this.profilDao.save(toUpdate);
		}
		
		
		
	}

	@Override
	public void Delete(int profilId) {
		this.profilDao.deleteById(profilId);
	}

}
