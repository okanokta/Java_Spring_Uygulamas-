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
