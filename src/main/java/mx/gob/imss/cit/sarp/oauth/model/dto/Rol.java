package mx.gob.imss.cit.sarp.oauth.model.dto;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Rol {

	private Integer cveRol;
	private String nomRol;

	private ArrayList<Subrol> subroles;	
}
