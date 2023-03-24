package mx.gob.imss.cit.sarp.oauth.security;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.unboundid.ldap.sdk.migrate.ldapjdk.LDAPConnection;
import com.unboundid.ldap.sdk.migrate.ldapjdk.LDAPException;




@Service
public class SimpleLdapAuthentication {
	
	@Value("${ldap.url}")
	private String ldapURL;
	
	@Value("${ldap.base}")
	private String base;
	
	 private int ldapPort=3268;
     private int ldapVersion;
     private LDAPConnection lc;
     
     private String login;
     private String ldapHost = "172.16.0.31";
	
    @SuppressWarnings("unused")
	public boolean ldapAuthentication(String userName, String password) {
        /*String dn = "" + userName + "" + base;
        
        // Setup environment for authenticating
        Hashtable<String, String> environment = new Hashtable<String, String>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, ldapURL);
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");
        environment.put(Context.SECURITY_PRINCIPAL, dn);
        environment.put(Context.SECURITY_CREDENTIALS, password);

        try {
            DirContext authContext = new InitialDirContext(environment);
            // user is authenticated
            return true;
        } catch (AuthenticationException ex) {
            //ex.printStackTrace();
            // Authentication failed
        } catch (NamingException ex) {
            //ex.printStackTrace();
        }
        return false;*/
        
        
        boolean correct = false;
        String dnc = "CN=ususario.bitacora,OUCuentas de Servicio,DC=imss,DC=gob,DC=mx";
        String strPassword="31+4cor@_u53r*";

        login = "CN=ususario.bitacora,OUCuentas de Servicio,DC=imss,DC=gob,DC=mx";
        System.out.println("" + login);
        //ldapPort = LDAPConnection.DEFAULT_PORT;
        System.out.println("puerto: " + ldapPort);
        //ldapVersion = LDAPConnection.LDAP_V3;
        System.out.println("Vesion: " + ldapVersion);
        System.out.println("HOST: " + ldapHost);
        try {
             lc = new LDAPConnection();
             lc.connect(ldapHost, ldapPort);
             System.out.println("====Conectado al Servidor LDAP====");
             lc.bind(login, strPassword);
             System.out.println("Autenticado en el servidor....");
        } catch (Exception ex) {
             //Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null, ex);
        } 
        
        
        return true;
        
    }
}
