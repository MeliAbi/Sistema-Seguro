package sistSeguro;

import static org.junit.Assert.*;

import unlam.pb2.InvalidPassword;
import unlam.pb2.LoginError;
import unlam.pb2.UserNotFound;

public class Test {

	@org.junit.Test
	public void queSeCreeUnSistema() {
		Sistema nuevoSistema = new Sistema();
		//-//
		assertNotNull(nuevoSistema);
	}
	
	@org.junit.Test
	public void queSeCreenUsuarios() {
		Usuario basico = new UsuarioBasico("NombreDeUsuario1","Pass123");
		Usuario administrador = new UsuarioAdministrador("NombreDeUsuario2","pass123");
		//-//
		assertEquals(basico.getNombre(),"NombreDeUsuario1");
		assertEquals(administrador.getNombre(),"NombreDeUsuario2");
	}
	
	@org.junit.Test
	public void registrarUnUsuarioAdministradorEnElSistema() throws InvalidPassword {
		Sistema nuevoSistema = new Sistema();
		Usuario administrador = new UsuarioAdministrador("NombreDeUsuario2","Pass123%");
		//-//
		try {
			nuevoSistema.registrarUsuario(administrador);
		}catch(unlam.pb2.InvalidPassword e){
			throw new unlam.pb2.InvalidPassword(e.getMessage());
		}
		//-//
		assertNotNull(nuevoSistema.buscarUsuario("NombreDeUsuario2"));
	}
	
	@org.junit.Test
	public void registrarUnUsuarioBasicoEnElSistema() throws InvalidPassword {
		Sistema nuevoSistema = new Sistema();
		Usuario basico = new UsuarioBasico("NombreDeUsuario1","Pass123");
		//-//
		try {
			nuevoSistema.registrarUsuario(basico);
		}catch(unlam.pb2.InvalidPassword e){
			throw new unlam.pb2.InvalidPassword(e.getMessage());
		}
		//-//
		assertNotNull(nuevoSistema.buscarUsuario("NombreDeUsuario1"));
	}
	
	@org.junit.Test (expected = LoginError.class)
	public void queSeBloqueeUsuario() throws InvalidPassword, UserNotFound, LoginError{
		Sistema nuevoSistema = new Sistema();
		Usuario basico = new UsuarioBasico("NombreDeUsuario1","Pass123");
		Usuario aLoguear = new UsuarioBasico("NombreDeUsuario1","lass124");
		
		//-//
		
		try {
			nuevoSistema.registrarUsuario(basico);
		}catch(unlam.pb2.InvalidPassword e){
			throw new unlam.pb2.InvalidPassword(e.getMessage());
		}
		
		try {
			nuevoSistema.loguearUsuario(aLoguear);
			nuevoSistema.loguearUsuario(aLoguear);
			nuevoSistema.loguearUsuario(aLoguear);
		} catch (UserNotFound e) {
			throw new unlam.pb2.UserNotFound(e.getMessage());
		} catch (LoginError e) {
			throw new unlam.pb2.LoginError(e.getMessage());
		}

		//-//
		
		assertEquals(((Integer)3),((UsuarioBasico)basico).getIntentosLogin());
		assertEquals(true,((UsuarioBasico)basico).getEstaBloqueado());
	}
	
	@org.junit.Test(expected = UserNotFound.class)
	public void queNoSeEncuentreUsuario() throws InvalidPassword, UserNotFound, LoginError {
		Sistema nuevoSistema = new Sistema();
		Usuario administrador = new UsuarioAdministrador("NombreDeUsuario2","Pass123%");
		Usuario aLoguear = new UsuarioAdministrador("NombreDeUsuario4","rass113%");
		
		//-//
		
		try {
			nuevoSistema.registrarUsuario(administrador);
		}catch(unlam.pb2.InvalidPassword e){
			throw new unlam.pb2.InvalidPassword(e.getMessage());
		}
		
		try {
			nuevoSistema.loguearUsuario(aLoguear);
		} catch (UserNotFound e) {
			throw new unlam.pb2.UserNotFound(e.getMessage());
		} catch (LoginError e) {
			throw new unlam.pb2.LoginError(e.getMessage());
		}
		
		//-//
		
		assertNull(nuevoSistema.buscarUsuario("NombreDeUsuario4"));
	}
	
	@org.junit.Test
	public void queSeLogueeExitosamenteLosUsuarios() throws InvalidPassword, UserNotFound, LoginError {
		Sistema nuevoSistema = new Sistema();
		Usuario administrador = new UsuarioAdministrador("NombreDeUsuario2","Pass123%");
		Usuario basico = new UsuarioBasico("NombreDeUsuario1","Pass123");
		
		//-//
		
		try {
			nuevoSistema.registrarUsuario(administrador);
			nuevoSistema.registrarUsuario(basico);
		}catch(unlam.pb2.InvalidPassword e){
			throw new unlam.pb2.InvalidPassword(e.getMessage());
		}
		
		try {
			assertEquals(true,nuevoSistema.loguearUsuario(administrador));
			assertEquals(true,nuevoSistema.loguearUsuario(basico));
		} catch (UserNotFound e) {
			throw new unlam.pb2.UserNotFound(e.getMessage());
		} catch (LoginError e) {
			throw new unlam.pb2.LoginError(e.getMessage());
		}
		
	}
	
	@org.junit.Test
	public void queSeElimineUsuario() throws InvalidPassword, unlam.pb2.ClassCastException{
		Sistema nuevoSistema = new Sistema();
		Usuario basico = new UsuarioBasico("NombreDeUsuario1","Pass123");
		
		//-//
		
		try {
			nuevoSistema.registrarUsuario(basico);
		}catch(unlam.pb2.InvalidPassword e){
			throw new unlam.pb2.InvalidPassword(e.getMessage());
		}
		
		//-//
		
		try {
			assertEquals(true,nuevoSistema.eliminarUsuario(basico));
		} catch (ClassCastException e) {
			throw new unlam.pb2.ClassCastException(e.getMessage());
		} 
		
	}
	
	@org.junit.Test (expected = unlam.pb2.ClassCastException.class)
	public void queNoSeElimineUsuario() throws InvalidPassword, unlam.pb2.ClassCastException {
		Sistema nuevoSistema = new Sistema();
		Usuario administrador = new UsuarioAdministrador("NombreDeUsuario2","Pass123%");
		
		//-//
		
		try {
			nuevoSistema.registrarUsuario(administrador);
		}catch(unlam.pb2.InvalidPassword e){
			throw new unlam.pb2.InvalidPassword(e.getMessage());
		}
		
		//-//
		
		try {
			assertEquals(true,nuevoSistema.eliminarUsuario(administrador));
		} catch (unlam.pb2.ClassCastException e) {
			throw new unlam.pb2.ClassCastException(e.getMessage());
		} 
		
	}
	

}
