package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * Metodo conector que proporciona la conexion a la base de datos
 */
public class DBConexion {
	 // URL de la conexion JDBC a la base de datos 
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/adnotationes";
	// Instancia de la conexión a la base de datos 
	public static Connection instance = null;
	/**
	 * Metodo que obtiene una conexion a la base de datos.
	 * @return Devuelve una conexion a la base de datos.
	 */
	public static Connection getConexion()  {
		
		if(instance == null) {
			
			//opcional parametros de conexion
			Properties props = new Properties();
			props.put("user", "root");
			props.put("password", "");
			props.put("charset", "UTF-8");
			try {
				instance = DriverManager.getConnection(JDBC_URL, props);
				System.out.println("CONECTADO ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return instance;
	}

	
}

