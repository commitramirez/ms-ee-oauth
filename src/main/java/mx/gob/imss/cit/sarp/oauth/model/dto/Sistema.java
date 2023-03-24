package mx.gob.imss.cit.sarp.oauth.model.dto;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Sistema {

	private Integer cveSistema;
	private String nomSistema;

	private ArrayList<Rol> roles;	
}
