package mx.gob.imss.cit.sarp.oauth.model.dto;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Dependencia {

	private Integer cveDependencia;
	private String nomDependencia;

	private ArrayList<Sistema> sistemas;	
}
