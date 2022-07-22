package bitirmeprojesi.CvZoneV2.business.abstracts;

import java.util.List;
import java.util.Optional;

import bitirmeprojesi.CvZoneV2.entities.concretes.Medya;
import bitirmeprojesi.CvZoneV2.requests.MedyaCreateRequest;
import bitirmeprojesi.CvZoneV2.responses.MedyaResponse;


public interface MedyaService {

	List<MedyaResponse> getAllMedya(Optional<Integer> kullaniciId);
	Medya getOne(int medyaId);
	void Add(MedyaCreateRequest newMedyaRequest);
	void Delete(int medyaId);
	
	
}
