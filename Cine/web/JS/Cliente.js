
var url = "http://localhost:8080/Cine/";


function register() {
    console.log("REGISTER");
    cliente = {
        nombre: $("#nombre").val(),
        apellidos: $("#apellidos").val(),
        numero_cuenta: $("#numero_cuenta").val(),
        //usuario: {
            id: $("#idR").val(),
            password: $("#passwordR").val(),
            type: "CLIENTE"
        //}
    };
    usuario = {
        id: "DEFAULT",
        password: "DEFAULT",
        type: "DEFAULT"
    };
    console.log(cliente);
    console.log(usuario);
    let request = new Request(url + 'api/usuario/register', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(cliente)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#loginDialog #errorDiv"));
            return false;
        }
        usuario = await response.json();
        sessionStorage.setItem('Usuario', JSON.stringify(usuario));
        $('#modalRegistro').modal('hide');
        
        switch (cliente.type) {
            case 'ADMINISTRATOR':
                console.log("LOGIN ADMINISTRATOR"); //document.location = url+"listado.html"; 
                break;
            case 'CLIENTE':
                showClientOptions();
                $("#modalButacas").modal("hide");
                $("#modalHorarios").modal("hide");
                console.log("LOGIN REGISTER CLIENTE"); //document.location = url+"about.html"; 
                break;
        }
    })();
    $('#idR').val('');
    $('#nombre').val('');
    $('#apellidos').val('');
    $('#numero_cuenta').val('');
    $('#passwordR').val('');
}
function errorMessage(status, place) {
    switch (status) {
        case 404:
            error = "Registro no encontrado";
            break;
        case 403:
        case 405:
            error = "Usuario no autorizado";
            break;
        case 406:
        case 405:
            error = "Usuario ya existe";
            break;
        case 500:
            error = "Usuario no existe";
            break;
    }
    ;
    place.html('<div class="alert alert-danger fade show">' +
            '<button type="button" class="close" data-dismiss="alert">' +
            '&times;</button><h4 class="alert-heading">Error!</h4>' + error + '</div>');
    return;
}

 var listadoTU=[];
function fetchTUsuarios(){
    
     listadoTU=[];
    $("#tablaTicktesUsuario").empty();
    usuario = JSON.parse(sessionStorage.getItem('Usuario'));
    
let request = new Request(url + 'api/usuario/'+usuario.id+'/ticketsListado', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#modalTicketsUsuario #errorUT"));
            return;
        }
        listadoTU = await response.json();
        leerTicketsUsuario();
        //$("#buscarT").val('');
    })();
    
}



function leerTicketsUsuario(){
    var lista= $("#tablaTicktesUsuario");
    listadoTU.forEach((item) => {
        var id =item.id;
        var n = item.nombre;
        var a = item.apellido;
        var s = item.sala;
        var p = item.pelicula;
        var f = item.fecha;
        var h = item.hora;
        var as = item.asiento;

        var tr = $("<div>Ticket Numero: "+id+" </div>");
        var tr1 = $("<div>Cliente: "+n+" "+a+" </div>");
        var tr2 = $("<div>Sala: "+s+" </div>");
        var tr3 = $("<div>Pelicula: "+p+" </div>");
        var tr4 = $("<div>Asiento: "+as+" </div>");
        var tr5 = $("<div>Fecha de la funcion: "+f+" </div>");
        var tr6 = $("<div>Hora de la Funcion: "+h+" </div>");
        var l2 = $("<div>-----------------------------------------------------</div>");
        
        lista.append(tr);
        lista.append(tr1);
        lista.append(tr2);
        lista.append(tr3);
        lista.append(tr4);
        lista.append(tr5);
        lista.append(tr6);
        lista.append(l2);
        
        $('#modalTicketsUsuario').modal('show');
        
    });
}


function load() {
     
        $("#register").click(register);
        $("#nav-compras-btn").click(fetchTUsuarios);
        console.log("LOAD CLIENTE");
} 

$(load);

