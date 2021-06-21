
var url = "http://localhost:8080/Cine/";
var peliculas = [];
var salas = [];
var ticketHeaders = {
    id: 'Ticket #',
    nombre: 'Nombre cliente',
    apellido: 'Apellido cliente',
    sala: 'Pelicula',
    pelicula: 'Sala',
    fecha: 'Fecha',
    hora: 'Hora',
    asiento: 'Asientos'
};
var ticketKeys = ['id', 'nombre', 'apellido','pelicula', 'sala', 'fecha', 'hora', 'asiento'];


//-----------------------------------------pelicula------------------------------------------------------------------------------------------------------------------------------------------------------------
var pelicula = {id: "", nombre: "", duracion: "", descripcion: "", precio: ""};
//var image = {base64Image :"Images/spiderman.jpg"};

function loadPelicula() {
    pelicula = Object.fromEntries((new FormData($("#forPeli").get(0))).entries());
}
function resetPelicula() {
    pelicula = {id: "", nombre: "", duracion: "", descripcion: "", precio: ""};
    $('#nombrepeli').val('');
    $('#descripeli').val('');
    $('#duracionpeli').val('');
    $('#preciopeli').val('');

}
function resetSala() {
    sala = {sala: "", asientos: ""};
}
function Pelicula() {
    loadPelicula();
    let request = new Request(url + 'api/admin/pelicula', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(pelicula)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#loginDialog #errorDiv"));
            return;
        }
        resetPelicula();
        pelicula = await response.json();
        $('#modalPelicula').modal('hide');
        addImagen();
        resetPelicula();
        fetchAndListMoviesAdmin();

    })();

}

function loadMoviesListingAdmin() { //Dentro de este metodo deberia ir el request al API para solicitar las peliculas de la cartelera

    var listaPeliculasContainer = $("#movie-cards-container");
    resetMoviesContainer();

    peliculas.forEach((item) => {
        var movieID = item.id;
        var movieName = item.nombre;
        var movieDuration = item.duracion;
        var movieDescripcion = item.descripcion;//"data:image/jpg;base64,${image.base64Image}"
        var movieStatus = item.estado;
        var buttonDelete = `<button id="admin-movie-action" type="button" class="btn btn-danger movie-action">Borrar</button>`;
        var buttonAdd = `<button id="admin-movie-action" type="button" class="btn btn-success movie-action">Agregar</button>`;
        var actionButton = (movieStatus === "0") ? buttonAdd : buttonDelete;
        var newListItem = $("<div />");
        var adminCard = `<div class="col">
                        <div class="card shadow-sm">
                            
                            <img  src="` + url + `api/cartelera/` + movieID + `/imagen" class="card-img-top" alt="">
                            <div class="card-body">
                                <p class="card-text">`
                + movieDescripcion +
                `</p>
                                <div class="d-flex justify-content-between align-items-center" >
                                    <div class="btn-group">`

                + actionButton +
                `</div>
                                    <small class="text-muted">` + movieDuration + `</small>
                                </div>
                            </div>
                        </div>
                    </div>`;
        newListItem.html(adminCard);

        var btn = newListItem.find("#admin-movie-action");
        if (movieStatus === "1") {
            btn.on("click", () => {
                loadDeleteMovieModal(movieID, movieName);
            });
        } else {
            btn.on("click", () => {
                loadActivateMovieModal(movieID, movieName);
            });
        }
        btn.hide();
        newListItem.on("mouseover", () => {
            btn.show();
        });
        newListItem.on("mouseleave", () => {
            btn.hide();
        });
        listaPeliculasContainer.append(newListItem);
    });
}
function fetchAndListMoviesAdmin() {
    peliculas = [];
    resetMoviesContainer();
    let request = new Request(url + 'api/admin/peliculas', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        peliculas = await response.json();
        loadMoviesListingAdmin();

    })();
}


function loadDeleteMovieModal(idPelicula, movieName) { //Desactiva la pelicula para que esta no aparezca en cartelera
    $('#modalDeleteMovie').find('.modal-body').empty();//Borra el body del modal
    var modal = $('#modalDeleteMovie');
    var mensaje = "<p>Esta seguro que desea borrar la pelicula " + movieName + "?</p>";
    modal.find('.modal-body').append(mensaje); //Busca el modal-body y agrega el mensaje
    $('#modalDeleteMovie').modal('show');

    //Cargar listener para boton de aceptar
    var btnAceptar = modal.find('#action-movie-aceptar-btn');
    btnAceptar.on("click", () => {
        deleteMovie(idPelicula);
        btnAceptar.off("click");
        modal.modal("hide");
    });
}

function deleteMovie(id) {
    //Todo... ejecutar request para borrar pelicula
    let request = new Request(url + 'api/admin/borrar/' + id, {method: 'DELETE', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        //resultado = await response.json();
        fetchAndListMoviesAdmin();
    })();
}

function loadActivateMovieModal(idPelicula, movieName) {//Activa la pelicula para que esta aparezca en cartelera
    $('#modalDeleteMovie').find('.modal-body').empty();//Borra el body del modal
    var modal = $('#modalDeleteMovie');
    var mensaje = "<p>Esta seguro que desea agregar la pelicula " + movieName + " a la cartelera?</p>";
    modal.find('.modal-body').append(mensaje); //Busca el modal-body y agrega el mensaje
    $('#modalDeleteMovie').modal('show');

    //Cargar listener para boton de aceptar
    var btnAceptar = modal.find('#action-movie-aceptar-btn');
    btnAceptar.on("click", () => {
        activateMovie(idPelicula);
        //Una vez que se ejecuta el comando anterior se ejecutan las siguientes dos acciones para quitar el listener y ademas ocultar el modal
        btnAceptar.off("click");
        modal.modal("hide");
    });
}

function activateMovie(id) {
    //Todo... ejecutar request para activar pelicula

    p = {idPelicula: id};
    pJson = JSON.stringify(p);
    let request = new Request(url + 'api/admin/activar', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(p)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        //resultado = await response.json();
        fetchAndListMoviesAdmin();
    })();
}

function addImagen() {
    var imagenData = new FormData();
    imagenData.append("id_pelicula", pelicula.id);
    imagenData.append("image", $("#imagen").get(0).files[0]);
    let request = new Request(url + 'api/admin/' + pelicula.id + "/image", {method: 'POST', body: imagenData});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#add-modal #errorDiv"));
            return;
        }
    })();
    $('#imagen').val('');
}

function loadImage() {
    //'"+url+"api/personas/"+persona.cedula+"/imagen'  
    console.log("imagenData");
    var imagenData = new FormData();
    var id = 1;//pelicula.idPelicula
    let request = new Request(url + 'api/admin/' + id + "/imagen", {method: 'GET', headers: {}});
    //let request = new Request(url + '/api/admin/1/image' ,{ method: 'GET', headers: { }});
    (async () => {
        const response = await fetch(request);   //   /+"data:image/jpg;base64,${imagen.base64Image}" +
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        image = await response.json();
    })();
}


//-----------------------------------------sala---------------------------------------------------------------------------------------------------------------------------------------------------------
var sala = {idSala: "", asientos: ""};

function loadSala() {
    sala = Object.fromEntries((new FormData($("#forSala").get(0))).entries());
}

function resetSala() {
    sala = {idSala: "", asientos: ""};
    $('#idsala').val('');
    $('#cantidadAsientos').val('');

}

function Sala() {
    loadSala();
    console.log(sala);
    let request = new Request(url + 'api/admin/sala', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(sala)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#loginDialog #errorDiv"));
            return;
        }
        $('#modalSala').modal('hide');



        resetSala();
        fetchAndListSalas();
    })();
}

//-----------------------------------------Cartelera--------------------------------------------------------------------------------------------------------------------------------------------------
var cartelera = {fecha: "", Hinicio: "", Hfinal: "", IdpeliC: "", IdSalaC: ""};

function loadCartelera() {
    cartelera = Object.fromEntries((new FormData($("#forCart").get(0))).entries());
}

function resetCartelera() {
    cartelera = {fecha: "", Hinicio: "", Hfinal: "", IdpeliC: "", IdSalaC: ""};
    $('#fecha').val('');
    $('#Hinicio').val('');
    $('#hfinal').val('');
    $('#idP').val('');
    $('#idS').val('');
}

function Cartelera() {
    loadCartelera();
    let request = new Request(url + 'api/admin/cartelera', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(cartelera)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#loginDialog #errorDiv"));
            return;
        }
        $('#modalCartelera').modal('hide');

        resetCartelera();
    })();
}

function loadCarteleraForm() {
    setTimeout(() => {
        fetchAndListSalas();
    }, 400);

    var moviesSelector = $('#idP');
    var salasSelector = $('#idS');
    moviesSelector.empty();
    salasSelector.empty();
    moviesSelector.append($('<option selected>Seleccionar pelicula</option>'));
    salasSelector.append($('<option selected>Seleccionar sala</option>'));
    peliculas.forEach((item) => {
        var movieID = item.id;
        var movieName = item.nombre;
        moviesSelector.append($('<option></option>').attr('value', movieID).text(movieName));
    });
    salas.forEach((item) => {
        var salaID = item.sala;
        salasSelector.append($('<option></option>').attr('value', salaID).text(salaID));
    });

}

function fetchAndListSalas() {
    resetSalas();
    //resetMoviesContainer();
    let request = new Request(url + 'api/cartelera/salas', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        salas = await response.json();
        //loadMoviesListing();

    })();
}

function resetSalas() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    salas = [];
}

function resetPeliculas() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    peliculas = [];
}

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

var listadoT = [];
function buscaTicket() {
    listadoT = [];
    $("#tablaTicktes").empty();
    var t = $("#buscarT").val();
    let request = new Request(url + 'api/admin/' + t + '/ticketsListado', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#modalTickets #errorBT"));
            return;
        }
        listadoT = await response.json();
        leerTickets();
        $("#buscarT").val('');
    })();
}

function mostarH() {
    $("#modalTickets").modal('show');
    $("#tablaTicktes").empty();
}


function leerTickets() {
    var lista = $("#tablaTicktes");

    listadoT.forEach((item) => {
        var id = item.id;
        var n = item.nombre;
        var a = item.apellido;
        var s = item.sala;
        var p = item.pelicula;
        var f = item.fecha;
        var h = item.hora;
        var as = item.asiento;
        var tr = $("<div>Ticket Numero: " + id + " </div>");
        var tr1 = $("<div>Cliente: " + n + " " + a + " </div>");
        var tr2 = $("<div>Sala: " + s + " </div>");
        var tr3 = $("<div>Pelicula: " + p + " </div>");
        var tr4 = $("<div>Asientos: " + as + " </div>");
        var tr5 = $("<div>Fecha de la funcion: " + f + " </div>");
        var tr6 = $("<div>Hora de la Funcion: " + h + " </div>");
        var imprimir = $("<button>Imprimir ticket</button>");

        var l2 = $("<div>-----------------------------------------------------</div>");

        lista.append(tr);
        lista.append(tr1);
        lista.append(tr2);
        lista.append(tr3);
        lista.append(tr4);
        lista.append(tr5);
        lista.append(tr6);
        lista.append(imprimir);
        lista.append(l2);


        imprimir.on("click", () => {
            generatePDF(item, ticketHeaders, ticketKeys);
        });

        $('#modalTickets').modal('show');

    });
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


function load() {
    $("#peliculaRegister").click(Pelicula);
    $("#salaRegister").click(Sala);
    $("#cartRegister").click(Cartelera);
    $("#ticketCliente").click(buscaTicket);
    $("#nav-dropdown-cartelera-btn").click(loadCarteleraForm);
    $("#nav-dropdown-tickets-btn").click(mostarH);
}

$(load);