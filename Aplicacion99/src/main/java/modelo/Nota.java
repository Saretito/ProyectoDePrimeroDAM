package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoNota;

/**
 * Clase Nota
 * Esta clase define objetos que tienen un id de tipo entero, un titulo de tipo texto y un contenido de tipo texto.
 * @version v 1.0
 * @author Sara Hevia
 */

public class Nota {
/**
 * Id de la nota tipo entero.
 */
	private int id_nota;
/**
 * Titulo de la nota tipo texto.
 */
	private String titulo;
/**
 * Contenido de la nota tipo texto.
 */
	private String contenido;
	
	/**
	 * Constructor para generar un objeto vacio de tipo nota
	 */
	
	public Nota() {
		super();
		// TODO Auto-generated constructor stub
	}

/**
 * Constructor para la creacion del objeto desde el formulario	
 * @param titulo Atributo de texto para el titulo de la nota
 * @param contenido Atributo de texto para el contenido de la nota
 */
	public Nota(String titulo, String contenido) {
		super();
		this.titulo = titulo;
		this.contenido = contenido;
	
	}
/**
 * Constructor para la creacion del objeto desde la base de datos
 * @param id_nota Atributo de tipo entero para el id de la nota
 * @param titulo Atributo de tipo texto para el titulo de la nota
 * @param contenido Atributo de texto para el contenido de la nota
 */

	public Nota(int id_nota, String titulo, String contenido) {
		super();
		this.id_nota = id_nota;
		this.titulo = titulo;
		this.contenido = contenido;
		
	}

/**
 * Metodo de recuperacion del id del objeto
 * @return retorna el id en tipo entero
 */

	public int getId_nota() {
		return id_nota;
	}


/**
 * Metodo de insercion del id en el objeto
 * @param id_nota es el id en tipo entero
 */
	public void setId_nota(int id_nota) {
		this.id_nota = id_nota;
	}

/**
 * Metodo de recuperacion del titulo del objeto
 * @return retorna el titulo de tipo texto
 */

	public String getTitulo() {
		return titulo;
	}
/**
* Metodo de insercion del titulo del objeto
* @param titulo es el titulo en tipo texto
*/

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
/**
 * Metodo de recuperacion del contenido del objeto
 * @return retorna el contenido de tipo texto
 */

	public String getContenido() {
		return contenido;
	}

/**
 * Metodo de insercion del contenido en el objeto.
 * @param contenido es el contenido de tipo texto de la nota.
 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	/**
	 * Metodo insertar que llama al dao e inserta un objeto con informacion en la base de datos.
	 * @throws SQLException
	 */
	
	public void insertar() throws SQLException {
		DaoNota dao = new DaoNota();
		dao.insertar(this);
		System.out.println("Insertando");
	}

	
	/**
	 * Metodo que recibe un parametro y llama al dao para obtener una tupla que se identifica con ese parametro.
	 * @param Id_nota es el parametro que identifica la nota que nos va a devolver el metodo.
	 * @throws SQLException
	 */
	
	public void obtenerPorID(int Id_nota) throws SQLException {
		DaoNota dao = new DaoNota();
		Nota aux = dao.obtenerPorID(Id_nota);
		
		this.setId_nota(aux.getId_nota());
		this.setTitulo(aux.getTitulo());
		this.setContenido(aux.getContenido());	
		System.out.println(aux);
	}
	/**
	 * Metodo que me devuelve Json
	 * @return retorna el contenido del Json
	 */
	public String dameJson() {
		String json ="";
		Gson gson = new Gson();
		json = gson.toJson(this);
		return json;
	}
	/**
	 * Metodo que llama al dao para actualizar la informacion de la base de datos
	 * @throws SQLException
	 */
	public void actualizar() throws SQLException {
		DaoNota dao = new DaoNota();
		dao.actualizar(this);
		System.out.println("Actualizando");
	}
	/**
	 * Metodo que llama al dao para eliminar una nota en la base de datos que se identifica con un id.
	 * @param id identifica la nota a eliminar.
	 * @throws SQLException
	 */
	public void borrar(int id) throws SQLException {
		DaoNota dao = new DaoNota();
		dao.borrar(id);
		System.out.println("Borrando");
	}
/**
 * Metodo toString del objeto nota
 */

	@Override
	public String toString() {
		return "Nota [id_nota=" + id_nota + ", titulo=" + titulo + ", contenido=" + contenido + "]";
	}

	
	
}
