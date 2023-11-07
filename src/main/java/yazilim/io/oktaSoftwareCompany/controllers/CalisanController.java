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

import lombok.AllArgsConstructor;
import yazilim.io.oktaSoftwareCompany.business.abstracts.CalisanService;
import yazilim.io.oktaSoftwareCompany.business.request.CreateCalisanRequest;
import yazilim.io.oktaSoftwareCompany.business.request.UpdateCalisanRequest;
import yazilim.io.oktaSoftwareCompany.business.response.GetAllCalisanResponse;

@RestController
@RequestMapping("/api/calisan")
@AllArgsConstructor
public class CalisanController {
	private CalisanService calisanService;
	
	@GetMapping()
	public List<GetAllCalisanResponse> getAll() {
		return calisanService.getAll();
	}
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody() CreateCalisanRequest createCalisanRequest) {
		this.calisanService.add(createCalisanRequest);
	}
	
	@PutMapping
	public void update(@RequestBody() UpdateCalisanRequest updateCalisanRequest) {
		this.calisanService.update(updateCalisanRequest);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		this.calisanService.delete(id);
	}
}
