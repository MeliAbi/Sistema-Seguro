package sistSeguro;

public class UsuarioBasico extends Usuario implements Bloqueable, Eliminable{
	
	private Integer intentosLogin=0;
	private Boolean estaBloqueado=false;
	private Boolean fueEliminado=false;

	public UsuarioBasico(String nombre, String pass) {
		super(nombre,pass);
	}

	@Override
	public void eliminarUsuario(Usuario user) {
		fueEliminado=true;
	}

	@Override
	public Boolean bloquearUsuario() {
		if(intentosLogin >= 3 && estaBloqueado==false) {
			estaBloqueado=true;
			reiniciarIntentosLogin();
			return true;
		}
		return false;
	}
	
	public void aumentarIntentosLogin() {
		intentosLogin++;
	}
	
	public void reiniciarIntentosLogin() {
		intentosLogin=0;
	}
	
	public Integer getIntentosLogin() {
		return intentosLogin;
	}
	
	public Boolean getEstaBloqueado() {
		return estaBloqueado;
	}
	
	public Boolean getFueEliminado() {
		return fueEliminado;
	}
	
}
