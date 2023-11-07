package yazilim.io.oktaSoftwareCompany.Models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="sirket")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sirket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy = "sirket") // mappedBy = "sirket" buradaki sirket model varlığındaki  private Sirket sirket; olarak tanımlanmış olan 'sirket' den gelir ilişkilendirilmesi.
	List<Calisan> calisanlar;
}
