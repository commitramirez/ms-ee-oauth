package mx.gob.imss.cit.sarp.oauth.service;

import mx.gob.imss.cit.sarp.oauth.model.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByNomCuentaMetro(String cveCorreo);	
	public void setPasswordAux(String passwordAux);
	public void setCveSistema(Integer idSistema);
	public Integer getCveSistema();

}
