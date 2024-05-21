package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;


import modelo.Usuarioo;

public class DaoUsuario {

	//genero atributo de tipo conexion y lo instancio en null
	public static Connection con = null;
	public DaoUsuario(){
		//nada mas se instancie la clase nota, ¡conectate a la BD!
		this.con = DBConexion.getConexion();
		
	}
	/**
	 * Metodo de insercion en la BD del objeto usuario
	 * @param u Objeto tipo usuario
	 * @throws SQLException 
	 */
	public void insertarUsuario(Usuarioo u) throws SQLException {
		String sql = "INSERT INTO usuarios (idusuarios,username,email,permiso) VALUES (?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql); 
		ps.setInt(1, u.getId_usuario());
		ps.setString(2, u.getUsername());
		ps.setString(3, u.getMail());
		ps.setInt(4, u.getPermiso());
		
		int filas = ps.executeUpdate();
		ps.close();

	}
	/**
	 * Matriz con datos
	 * @return
	 * @throws SQLException 
	 */
	//SIN FILTORS
	public ArrayList<Usuarioo> listar() throws SQLException{
		String sql ="SELECT * FROM usuarios";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		//buenas practicas
		//inicializo la coleccion
		ArrayList<Usuarioo>ls = null; // si está vacía la lleno
		while(rs.next()) { // recorre el array desde la segunda posicion hasta que encuentre el null
			if(ls==null ) {//si no se ha inicializado, lo inicializo
				ls = new ArrayList<Usuarioo>();
			}// relleno el array con los datos
			ls.add(new Usuarioo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
		}
		return ls;
		
	}
	//CON FILTROS 
	//ya no me devuelve un arraylist, me devuelve un usuario
	public Usuarioo listar(String permiso) throws SQLException{
		String sql ="SELECT * FROM usuarioo WHERE permiso=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, permiso);
		ResultSet rs = ps.executeQuery();
		
		//buenas practicas
		//inicializo la coleccion
		ArrayList<Usuarioo>ls = null; // si está vacía la lleno
		rs.next(); 
		Usuarioo p = new Usuarioo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
		
		return p;
		}
	
	
	//metodo que devuelve string tipo JSON
	public String listarJson() throws SQLException {
		String json="";
		//importo librería gson
		Gson gson = new Gson();
		//en la variable que acabo de crear, guarda lo que genere el objeto json 
		//con el metodo.tojson y que me convierta lo que me devuelve this.listar
		json = gson.toJson(this.listar());
		return json;
	}
}
