package modelo;

import java.sql.SQLException;

import dao.DaoUsuario;

public class Usuarioo {

	private int id_usuario;
	private String username;
	private String mail;
	private int permiso;
	/**
	 * Constructor para generar un objeto vacio de tipo usuario
	 */
	public Usuarioo() {}
	/**
	 * Constructor para la creacion del objeto desde el formulario
	 * @param username Atributo de texto para el nombre de usuario
	 * @param mail Atributo de texto para el email del usuario
	 * @param permiso Atributo de 
	 */
	public Usuarioo( String username, String mail, int permiso) {
		super();
		this.username = username;
		this.mail = mail;
		this.permiso = permiso;
		
		
	}

	public Usuarioo(int id_usuario, String username, String mail, int permiso) {
		super();
		this.id_usuario = id_usuario;
		this.username = username;
		this.mail = mail;
		this.permiso = permiso;
	}
/**
 * Metodo de insercion del id en el objeto
 * @return retorna el id en tipo entero
 */
	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getPermiso() {
		return permiso;
	}

	public void setPermiso(int permiso) {
		this.permiso = permiso;
	}

	@Override
	public String toString() {
		return "Usuarioo [id_usuario=" + id_usuario + ", username=" + username + ", mail=" + mail + ", permiso="
				+ permiso + "]";
	}

	public void insertarUsuario () throws SQLException {//el proceso de insertar pertenece al objeto usuario
		DaoUsuario dao = new DaoUsuario();				// y la llamada a insercion debe estar dentro del objeto usuario
		dao.insertarUsuario(this);						//el objeto usuario da orden de su inserción al objeto dao para introducirse a la bd
	}
}

