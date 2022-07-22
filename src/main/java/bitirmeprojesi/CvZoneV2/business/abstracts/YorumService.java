package bitirmeprojesi.CvZoneV2.business.abstracts;

import java.util.List;
import java.util.Optional;

import bitirmeprojesi.CvZoneV2.entities.concretes.Yorum;
import bitirmeprojesi.CvZoneV2.requests.YorumCreateRequest;
import bitirmeprojesi.CvZoneV2.requests.YorumUpdateRequest;
import bitirmeprojesi.CvZoneV2.responses.YorumResponse;

public interface YorumService {
	List<YorumResponse> getAllYorum(Optional<Integer> postId,Optional<Integer> kullaniciId);
	Yorum getOne(int yorumId);
	void Add(YorumCreateRequest yorumCreateRequest);
	void Update(int yorumId,YorumUpdateRequest yorumUpdateRequest);
	void Delete(int yorumId);
}
