package mx.gob.imss.cit.sarp.oauth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4588168413400272478L;

	public CustomOauthException(String msg) {
        super(msg);
    }
}