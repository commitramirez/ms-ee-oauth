package mx.gob.imss.cit.sarp.oauth.model.dto;

import lombok.Data;

@Data
public class FuncionalidadRequest {

	private String correoUsuario;
	private int cveUsuario;
	private int cveDependencia;
	private int cveSistema;
	private int cveRol;
	private int cveSubrol;
	private int cveModulo;
}
