package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Nota;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class SEditar
 */
public class SEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SEditar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		int Id_nota = Integer.parseInt(request.getParameter("Id_nota"));
	//	int opcion = Integer.parseInt(request.getParameter("op"));
		System.out.println("recibido Id_nota:" + Id_nota);
		
			//logica edicion
			Nota ne = new Nota();
			try {
				ne.obtenerPorID(Id_nota);
//NO SE PUEDE REDIRECCIONAR Y MANDAR JSON. DECIDE//response.sendRedirect("nuevanota.html");

				//creo variable tipo printWriter, cuando reciba respuesta, imprimelo.
				PrintWriter out = response.getWriter();
				String json = ne.dameJson();
				out.print(ne.dameJson());
				System.out.println("hola??");
				System.out.println(ne.dameJson());
				System.out.println(ne.toString());
				out.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
	            System.out.println("Error al obtener la nota de la base de datos: " + e.getMessage());

				e.printStackTrace();
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
