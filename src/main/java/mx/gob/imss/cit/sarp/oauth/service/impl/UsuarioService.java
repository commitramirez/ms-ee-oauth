package mx.gob.imss.cit.sarp.oauth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mx.gob.imss.cit.sarp.oauth.model.dao.UsuarioDAO;
import mx.gob.imss.cit.sarp.oauth.model.dto.FuncionalidadRequest;
import mx.gob.imss.cit.sarp.oauth.model.entity.IUsuario;
import mx.gob.imss.cit.sarp.oauth.model.entity.Usuario;
import mx.gob.imss.cit.sarp.oauth.security.SimpleLdapAuthentication;
import mx.gob.imss.cit.sarp.oauth.service.IUsuarioService;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {
	
	private Logger log = LoggerFactory.getLogger(UsuarioService.class);
	
	@Value("${ldap.prefixUserName}")
	private String prefixUserNameLdap;
	
	@Autowired
	private SimpleLdapAuthentication simpleLdapAuthentication;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	//@Autowired
	//private RolDAO rolDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;	
	
	public String passwordAux;
	public Integer idSistema;
	
	public FuncionalidadRequest funcionalidadRequest;
	
	@Override
	public void setPasswordAux(String passwordAux) {
		this.passwordAux = passwordAux;
	}
	
	@Override
	public UserDetails loadUserByUsername(String nomCuentaMetro) throws UsernameNotFoundException {
		
		IUsuario usuario = usuarioDAO.findByNomCuentaMetro(nomCuentaMetro,idSistema);
		
		if(usuario == null) {
			log.error("Error : '" +  nomCuentaMetro + "' no existe en el sistema");
			throw new UsernameNotFoundException("Error : '" +  nomCuentaMetro + "' no existe en el sistema");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();/*rolDAO.findRolByUsuarioAllWithOutFecBaja(usuario.getCveUsuario())
				.stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getNomNombre()))
				.peek(authority -> log.info("Role : " + authority.getAuthority()))
				.collect(Collectors.toList());*/
		
		/*if(usuario.getIndEsSuperAdmin())
			authorities.add(new SimpleGrantedAuthority(Constants.SUPERADMIN_STR));*/
		
		//Si es 2 valida por LDAP
		if(usuario.getCVE_TIPO_AUTENTICACION()==2) {
			if(!simpleLdapAuthentication.ldapAuthentication(prefixUserNameLdap + nomCuentaMetro, this.passwordAux))
				throw new UsernameNotFoundException("Error : '" +  nomCuentaMetro + "' no existe en el sistema en el LDAP");
						
		}
		
		return new CustomUser(usuario.getCVE_CORREO(), passwordEncoder.encode(this.passwordAux), true, true, true, true, authorities);
	}

	@Override
	public Usuario findByNomCuentaMetro(String nomCuentaMetro) {
		//return usuarioDAO.findByNomCuentaMetro(nomCuentaMetro);
		return usuarioDAO.findByCveCorreo(nomCuentaMetro);
	}

	@Override
	public void setCveSistema(Integer idSistema) {
		this.idSistema=idSistema;
		
	}

	@Override
	public Integer getCveSistema() {
		return this.idSistema;
		
	}
}
