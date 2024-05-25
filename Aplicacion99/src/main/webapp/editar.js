
  
 
//Funci�n para otener el valor de un parametro en el GET */
/*function getParameterByName(name) {
    let urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}
*/

function getParameterByName(name) {
    name = name.replace(/\[/g, "\\[").replace(/\]/g, "\\]"); // Escapar todos los corchetes en la cadena
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)");
    var results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}



function llamada(id){
    let xhr = new XMLHttpRequest(); //ejecuta cada estado de la conexión, la última es la 4
    let resultados; //resultados es un array de mis datos
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){ //verifica que no hay mensaje distinto a 200
                try {
                    resultados = JSON.parse(xhr.responseText); //parsear el texto que me envía Java en JSON 
					console.log(resultados);
                    pintarForm(resultados);
                } catch (e) {
                    console.error('Error parsing JSON:', e);
                }
            } else {
                console.error('Error:', xhr.statusText);
            }
        }
    };

    xhr.open("get", "SEditar?Id_nota=" + id, true); //get o post, nombreServlet y el id
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(id);
    console.log(id);
}

window.onload = function(){
let id = getParameterByName("Id_nota");
    llamada(id);
   
}


function pintarForm(resultados) {
	console.log(resultados);
    
        document.getElementById("Id_nota").value = resultados.id_nota;
        console.log("ID de la nota:", resultados.id_nota);

        document.getElementById("titnota").value = resultados.titulo;
        console.log("Título de la nota:", resultados.titulo);

        document.getElementById("contnota").value = resultados.contenido;
        console.log("Contenido de la nota:", resultados.contenido);
    
}