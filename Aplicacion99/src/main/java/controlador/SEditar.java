
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
	 * Metodo GET que maneja los datos que llegan del boton del formulario(id) y solicita los demás datos de su tupla a
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		int Id_nota = Integer.parseInt(request.getParameter("Id_nota"));
	//	int opcion = Integer.parseInt(request.getParameter("op"));
		System.out.println("recibido SEditar Id_nota:" + Id_nota);
		
			Nota ne = new Nota();
			try {
				ne.obtenerPorID(Id_nota);
//NO SE PUEDE REDIRECCIONAR Y MANDAR JSON. DECIDE//response.sendRedirect("nuevanota.html");

				//creo variable tipo printWriter, cuando reciba respuesta, imprimelo.
				PrintWriter out = response.getWriter();
				String json = ne.dameJson();
				out.print(ne.dameJson());
				System.out.println("Servlet Editar imprime:"+ne.toString());
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

	
		String titulo = request.getParameter("escribetitulo");
		String contenido = request.getParameter("escribenota");
		String ide = request.getParameter("Id_nota");
		System.out.println(contenido);
		System.out.println(titulo);

		System.out.println(ide);

		int id = Integer.parseInt(ide);
		
		
			Nota ne = new Nota(id, titulo, contenido);
			try {
				ne.obtenerPorID(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		Nota nee = new Nota(id, titulo, contenido);
		try {
			nee.actualizar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		response.sendRedirect("listar.html");
	}
}