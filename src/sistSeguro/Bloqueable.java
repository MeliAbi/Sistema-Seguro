package sistSeguro;

public interface Bloqueable {
	
	public Boolean bloquearUsuario();
	public void aumentarIntentosLogin();
	public void reiniciarIntentosLogin();

}
