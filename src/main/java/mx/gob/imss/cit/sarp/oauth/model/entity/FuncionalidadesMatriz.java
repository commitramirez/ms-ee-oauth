package mx.gob.imss.cit.sarp.oauth.model.entity;

import lombok.Data;

@Data
public class FuncionalidadesMatriz {
	
	// USUARIO
	private Integer cveUsuario;
	private String nomUsuario;
	private String paternoUsuario;
	private String maternoUsuario;
	private String correoUsuario;
	
	// DEPENDENCIA
	private Integer cveDependencia;
	private String nomDependencia;
	
	// SISTEMA
	private Integer cveSistema;
	private String nomSistema;
	
	// MODULOS
	private Integer cveModulo;
	private String nomModulo;
	
	// FUNCIONALIDADES
	private Integer cveFuncionalidad;
	private String nomFuncionalidad;
	private String cveRutaFuncionalidad;
	private String cveRutaAngularFuncionalidad;
		
	// ROL
	private Integer cveRol;
	private String nomRol;
	
	// SUBROL
	private Integer cveSubrol;
	private String nomSubrol;
}