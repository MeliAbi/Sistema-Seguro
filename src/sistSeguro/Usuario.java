package sistSeguro;

public class Usuario{
	
	private String nombre="";
	private String pass;

	public Usuario(String nombre, String pass) {
		this.nombre=nombre;
		this.pass=pass;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPass() {
		return pass;
	}
	
	public Boolean validarContraseña(String pass) {
		Boolean tieneMayuscula = false;
        Boolean tieneMinuscula = false;
        Boolean tieneNumero = false;

        for (int i = 0; i < pass.length(); i++) {
            char c = pass.charAt(i);

            if (c >= 65 && c <= 90) {
                tieneMayuscula = true;
            } else if (c >= 97 && c <= 122) {
               tieneMinuscula  = true;
            } else if (c >= 48 && c <= 57) {
                tieneNumero = true;
            }
        }

        return tieneMayuscula && tieneMinuscula && tieneNumero;
	}

}
