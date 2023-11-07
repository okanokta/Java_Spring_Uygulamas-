package yazilim.io.oktaSoftwareCompany.business.abstracts;

import java.util.List;

import yazilim.io.oktaSoftwareCompany.business.request.CreateCalisanRequest;
import yazilim.io.oktaSoftwareCompany.business.request.UpdateCalisanRequest;
import yazilim.io.oktaSoftwareCompany.business.response.GetAllCalisanResponse;

public interface CalisanService {
	List<GetAllCalisanResponse> getAll();
	//GetByIdResponse getById(int id); 
	void add(CreateCalisanRequest createCalisanRequest);
	void update(UpdateCalisanRequest updateCalisanRequest);
	void delete(int id);
}
