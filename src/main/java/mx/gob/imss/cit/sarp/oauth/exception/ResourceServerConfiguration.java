package mx.gob.imss.cit.sarp.oauth.exception;

import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


public  class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
        public void configure(ResourceServerSecurityConfigurer resources) {
            // format message
            resources.authenticationEntryPoint(new MyAuthenticationEntryPoint());
            resources.accessDeniedHandler(new MyAccessDeniedHandler());
        }
}