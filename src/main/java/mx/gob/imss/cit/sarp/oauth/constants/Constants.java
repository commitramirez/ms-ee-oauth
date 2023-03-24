package mx.gob.imss.cit.sarp.oauth.constants;

public class Constants {

	private Constants() {
		
	}
	
	//Exception
	public static final String ERROR_RESPONSE_FIND = "No existe informacion con ese identificador";
	
	// Roles de Usuario
	public static final String SUPERADMIN_STR = "Administrador";
	
	public static final int SUPERADMIN = 0;
	//public static final int ADMINISTRADOR = 1;
	public static final int COORDINADOR = 2;
	public static final int SUPERVISOR = 3;
	public static final int COLABORADOR = 4;
	
	// Estatus de tarea
	public static final int POR_REVISAR = 1;
	public static final int AUTORIZADA = 2;
	public static final int RECHAZADA = 3;
}
