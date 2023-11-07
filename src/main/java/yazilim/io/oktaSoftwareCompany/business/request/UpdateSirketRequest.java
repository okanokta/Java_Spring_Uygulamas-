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
