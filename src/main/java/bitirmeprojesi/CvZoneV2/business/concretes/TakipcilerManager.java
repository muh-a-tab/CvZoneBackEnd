package bitirmeprojesi.CvZoneV2.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitirmeprojesi.CvZoneV2.business.abstracts.KullaniciService;
import bitirmeprojesi.CvZoneV2.business.abstracts.TakipcilerService;
import bitirmeprojesi.CvZoneV2.dataAccess.abstracts.TakipcilerDao;
import bitirmeprojesi.CvZoneV2.entities.concretes.Kullanici;
import bitirmeprojesi.CvZoneV2.entities.concretes.Takipciler;
import bitirmeprojesi.CvZoneV2.requests.TakipcilerCreateRequest;
import bitirmeprojesi.CvZoneV2.responses.TakipcilerResponse;

@Service
public class TakipcilerManager implements TakipcilerService{

	private TakipcilerDao takipcilerDao;
	private KullaniciService kullaniciService;
	
	@Autowired
	public TakipcilerManager(TakipcilerDao takipcilerDao,KullaniciService kullaniciService) {
		this.takipcilerDao = takipcilerDao;
		this.kullaniciService = kullaniciService;
	}

	@Override
	public void Add(TakipcilerCreateRequest takipcilerCreateRequest) {
		Kullanici takipEden = kullaniciService.getOne(takipcilerCreateRequest.getTakipEden());
		Kullanici takipEdilen = kullaniciService.getOne(takipcilerCreateRequest.getTakipEdilen());
		if(takipEden != null && takipEdilen != null ) {
			Takipciler toSave = new Takipciler();
			toSave.setTakipId(takipcilerCreateRequest.getTakipId());
			toSave.setTakipEden(takipEden);
			toSave.setTakipEdilen(takipEdilen);
			this.takipcilerDao.save(toSave);
		}
	}

	@Override
	public void Delete(int takipId) {
		this.takipcilerDao.deleteById(takipId);
	}

	@Override
	public List<TakipcilerResponse> getAllTakipciler(Optional<Integer> takipEdenId, Optional<Integer> takipEdilenId) {
		
		List<Takipciler> list;
		if(takipEdenId.isPresent() && takipEdilenId.isPresent())
			list = takipcilerDao.findByTakipEden_KullaniciIdAndTakipEdilen_KullaniciId(takipEdenId.get(), takipEdilenId.get());
		else if(takipEdenId.isPresent())
			list = takipcilerDao.findByTakipEden_KullaniciId(takipEdenId.get());
		else if(takipEdilenId.isPresent())
			list = takipcilerDao.findByTakipEdilen_KullaniciId(takipEdilenId.get());
		else
			list = takipcilerDao.findAll();
		
		return list.stream().map(takipciler -> new TakipcilerResponse(takipciler)).collect(Collectors.toList());
	}

	

}
