package mx.gob.imss.cit.sarp.oauth.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="\"SART_USUARIO\"")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CVE_USUARIO")
	private Long cveUsuario;
	
	@Size(max=40)
	@NotNull
	@NotEmpty
	@Column(name = "NOM_NOMBRE")
	private String nomNombre; 
	
	@Size(max=40)
	@NotNull
	@NotEmpty
	@Column(name = "NOM_APELLIDOPATERNO")
	private String nomApellidoPaterno;
	
	@Size(max=40)
	@NotNull
	@NotEmpty
	@Column(name = "NOM_APELLIDOMATERNO")
	private String nomApellidoMaterno;
	
	@Email
	@Size(max=40)
	@NotNull
	@NotEmpty
	@Column(name = "CVE_CORREO")
	private String cveCorreo;
	
	@Column(name = "CVE_CONTRASENA")
	private Date cveContrasena;
		
	@Column(name = "FEC_EXPIRA")
	private Date fecExpira;
	
	@Column(name = "FEC_ALTA")
	private Date fecAlta;
	
	@Column(name = "FEC_ACTUALIZACION")
	private Date fecActualizacion;
	
	@Column(name = "FEC_BAJA")
	private Date fecBaja;
	
	/*
	  * Regresar true cuando a fec_expira seá mayor a la fecha actual y cuando fecha baja sea nulo
	*/
	public boolean isEnable() {
		//if(fecBaja!=null || fecExpira.getTime()<new Date().getTime()) return false;
		if(fecBaja!=null) return false;
		return true;
	}
	
	public String getNamePacked() {
		String namePackaged = "";
		if(nomNombre != null && nomNombre.length() > 0)
			namePackaged = nomNombre.substring(0, 1);
		if(nomApellidoPaterno != null && nomApellidoPaterno.length() > 0)
			namePackaged += nomApellidoPaterno.substring(0, 1);
		
		return namePackaged.toUpperCase();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8337640745343203538L;
}
