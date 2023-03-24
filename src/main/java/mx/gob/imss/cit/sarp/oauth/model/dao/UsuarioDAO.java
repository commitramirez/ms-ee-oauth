package mx.gob.imss.cit.sarp.oauth.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.imss.cit.sarp.oauth.model.entity.IUsuario;
import mx.gob.imss.cit.sarp.oauth.model.entity.Usuario;

@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {
	
	public Usuario findByCveCorreo(String cveCorreo);
	
	@Transactional
	@Query(value= " SELECT U.CVE_CORREO,US.CVE_TIPO_AUTENTICACION \r\n"
			+ "FROM SART_USUARIO U\r\n"
			+ "JOIN SART_USUARIO_SISTEMA US\r\n"
			+ "ON U.CVE_USUARIO=US.CVE_USUARIO\r\n"
			+ "WHERE US.NOM_USUARIO=:nomCuentaMetro \r\n"
			+ "AND US.CVE_SISTEMA=:idSistema \r\n"
			+ "LIMIT 1 " , nativeQuery = true)	
	public IUsuario findByNomCuentaMetro(String nomCuentaMetro, Integer idSistema);

}
