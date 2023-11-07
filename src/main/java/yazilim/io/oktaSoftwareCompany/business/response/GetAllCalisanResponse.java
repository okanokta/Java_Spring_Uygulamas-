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
