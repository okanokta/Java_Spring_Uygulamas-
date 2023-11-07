# Java_Spring_Uygulamasi-
Java Spring Boot ile bir RestApi Projesi
## Proje için Gerekli Teknolojiler
## 1. Spring-Boot projesi için Temel Dosyalar
Bunun İçin [Spring.io](https://start.spring.io/) Adresine gidip Asagıdaki gibi eklentileri eklememiz ve Generate gerekiyor.
![İnitializr](https://github.com/okanokta/Java_Spring_Uygulamas-/assets/100216485/dcfe7bc6-767a-4a17-878e-3ce5eddd93c3)
## 2. PostgreSQL Veritabanı
PostgreSQL'in kurulu olması lazım Bende Daha önceden Kurulu oldugundan sizde yoksa PostgreSQL'in adresinden ilgili işletim sisteminize göre kurulum yapabilirsiniz.
## Java Uygulamasını Ayaga Kaldırma
Spring.io da Generate ettigimiz dosyayı zip'den iligili klasöre çıkartıp Eclipse'den asagıdaki ekran goruntusunde oldugu gibi import ettikten sonra proje ayaga kalkacaktır.
![Ayağa_Kaldırma](https://github.com/okanokta/Java_Spring_Uygulamas-/assets/100216485/f4deef53-5cff-4527-8b6d-cb7ab59bf707)
## Spring Uygulamamıza Veritabanı Baglantısı
#### . VeriTabanı Bağlantısı ve Port Seçimi -main/resources/application.properties
```
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.show-sql=true
spring.datasource.url=jdbc:postgresql://localhost:5432/softwareCompany #softwareCompany veritabanı adımız oluyor
spring.datasource.username=postgres   #PostgreSQL kullanıcı adı
spring.datasource.password=12345      #Şifresi
spring.jpa.properties.javax.persistence.validation.mode = none  
server.port=8081 #Api için port 
```
## Modelleri Olusturmak
Sirket ve Calisan adında iki tane Model ekledik.
Modellerdeki getter,setter constructor'lar gibi metotları lombok sayesinde sınıftaki kodları uzamadan halletmiş olduk.
## DataAccess'lerimiz olan Repositorileri Olusturma
Repositories package'ının altında oluşturuldu. Her bir repository JpaRepository'i extends ettirerek Jpa Tarafından bize sql tarafı için birçok hazır yapı gelmekte
## Request ve Response'lar Olusturma
Buradaki onemli noktalardan birisi de id gibi bilgileri vs. Gostermemek ve gerekirse ,istek ve cevaplar icin ayrı ayrı metotlar olusturmak ,isteginize gore cevaplarınızı donebilir, isteginize gore isteklerinizi belirleyebilmek icin Boyle bir islem gerceklestirildi..
##### Request 'ler
```
package yazilim.io.oktaSoftwareCompany.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCalisanRequest {
	private String firstName;
	private String lastName;
	private String position;
	
	private int sirketId;
}

```
```
package yazilim.io.oktaSoftwareCompany.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSirketRequest { // id'yi bilmesine gerek yok son kullanıcının
	private String name;

}

```
```
package yazilim.io.oktaSoftwareCompany.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCalisanRequest {
	private int id;
	private String position;
}

```
```
package yazilim.io.oktaSoftwareCompany.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSirketRequest {
	private int id;
	private String name;
}

```
##### Response 'lar
```
package yazilim.io.oktaSoftwareCompany.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCalisanResponse {
	private int id;
	private String firstName;
	private String lastName;
	private String position;
	private String sirketName;
}

```
```
package yazilim.io.oktaSoftwareCompany.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GettAllSirketResponse {
	private int id;
	private String name;
}

```
## Serviceler ve Bu servicelerin is kuraları olan Manager sınıflarını Olusturma
İlk olarak CalisanService ve SirketService adında interfacelerimizi oluşturduk ve içerisinde Crud işlem metotlarını ekledik.
Daha sonra ise Crud islem metotlarını CalisanManager ve SirketManager da iş kurallarımızı yazıp Halletmiş olduk.
-- CalisanService --
```
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

```
-- CalisanManager --
```
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
```
-- SirketService --
```
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
```
-- SirketManager --
```
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

```
## Manager Sınıflarındaki ModelMapper Kullanımı Hakkında
Mapper'lar Genellikle istemci istekleri ve yanıtlarının (request-response) dönüşüm işlemlerini belirli bir şekilde yapılandırmak için kullanılır. 
Bu sayede istemci istekleri ve sunucu yanıtları arasındaki nesne dönüşümü işlemleri daha belirgin ve özelleştirilebilir hale getirilir.
```
package yazilim.io.oktaSoftwareCompany.core.utilities.mappers;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	ModelMapper forResponse();
	ModelMapper forRequest();
}

```
```
package yazilim.io.oktaSoftwareCompany.core.utilities.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ModelMapperManager implements ModelMapperService{

	private ModelMapper modelMapper;

	@Override
	public ModelMapper forResponse() {
		this.modelMapper.getConfiguration()
		.setAmbiguityIgnored(true)
		.setMatchingStrategy(MatchingStrategies.LOOSE);
		return this.modelMapper;
	}

	@Override
	public ModelMapper forRequest() {
		this.modelMapper.getConfiguration()
		.setAmbiguityIgnored(true)
		.setMatchingStrategy(MatchingStrategies.STANDARD);
		return this.modelMapper;
	}

}
```
Manager icindeki özellikle List(listeleme),add(ekleme) ve update(guncelleme) islemlerinde 
```
/*
    * List<GetAllBrandsResponse> brandResponse = new
    * ArrayList<GetAllBrandsResponse>(); for(Brand brand:brands) {
    * GetAllBrandsResponse responseItem = new GetAllBrandsResponse();
    * responseItem.setId(brand.getId()); responseItem.setName(brand.getName());
    * brandResponse.add(responseItem); }
*/
```
Bu sekilde cok sayıda kod yazmak yerine birbirine benzeyen nesneleri örnegin Sirket'i GetAllSirketResponse'a çevirmek için Mapper'ları kullanırız. 
## Controller Sınıfları OLuşturma
#### SirketController
```
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
@RequestMapping("/api/sirket") //Ana Yol
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

```
#### CalisanController
```
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
@RequestMapping("/api/calisan") //Ana Yol
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

```
