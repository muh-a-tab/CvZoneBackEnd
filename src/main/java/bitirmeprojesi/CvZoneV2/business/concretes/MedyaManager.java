package bitirmeprojesi.CvZoneV2.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitirmeprojesi.CvZoneV2.business.abstracts.KullaniciService;
import bitirmeprojesi.CvZoneV2.business.abstracts.MedyaService;
import bitirmeprojesi.CvZoneV2.dataAccess.abstracts.MedyaDao;
import bitirmeprojesi.CvZoneV2.entities.concretes.Kullanici;
import bitirmeprojesi.CvZoneV2.entities.concretes.Medya;
import bitirmeprojesi.CvZoneV2.requests.MedyaCreateRequest;
import bitirmeprojesi.CvZoneV2.responses.MedyaResponse;


@Service
public class MedyaManager implements MedyaService{

	private MedyaDao medyaDao;
	private KullaniciService kullaniciService;
	
	@Autowired
	public MedyaManager(MedyaDao medyaDao,KullaniciService kullaniciService) {
		this.medyaDao = medyaDao;
		this.kullaniciService = kullaniciService;
	}

	
	@Override
	public List<MedyaResponse> getAllMedya(Optional<Integer> kullaniciId) {
		List<Medya> list ;
		if(kullaniciId.isPresent()) {
			
			list = this.medyaDao.findByKullanici_KullaniciId(kullaniciId.get());
		}else {
			list = this.medyaDao.findAll();
		}
		
		return list.stream().map(m -> new MedyaResponse(m)).collect(Collectors.toList());
		
	}

	@Override
	public Medya getOne(int medyaId) {
		return this.medyaDao.findById(medyaId).orElse(null);
	}

	@Override
	public void Add(MedyaCreateRequest newMedyaRequest) {
		Kullanici kullanici = this.kullaniciService.getOne(newMedyaRequest.getKullaniciId());
		
		if(kullanici != null) {
			Medya toSave = new Medya();
			toSave.setMedyaId(newMedyaRequest.getMedyaId());
			toSave.setKullanici(kullanici);
			toSave.setBelge(newMedyaRequest.getBelge());
			toSave.setBelgeAdi(newMedyaRequest.getBelgeAdi());
			this.medyaDao.save(toSave);
		}
		
	}

	@Override
	public void Delete(int medyaId) {
		this.medyaDao.deleteById(medyaId);
		
	}

}
