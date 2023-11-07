package yazilim.io.oktaSoftwareCompany.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import yazilim.io.oktaSoftwareCompany.business.abstracts.SirketService;
import yazilim.io.oktaSoftwareCompany.business.request.CreateSirketRequest;
import yazilim.io.oktaSoftwareCompany.business.request.UpdateSirketRequest;
import yazilim.io.oktaSoftwareCompany.business.response.GettAllSirketResponse;


@RestController
@RequestMapping("/api/sirket")
@AllArgsConstructor
public class SirketControllers {
	private SirketService sirketService;
	
	@GetMapping()
	public List<GettAllSirketResponse> getAll() {
		return sirketService.getAll();
	}
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody() @Valid() CreateSirketRequest createSirektRequest) {
		this.sirketService.add(createSirektRequest);
	}
	
	@PutMapping
	public void update(@RequestBody() UpdateSirketRequest updateSirketRequest) {
		this.sirketService.update(updateSirketRequest);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		this.sirketService.delete(id);
	}
}
