package bitirmeprojesi.CvZoneV2.business.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

import bitirmeprojesi.CvZoneV2.requests.TakipcilerCreateRequest;
import bitirmeprojesi.CvZoneV2.responses.TakipcilerResponse;

public interface TakipcilerService {

	List<TakipcilerResponse> getAllTakipciler(Optional<Integer> takipEdenId, Optional<Integer> takipEdilenId);
	void Add(TakipcilerCreateRequest takipcilerCreateRequest);
	void Delete(int takipId);
	
}
