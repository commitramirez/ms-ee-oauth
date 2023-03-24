package mx.gob.imss.cit.sarp.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import mx.gob.imss.cit.sarp.oauth.exception.MyAccessDeniedHandler;
import mx.gob.imss.cit.sarp.oauth.exception.MyAuthenticationEntryPoint;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure (HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.POST,"*").permitAll();       
    }
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(new MyAuthenticationEntryPoint());
        resources.accessDeniedHandler(new MyAccessDeniedHandler());
    }
}