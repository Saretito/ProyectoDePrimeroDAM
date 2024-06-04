package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Nota;

public class DaoNota {


/**
*Atributo de tipo conexión que se instancia en null.
*Instrucción que haga que al instanciar el objeto, se conecte a la base de datos.
*/
	public static Connection con = null;
	public DaoNota() {
		this.con = DBConexion.getConexion();
	}
/* Método insertar, que no devuelve nada y recibe un objeto nota, donde incluiré la sentencia sql, y mediante las instrucciones pertinentes meta en las columnas de mi tabla sql los atributos del objeto. Este proceso se ejecutará cada vez que se
cree un objeto. (no olvidar poner el método insertar en la clase-Nota).
 */
	/**
	 * Metodo que inserta, no devuelve nada y recibe un objeto nota donde va la query SQL.
	 * Se ejecutara cada vez que se cree un objeto Nota
	 * @param n El parametro n es un objeto, que será el que se inserte en la BD
	 * @throws SQLException
	 */
	public void insertar(Nota n) throws SQLException {
// Sentencia SQL para insertar una nota

		String sql = "INSERT INTO notas (titulo, contenido) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, n.getTitulo());
		ps.setString(2, n.getContenido());
// Ejecución de la sentencia SQL
		int filas = ps.executeUpdate();
		ps.close();
		System.out.println("DAO: INSERTADO");
	}
	/**
	 * Metodo que devuelve informacion  de la BD segun el parametro ID.
	 * @param Id_nota El parametro Id es un atributo que identifica las tuplas.
	 * @return Devuelve un objeto nota, que tiene Id, y dos Strings que corresponden al titulo y contenido de la nota.
	 * @throws SQLException
	 */
	
	public Nota obtenerPorID(int Id_nota) throws SQLException {
		String sql = "SELECT * FROM notas WHERE Id_nota=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, Id_nota);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Nota ne = new Nota (rs.getInt(1), rs.getString(2), rs.getString(3));
		System.out.println("DAO : DEVOLVIENDO DATOS");
		return ne;
	}
	/**
	 * Metodo para actualizar la informacion de un objeto determinado por su id.
	 * @param id con este parametro es un atributo del objeto nota que las identifica. 
	 * @return Devuelve un objeto nota, que tiene Id y dos Strings, el titulo y el contenido. 
	 * @throws SQLException
	 */
	
	public void actualizar(Nota n) throws SQLException {
		String sql = "UPDATE notas SET titulo=?,contenido=? WHERE Id_nota=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, n.getTitulo());
		ps.setString(2, n.getContenido());
		ps.setInt(3, n.getId_nota());

// Ejecución de la sentencia SQL
		int filas = ps.executeUpdate();
		ps.close();
		System.out.println("DAO: ACTUALIZANDO");
	} 
	
	/**
	 * Metodo para borrar la informacion de un objeto determinado por su id.
	 * @param id Es el parametro que localiza la nota que identica el boton pulsado.
	 * @throws SQLException
	 */
	public void borrar(int id) throws SQLException {
		String sql = "DELETE FROM notas WHERE Id_nota=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);

		int filas = ps.executeUpdate();
		ps.close();
		System.out.println("DAO: BORRANDO");
	}
/*
Creo un método que llame a los datos con un SELECT a la base de datos. 
Será un método público que devuelve un ArrayList de los objetos (y puede recibir parámetros para filtrar la lista si por ejemplo en la zona resaltada metemos String titulo y modificamos la query de sql) que contiene:
CONTIENE: una query sql, preparedStatement que llama al método conector, ResultSet con executeQuery (ya no es executeUpdate como hacíamos para crear datos) . Para obtener una colección tipo ResultSet con todos los datos. Esto no es un objeto, es una lista bidimensional con los datos., ArrayList para pasarle los datos de ese ResultSet (inicializandolo a null). Comprobando antes que NO esté instanciado, para instanciarlo y rellenarlo con el método .add, mediante el constructor de mi objeto y el método que devuelve los datos. En este caso sí le voy a pedir el ID.
Por último, retornamos el ArrayList.

 */
	/**
	 * Metodo publico que llama a los datos de la BD y devuelve un Arraylist con los objetos.
	 * @return Devuelve el ArrayList relleno con los datos de la base de datos.
	 * @throws SQLException
	 */
	public ArrayList<Nota> listar( ) throws SQLException{
// Sentencia SQL para obtener todas las notas

		String sql = "SELECT * FROM notas";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet result = ps.executeQuery();
		ArrayList<Nota> notas = null;
		
// Comprobación para inicializar el ArrayList si aún no está instanciado
		while(result.next()) {
			if (notas == null) {
				notas = new ArrayList<Nota>();
			}
// Creación de objeto Nota y añadido al ArrayList
		notas.add(new Nota(result.getInt(1),result.getString(2), result.getString(3)));
		}
	return notas;
	}
/*
 * Para que Java convierta el ArrayList en un String con el formato Json, mediante una librería.
Creo un método publico que devuelve un String que listará.
Creamos un objeto gson con la librería que acabo de añadir.
El String txtJSON se rellena en el formato correcto con todos los datos del ArrayList mediante el método toJson. 
Convierto a Json lo que me devuelve el método listar. 
Devuelvo el Json que acabo de crear.

 */
	/**
	 * Metodo publico que convierte el ArrayList con los datos de la BD en un String con fotmato JSON
	 * @return Devuelve un String con los datos de la BD en formato JSON
	 * @throws SQLException
	 */
	public String listarJson() throws SQLException {
		String txtJSON = "";
		 Gson gson = new Gson();
		 txtJSON = gson.toJson(this.listar());
		 return txtJSON;
	}
	
}
