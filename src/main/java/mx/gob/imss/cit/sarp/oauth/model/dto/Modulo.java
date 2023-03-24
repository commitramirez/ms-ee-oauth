package mx.gob.imss.cit.sarp.oauth.model.dto;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Modulo {

	private Integer cveModulo;
	private String nomModulo;

	private ArrayList<Funcionalidad> funcionalidades;	
}
