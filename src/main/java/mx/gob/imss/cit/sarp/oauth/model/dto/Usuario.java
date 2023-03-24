package mx.gob.imss.cit.sarp.oauth.model.dto;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Usuario {
	
	private Integer cveUsuario;
	private String nomUsuario;
	private String paternoUsuario;
	private String maternoUsuario;
	private String correoUsuario;
	
	private ArrayList<Dependencia> dependencias;	
}