package mx.gob.imss.cit.sarp.oauth.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Value("${authorization.nameApp}")
	private String nameApp;
	
	@Value("${authorization.passwordApp}")
	private String passwordApp;
	
	@Value("${authorization.signingKey}")
	private String signingKey;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AdditionalInfoToken infoAdditionalInfoToken;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()")
		;
	}

	/**
	 * Se configuracion los cientes que consumiran los servicios, por ejemplo una apploca, una web
	 * o una app android o iOS
	 * inMemory o JDBC o cualquier tipo de almacenamiento
	 * withCliente es el identificador del cliente
	 * secret una contraseña, debe estar encriptada
	 * scopes el alcance de la aplicacion cliente (lectura/escritura)
	 * authorizedGrantTypes es como se hara el tipo de autenticacion, por ejemplo "password" (credenciales usuario y contraseña)
	 * , se puede usar el authorizationCode se usa un codigo de auth que entrega el backend algun codigo de autorizacion y redireccionamiento
	 * , o implicit no se usa realmente un codigo de autorizacion se usa normalmente en app publicas.
	 * accessTokenValiditySeconds se obtiene un nuevo token justo antes de que caduque el token actual
	 * refreshTokenValiditySeconds tiempo del refresh token
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient(nameApp)
		.secret(passwordEncoder.encode(passwordApp))
		.scopes("read", "write")
		.authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(7200)
		.refreshTokenValiditySeconds(7200)
		;
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdditionalInfoToken, accessTokenConverter()));
		
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter())
		.tokenEnhancer(tokenEnhancerChain);
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		//tokenConverter.setSigningKey(signingKey);
		tokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA);
		tokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA);
		return tokenConverter;
	}
}