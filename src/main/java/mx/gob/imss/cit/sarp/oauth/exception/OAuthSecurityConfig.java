package mx.gob.imss.cit.sarp.oauth.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuthSecurityConfig extends AuthorizationServerConfigurerAdapter {
    @SuppressWarnings("unchecked")
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //for custom
        endpoints.exceptionTranslator(new MyWebResponseExceptionTranslator());
    }
}