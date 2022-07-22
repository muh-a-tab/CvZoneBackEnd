package bitirmeprojesi.CvZoneV2.business.concretes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import bitirmeprojesi.CvZoneV2.business.abstracts.KullaniciService;
import bitirmeprojesi.CvZoneV2.business.abstracts.OneriService;
import bitirmeprojesi.CvZoneV2.business.abstracts.TakipcilerService;
import bitirmeprojesi.CvZoneV2.entities.concretes.Kullanici;
import bitirmeprojesi.CvZoneV2.responses.BegeniResponse;
import bitirmeprojesi.CvZoneV2.responses.KullaniciResponse;
import bitirmeprojesi.CvZoneV2.responses.TakipcilerResponse;


@Service
public class OneriSistemi implements OneriService{
	
	
	
	private TakipcilerService takipcilerService;
	private KullaniciService kullaniciService;
	
	
	@Autowired
	public OneriSistemi(TakipcilerService takipcilerService,KullaniciService kullaniciService) {
		this.takipcilerService = takipcilerService;
		this.kullaniciService = kullaniciService;
	}
	
	
	public List<Kullanici> oner(int kullaniciId) {
		
		List<TakipcilerResponse> onerilenler = takipcilerService.getAllTakipciler(Optional.of(kullaniciId) ,Optional.ofNullable(null));
		List<Integer> onerilenId = new ArrayList<>();
		List<Kullanici> kullanicilar = new ArrayList<>();
		Collections.shuffle(onerilenler);
		for(int i = 0;i<onerilenler.size();i++) {
			onerilenId.add(kullaniciService.getOne(onerilenler.get(i).getTakipEdilen()).getKullaniciId());
			
		}
		
		for(int i=0;i<onerilenId.size();i++) {
			List<TakipcilerResponse> kul = takipcilerService.getAllTakipciler(Optional.of(onerilenId.get(i)) 
																	,Optional.ofNullable(null));
			for (int j = 0; j < kul.size(); j++) {
				
				if(!isFriend(kul.get(j).getTakipEdilen(), kullaniciId) && !kullanicilar.contains(kullaniciService.findKullanici(kul.get(j).getTakipEdilen())) && kullaniciService.findKullanici(kul.get(j).getTakipEdilen()).getKullaniciId() != kullaniciId)
					kullanicilar.add(kullaniciService.findKullanici(kul.get(j).getTakipEdilen()));
					
				
			}
		}
		
		
		Random rnd = new Random();
		int maxValue = kullaniciService.getAll().get(kullaniciService.getAll().size()-1).getKullaniciId();
		int limit = kullaniciService.getAll().size();
		
		int rSayi;
		int z = 0;
		while(kullanicilar.size() != 5 && z < limit) {
		
			rSayi = rnd.nextInt(maxValue+1)+1;
			
			if(  kullaniciId != rSayi && !isFriend(rSayi, kullaniciId) && !kullanicilar.contains(kullaniciService.getOne(rSayi)) && kullaniciService.getOne(rSayi) != null) {
				kullanicilar.add(kullaniciService.getOne(rSayi));
				
			}	
			
			z++;
		}
		Collections.shuffle(kullanicilar);
		
		
		
		return kullanicilar;
		
	}
	
	
	public boolean isFriend(int friendId,int kullaniciId) {
		List<TakipcilerResponse> arkadaslar = takipcilerService.getAllTakipciler(Optional.of(kullaniciId) 
				,Optional.ofNullable(null));
		
		for(int i=0;i<arkadaslar.size();i++) {
			
			if(arkadaslar.get(i).getTakipEdilen() == friendId)
				return true;
				
		}
		
		
		return false;
	}

}
