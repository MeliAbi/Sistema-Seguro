package sistSeguro;

public class UsuarioAdministrador extends Usuario{

	public UsuarioAdministrador(String nombre, String pass) {
		super(nombre, pass);
	}
	
	public Boolean validarContraseña(String pass) {
		Boolean tieneMayuscula = false;
        Boolean tieneMinuscula = false;
        Boolean tieneNumero = false;
        Boolean tieneDigEspecial = false;

        int contadorConsecutivos = 1;

        for (int i = 0; i < pass.length(); i++) {
            char c = pass.charAt(i);

            if (c >= 65 && c <= 90) {
            	tieneMayuscula = true;
            } else if (c >= 97 && c <= 122) {
            	tieneMinuscula = true;
            } else if (c >= 48 && c <= 57) {
            	tieneNumero = true;
            } else if ("@#$%^&+=".indexOf(c) != -1) {
            	tieneDigEspecial = true;
            }

            if (i > 0) {
                char prevChar = pass.charAt(i - 1);
                if (c - prevChar == 1) {
                	contadorConsecutivos++;
                    if (contadorConsecutivos > 3) {
                        return false;
                    }
                } else {
                	contadorConsecutivos = 1;
                }
            }
        }

        return tieneMayuscula && tieneMinuscula && tieneNumero && tieneDigEspecial;
	}

}
