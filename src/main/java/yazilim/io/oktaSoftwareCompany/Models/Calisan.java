package yazilim.io.oktaSoftwareCompany.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="calisan") //db tablo adı
@Getter //property'ler için getter 
@Setter //property'ler için setter
@AllArgsConstructor //argumanlı constructor
@NoArgsConstructor //argumansız constructor
@Entity //Bu classın db de maplenecegini soyleriz
public class Calisan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //otomatik artan sayı
	@Column(name="id") //kolon adı
	private int id;
	
	@Column(name="firstName") //kolon adı
	private String firstName;
	
	@Column(name="lastName") //kolon adı
	private String lastName;
	
	@Column(name="position") //kolon adı
	private String position;
	
	@ManyToOne
	@JoinColumn(name = "sirket_id") // iliskili kolon adı
	private Sirket sirket;
}
