package bitirmeprojesi.CvZoneV2.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import bitirmeprojesi.CvZoneV2.business.abstracts.KullaniciService;
import bitirmeprojesi.CvZoneV2.business.abstracts.ProfilService;
import bitirmeprojesi.CvZoneV2.business.abstracts.TakipcilerService;
import bitirmeprojesi.CvZoneV2.dataAccess.abstracts.KullaniciDao;
import bitirmeprojesi.CvZoneV2.entities.concretes.Kullanici;
import bitirmeprojesi.CvZoneV2.requests.KullaniciResimUpdateRequest;
import bitirmeprojesi.CvZoneV2.requests.ProfilCreateRequest;
import bitirmeprojesi.CvZoneV2.responses.KullaniciAuthResponse;
import bitirmeprojesi.CvZoneV2.responses.KullaniciResponse;
import bitirmeprojesi.CvZoneV2.responses.TakipcilerResponse;


@Service
public class KullaniciManager implements KullaniciService {

	private KullaniciDao kullaniciDao;
	private ProfilService profilService;
	private TakipcilerService takipcilerService;
	
	@Autowired
	KullaniciManager(KullaniciDao kullaniciDao,@Lazy ProfilService profilService,@Lazy TakipcilerService takipcilerService){
		this.kullaniciDao = kullaniciDao;
		this.profilService = profilService;
		this.takipcilerService = takipcilerService;
	}
	
	@Override
	public List<Kullanici> getAll() {
		return this.kullaniciDao.findAll();
	}

	@Override
	public Kullanici getOne(int kullaniciId) {
		return this.kullaniciDao.findById(kullaniciId).orElse(null);
	
	}

	@Override
	public void Add(Kullanici kullanici) {
		kullanici.setResim("https://garverins.com/wp-content/uploads/user-300x300.png");
		this.kullaniciDao.save(kullanici);
		ProfilCreateRequest profil = new ProfilCreateRequest();
		profil.setEgitimGecmisi("Lütfen Eğitim Bilgilerini Giriniz");
		profil.setHakkinda("Lütfen Hakkınızda Bilgisini Giriniz");
		profil.setKaydedilenler("");
		profil.setKonum(kullanici.getSehir());
		profil.setKullaniciIsmi(kullanici.getAdSoyad());
		profil.setOnerilenler("");
		profil.setSosyalMedya("Sosyal Medya Hesaplarını Giriniz (İnstagram:kullaniciadi, Facebook:kullaniciadi vb)");
		profil.setTakipEden(0);
		profil.setTakipEdilen(0);
		profil.setTecrubeler("Tecrübe Bilgilerinizi Giriniz (Havelsan(1 yıl), Aselsan(2 yıl) vb)");
		profil.setUnvan("Unvan Bilginizi Giriniz");
		profil.setYetenekler("Yeteneklerinizi Giriniz (Java, React vb.)");
		profil.setKullaniciId(kullanici.getKullaniciId());
		profilService.Add(profil);
		
		
	}

	@Override
	public void Update(int kullaniciId,Kullanici kullanici) {
		Optional<Kullanici> kullanici1 = kullaniciDao.findById(kullaniciId);
		if(kullanici1.isPresent()) {
			Kullanici bulunanKullanici = kullanici1.get();
			bulunanKullanici.setAdSoyad(kullanici.getAdSoyad());
			bulunanKullanici.setEMail(kullanici.getEMail());
			bulunanKullanici.setKullaniciTip(kullanici.getKullaniciTip());
			bulunanKullanici.setSehir(kullanici.getSehir());
			bulunanKullanici.setSifre(kullanici.getSifre());
			kullaniciDao.save(bulunanKullanici);
			
		}
	}

	@Override
	public void Delete(int kullaniciId) {
		this.kullaniciDao.deleteById(kullaniciId);;
	}

	@Override
	public Kullanici findKullanici(int kullaniciId) {
		return this.kullaniciDao.findById(kullaniciId).get();
	}

	@Override
	public KullaniciAuthResponse getAuth(String eMail,String sifre) {
		Kullanici k = this.kullaniciDao.findByeMailAndSifre(eMail,sifre);
		
		if(k != null)
			return new KullaniciAuthResponse(k);
		else
			return new KullaniciAuthResponse(0);
	}

	@Override
	public void UpdateResim(KullaniciResimUpdateRequest kullanici) {
		Kullanici updateResim  = this.findKullanici(kullanici.getKullaniciId());
		updateResim.setResim(kullanici.getResim());
		this.Update(kullanici.getKullaniciId(), updateResim);
	
	}
	
	@Override
	public List<KullaniciResponse> getTakipEdenler(int kullaniciId) {
		List<TakipcilerResponse> takipEdilenler = takipcilerService.getAllTakipciler(Optional.ofNullable(null)
				,Optional.of(kullaniciId));
		
		
		List<Kullanici> kullanicilar = new ArrayList<>();
		for(int i = 0;i<takipEdilenler.size();i++) {
			kullanicilar.add(this.getOne(takipEdilenler.get(i).getTakipEden()));
		}
		
		return kullanicilar.stream().map(k -> new KullaniciResponse(k)).collect(Collectors.toList());
	}

	@Override
	public List<KullaniciResponse> getTakipEdilenler(int kullaniciId) {
		
		
		List<TakipcilerResponse> takipEdenler = takipcilerService.getAllTakipciler(Optional.of(kullaniciId) 
				,Optional.ofNullable(null));
		
		List<Kullanici> kullanicilar = new ArrayList<>();
		for(int i = 0;i<takipEdenler.size();i++) {
			kullanicilar.add(this.getOne(takipEdenler.get(i).getTakipEdilen()));
		}
		
		return kullanicilar.stream().map(k -> new KullaniciResponse(k)).collect(Collectors.toList());
	}

	@Override
	public List<KullaniciResponse> getFirmalar() {
		List<Kullanici> list = this.kullaniciDao.findByKullaniciTip(1);
		return list.stream().map(k -> new KullaniciResponse(k)).collect(Collectors.toList());
		
	}
	

}
