package yazilim.io.oktaSoftwareCompany.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import yazilim.io.oktaSoftwareCompany.Models.Sirket;
import yazilim.io.oktaSoftwareCompany.Repositories.SirketRepository;
import yazilim.io.oktaSoftwareCompany.business.abstracts.SirketService;
import yazilim.io.oktaSoftwareCompany.business.request.CreateSirketRequest;
import yazilim.io.oktaSoftwareCompany.business.request.UpdateSirketRequest;
import yazilim.io.oktaSoftwareCompany.business.response.GettAllSirketResponse;
import yazilim.io.oktaSoftwareCompany.core.utilities.mappers.ModelMapperService;


@Service
@AllArgsConstructor
public class SirketManager implements SirketService {
	private SirketRepository sirketRepository;
	private ModelMapperService modelMapperService;

	@Override
	public List<GettAllSirketResponse> getAll() {
		  List<Sirket> sirkets = sirketRepository.findAll();
		  List<GettAllSirketResponse> sirketResponse = sirkets.stream()
					.map(sirket ->this.modelMapperService.forResponse()
							.map(sirket, GettAllSirketResponse.class)).collect(Collectors.toList());
			
			return sirketResponse;
	}

	@Override
	public void add(CreateSirketRequest createSirketRequest) {
		Sirket sirket = this.modelMapperService.forRequest().map(createSirketRequest, Sirket.class);
		this.sirketRepository.save(sirket);

	}

	@Override
	public void update(UpdateSirketRequest updateSirketRequest) {
		Sirket sirket = this.modelMapperService.forRequest().map(updateSirketRequest, Sirket.class);
		this.sirketRepository.save(sirket);
	}

	@Override
	public void delete(int id) {
		this.sirketRepository.deleteById(id);

	}

}
