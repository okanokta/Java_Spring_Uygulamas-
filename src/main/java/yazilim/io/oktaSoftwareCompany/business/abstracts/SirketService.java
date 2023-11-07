package yazilim.io.oktaSoftwareCompany.business.abstracts;

import java.util.List;

import yazilim.io.oktaSoftwareCompany.business.request.CreateSirketRequest;
import yazilim.io.oktaSoftwareCompany.business.request.UpdateSirketRequest;
import yazilim.io.oktaSoftwareCompany.business.response.GettAllSirketResponse;



public interface SirketService {
	List<GettAllSirketResponse> getAll();
	//GetByIdResponse getById(int id); 
	void add(CreateSirketRequest createSirketRequest);
	void update(UpdateSirketRequest updateSirketRequest);
	void delete(int id);
}
