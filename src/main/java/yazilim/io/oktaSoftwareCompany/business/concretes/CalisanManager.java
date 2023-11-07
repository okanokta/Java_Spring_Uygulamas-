package yazilim.io.oktaSoftwareCompany.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import yazilim.io.oktaSoftwareCompany.Models.Calisan;
import yazilim.io.oktaSoftwareCompany.Repositories.CalisanRepository;
import yazilim.io.oktaSoftwareCompany.business.abstracts.CalisanService;
import yazilim.io.oktaSoftwareCompany.business.request.CreateCalisanRequest;
import yazilim.io.oktaSoftwareCompany.business.request.UpdateCalisanRequest;
import yazilim.io.oktaSoftwareCompany.business.response.GetAllCalisanResponse;
import yazilim.io.oktaSoftwareCompany.core.utilities.mappers.ModelMapperService;



@Service 
@AllArgsConstructor
public class CalisanManager implements CalisanService{
	private CalisanRepository calisanRepository;
	private ModelMapperService modelMapperService;

	@Override
	public List<GetAllCalisanResponse> getAll() {
		List<Calisan> calisanlar = calisanRepository.findAll();
		List<GetAllCalisanResponse> calisanResponse = calisanlar.stream()
				.map(calisan ->this.modelMapperService.forResponse()
			    .map(calisan, GetAllCalisanResponse.class)).collect(Collectors.toList());
		return calisanResponse;
	}

	@Override
	public void add(CreateCalisanRequest createCalisanRequest) {
		Calisan calisan = this.modelMapperService.forRequest().map(createCalisanRequest, Calisan.class);
		this.calisanRepository.save(calisan);
		
	}

	@Override
	public void update(UpdateCalisanRequest updateCalisanRequest) {
		Calisan calisan = this.modelMapperService.forRequest().map(updateCalisanRequest, Calisan.class);
		this.calisanRepository.save(calisan);
		
	}

	@Override
	public void delete(int id) {
		this.calisanRepository.deleteById(id);
		
	}

}
