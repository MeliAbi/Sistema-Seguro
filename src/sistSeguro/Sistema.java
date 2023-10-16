package sistSeguro;

import java.util.Map;
import java.util.TreeMap;

import unlam.pb2.InvalidPassword;
import unlam.pb2.LoginError;

public class Sistema {
	private Map <String,Usuario> listaUsuarios;
	
	public Sistema() {
		this.listaUsuarios = new TreeMap<String,Usuario>();
	}
	
	public void agregarUsuario(Usuario a) {
		listaUsuarios.put(a.getNombre(), a);
	}
	
	public Usuario buscarUsuario(String usuario) {
		return listaUsuarios.get(usuario);
	}

	public void registrarUsuario(Usuario user) throws InvalidPassword {

		if(buscarUsuario(user.getNombre())==null && user.validarContraseña(user.getPass()))
		{
			agregarUsuario(user);
		}else {
			throw new unlam.pb2.InvalidPassword("La contraseña no es valida");	
		}
	}

	public Boolean loguearUsuario(Usuario user) throws unlam.pb2.UserNotFound, unlam.pb2.LoginError {
		Usuario local=buscarUsuario(user.getNombre());
		
		if(local!=null) {
			if(local instanceof UsuarioBasico) { // El Usuario se encuentra Bloqueado
				
				if (((UsuarioBasico) local).getEstaBloqueado()) {
		            throw new unlam.pb2.LoginError("El usuario está bloqueado.");
		            
		        } else {
		            if (local.getPass()==user.getPass()) { // Inicio de sesión exitoso
		            	((UsuarioBasico) local).reiniciarIntentosLogin();
		                return true;
		                
		            } else {
		            	((UsuarioBasico) local).aumentarIntentosLogin();
		            	
		                if (((UsuarioBasico) local).bloquearUsuario()) { // Se bloquea Usuario
		                    throw new unlam.pb2.LoginError("El usuario ha sido bloqueado debido a múltiples intentos fallidos.");
		                }
		                return false;
		            }
		        }
				
			}else {
				if (local.getPass()==user.getPass()) { // Inicio de sesión exitoso
		                return true;
		            } else {
		               throw new unlam.pb2.LoginError("Contraseña Incorrecta");
		            }
			}
			
		}else {
			throw new unlam.pb2.UserNotFound("Usuario invalido");
		}
	}
	
	public Boolean eliminarUsuario(Usuario user) throws unlam.pb2.ClassCastException{
		Usuario local=buscarUsuario(user.getNombre());
		
		if(local!=null) {
			if(local instanceof UsuarioBasico) {
				if(((UsuarioBasico)local).getFueEliminado()) {
					return false;
				}else {
					((UsuarioBasico)local).eliminarUsuario(local);
					return true;
				}
				
			}else {
				throw new unlam.pb2.ClassCastException("No es posibble eliminar un administrador");
			}
			
		}else {
			return false;
		}
		
		
	}

}
