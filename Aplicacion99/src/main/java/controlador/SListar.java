package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Nota;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoNota;


/**
 * Servlet implementation class SListar
 */
public class SListar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SListar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
/*
Este servlet que lista dará la orden al dao de que se conecte, recoja la información y la mande mediante Json a JavaScript para que las liste en el html.
Mediante el método doGet genero un objeto Dao, lo saco con PrintWriter.
Genero un objeto tipo PrintWriter 
Otro que llame al dao
Creo un String que liste todos los datos 
out.print
*/
		try {
			PrintWriter out = response.getWriter();

			DaoNota dao = new DaoNota();
			String resultados = dao.listarJson();
			System.out.println(resultados); //Imprime en el back
			out.print(resultados); //Imprime en el front
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en el servlet");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
