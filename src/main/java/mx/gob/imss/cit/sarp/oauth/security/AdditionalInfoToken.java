package mx.gob.imss.cit.sarp.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import mx.gob.imss.cit.sarp.oauth.model.entity.Usuario;
import mx.gob.imss.cit.sarp.oauth.service.IUsuarioService;

@Component
public class AdditionalInfoToken implements TokenEnhancer {
	
	@Autowired
	IUsuarioService usuarioService;
	
	@Value("${sarp.funcionalidad}")
	private String url;
	
	//String url = "http://localhost:9097/mssarp-funcionalidad/v1/matriz/{correoUsuario}/{idSistema}";
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			Usuario usuario = usuarioService.findByNomCuentaMetro(authentication.getName());
			Map<String,Object> uriVariables = new HashMap<String,Object>();
			
			uriVariables.put("correoUsuario", usuario.getCveCorreo());
			uriVariables.put("idSistema", usuarioService.getCveSistema());
			
			//FuncionalidadesMatriz[] funcionalidades = restTemplate.postForObject("http://localhost:9097/mssarp-funcionalidad/v1/matriz/funciones", request, FuncionalidadesMatriz[].class);
			mx.gob.imss.cit.sarp.oauth.model.dto.Usuario[] funcionalidades = restTemplate.getForObject(url, mx.gob.imss.cit.sarp.oauth.model.dto.Usuario[].class, uriVariables);
			
			info.put("funcionalidades", funcionalidades);
			
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		}
		catch (RestClientException e) {
			e.printStackTrace();		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return accessToken;
	}

}
