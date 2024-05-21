/**
 * 
 */
//Funci�n para otener el valor de un parametro en el GET */
function getParameterByName(name) {
    name = name.replace(/[[]/, "\[").replace(/[]]/, "\]");
    var regex = new RegExp("[\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/+/g, " "));
}

function llamada(id){
    let xhr = new XMLHttpRequest(); //ejecuta cada estado de la conexión, la última es la 4
    let resultados; //resultados es un array de mis datos
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status === 200){ //verifica que no hay mensaje distinto a 200
                try {
                    resultados = JSON.parse(xhr.responseText); //parsear el texto que me envía Java en JSON 
                    mostrar(resultados);
                } catch (e) {
                    console.error('Error parsing JSON:', e);
                }
            } else {
                console.error('Error:', xhr.statusText);
            }
        }
    };

    xhr.open("GET", "SEditar?id=" + id, true); //get o post, nombreServlet y el id
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
}


let id = getParameterByName("Id_nota");

window.onload = function(){
	let id = getParameterByName("Id_nota");

	llamada(id);
}
