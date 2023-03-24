package mx.gob.imss.cit.sarp.oauth.model.dto;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Subrol {

	private Integer cveSubrol;
	private String nomSubrol;

	private ArrayList<Modulo> modulos;
}
