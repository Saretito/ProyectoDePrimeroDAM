/**
 * 
 */
window.onload = function(){
		llamadalistar();
	}
	// al ejecutarse la función obtendré una variable con el formato JSON
	function llamadalistar(){
		
		let xhr = new XMLHttpRequest(); //ejecuta cada estado de la conexion, la ultima es la 4
		let resultados; //resultados es un array de mis datos
		xhr.onreadystatechange = function(){
				if(xhr.readyState===4){
					if(xhr.status === 200){ //verifica que no hay mensaje distinto a 200
						try {
							resultados = JSON.parse(xhr.responseText); //parsear el texto que me envia java en JSON 
						mostrar(resultados);
						}catch (e){
							
							}
					}
				}
		};
		
		xhr.open("GET", "SListar" , true); //get o post, nombreServlet
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.send();
		//TRUE solicitud asíncrona, el navegador no esperará a que se complete la solicitud antes de continuar ejecutando el resto del código JavaScript.
	}
	// función que recorre el array y me lo imprime en el html.

	function mostrar(resultados){
		
		let html = "";
		
		for(let i=0; i<resultados.length; i++){
			
			//html += resultados[i].idNota +"<br>"+ resultados[i].titulo+"<br>"+ resultados[i].contenido +"<br>";
		html += "<div class='nota'>";
        html += "<h2 class='tit'>" + resultados[i].titulo + "</h2>";
        html += "<p class='cont'>" + resultados[i].contenido + "</p>";
        // lo suyo es hacer el editar en otro servlet
      //  html += "<button class='botonedit'><a href='nuevanota.html?Id_nota="+resultados[i].id_nota+"'>Editar</a></button>"; 
          
          html += "<button class='botonedit' onclick='llamadaeditar(" + resultados[i].id_nota + ")'>Editar</button>";	
          html += "<button id='botonborrar' onclick='llamadaborrar("+resultados[i].id_nota+")'>Borrar</button>";	
		  html += "</div>";
        
		}
		
		document.getElementById("listado").innerHTML = html;
	}

	// Función que realiza una llamada al servlet SEditar cuando se presiona el botón de edición
function llamadaeditar(Id_nota) {
    let xhr = new XMLHttpRequest(); // Crear una nueva instancia de XMLHttpRequest

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) { // Verificar si la solicitud está completa
            if (xhr.status === 200) { // Verificar si la respuesta es exitosa
                try {
                    let resultado = JSON.parse(xhr.responseText); // Parsear la respuesta como JSON
                    console.log(resultado); // Imprimir el resultado en la consola para depuración
                } catch (e) {
                    console.error("Error al parsear la respuesta JSON: ", e); // Manejo de errores en caso de fallo al parsear
                }
            } else {
                console.error("Error en la solicitud: ", xhr.status, xhr.statusText); // Manejo de errores en caso de fallo en la solicitud
            }
        }
    };

    xhr.open("GET", "SListar", true); // Abre una nueva solicitud GET al servlet SEditar con el Id_nota correspondiente
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(); // Enviar la solicitud
    window.location.href = "editarnota.html?Id_nota=" + Id_nota; // Redirige a editarnota.html con el parámetro Id_nota
}

/////////////////////////////////////
    
	
function llamadaborrar(id_nota) {
    let estasseguro = confirm("¿Estás seguro de que deseas borrar esta nota?");
    let id = id_nota
    if (estasseguro) {
	
        borrar(id);
        console.log(id);
    } else {
        alert("No se ha borrado");
    }
	}
	function borrar(id){
	let xhr = new XMLHttpRequest(); // Ejecuta cada estado de la conexión, la última es la 4
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) { // Verifica que no hay mensaje distinto a 200
                try {
                    console.log(id);
                    llamadalistar(); // Refresca la lista después de borrar
                } catch (e) {
                    console.error('Error borrando', e);
                }
            } else {
                console.error('Error:', xhr.statusText);
            }
        }
    };

    xhr.open("GET", "SBorrar?Id_nota=" + id, true); // get o post, nombreServlet y el id
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
    console.log(id);
}
	
	///////////////////////////////////
	function ira(){
		    window.location.href = "editarnota.html?Id_nota=" + Id_nota; // Redirige a editarnota.html con el parámetro Id_nota

	}