package yazilim.io.oktaSoftwareCompany.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import yazilim.io.oktaSoftwareCompany.Models.Sirket;

public interface SirketRepository extends JpaRepository<Sirket,Integer>{
	boolean existsByName(String name);
}
