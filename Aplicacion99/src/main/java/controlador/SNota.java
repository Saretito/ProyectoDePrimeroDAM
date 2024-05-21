package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Nota;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class SNota
 */
public class SNota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SNota() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */ 
/*El método doPost del Servlet es donde voy a guardar ese dato que recibo del formulario mediante la instrucción request.getParameter(“”). Y dentro de sus comillas, introducimos el name del campo del html deseado.
Para recogerlo creamos una variable de tipo String y la igualamos a la instrucción que recoge los datos. 
Seguidamente, le pido al Servlet que me imprima por la consola esa variable, por si en el caso de que tenga errores en mi código, saber hasta dónde sí funciona.Porque si imprime la variable es que la he obtenido correctamente del html.
*/
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tituloN = request.getParameter("escribetitulo");
		String contenidoN = request.getParameter("escribenota");
	
		
// creo mi objeto Nota con el constructor adecuado y en el mismo orden que en el constructor.

		Nota n1 = new Nota(tituloN, contenidoN);	
		System.out.println( n1.toString());
		try {
//llamo al método insertar desde el objeto.
			n1.insertar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al insertar");
		} 
		System.out.println("Ahora debería redireccionar");
		response.sendRedirect("listar.html");
	}

}
