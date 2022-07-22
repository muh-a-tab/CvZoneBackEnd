package bitirmeprojesi.CvZoneV2.business.abstracts;


import java.util.List;
import java.util.Optional;

import bitirmeprojesi.CvZoneV2.entities.concretes.Begeni;
import bitirmeprojesi.CvZoneV2.requests.BegeniCreateRequest;
import bitirmeprojesi.CvZoneV2.responses.BegeniResponse;


public interface BegeniService {

	Begeni getOne(int begeniId);
	void Delete(int begeniId);
	void Add(BegeniCreateRequest begeniCreateRequest);
	List<BegeniResponse> getAllBegeni(Optional<Integer> postId,Optional<Integer> kullaniciId);

}
