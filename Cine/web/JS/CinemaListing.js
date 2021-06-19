var url = "http://localhost:8080/Cine/";
var totalPagar = 0;
var seatsArray = [];
var picAdress = "Images/spiderman.jpg";
//var image = {    base64Image: ""};
var imageD = new Image();
var image = new Image();
var peliculas = new Array();
var carteleras = new Array();
var salas = new Array();
var tickets = new Array();
var pelicula = {id: "", nombre: "", duracion: "", descripcion: "", precio: ""};
var usuario;

function loadMoviesListing() { //Dentro de este metodo deberia ir el request al API para solicitar las peliculas de la cartelera

    var listaPeliculasContainer = $("#movie-cards-container");
    resetMoviesContainer();


    peliculas.forEach((item) => {
        var movieID = item.id;
        var movieName = item.nombre;
        var movieDuration = item.duracion;
        var movieDescripcion = item.descripcion;//"data:image/jpg;base64,${image.base64Image}"
        //var movieStatus = "1";//item.estado;

        var newListItem = $("<div />");
        var clientCard = `<div class="col">
                        <div class="card shadow-sm">
                            
                            <img   src="` + url + `api/cartelera/` + movieID + `/imagen" class="card-img-top" alt="">
                            <div class="card-body">
                                <p class="card-text">`
                + movieDescripcion +
                `</p>
                                <div class="d-flex justify-content-between align-items-center" >
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-sm btn-outline-secondary" id="view-movie">
                                            Ver pelicula
                                        </button>
                                    </div>
                                    <small class="text-muted">` + movieDuration + `</small>
                                </div>
                            </div>
                        </div>
                    </div>`;


        newListItem.html(clientCard);
        newListItem.find("#view-movie").on("click", () => {
            view(movieName, movieID, item.precio);
        });
        var btn = newListItem.find("#view-movie");
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


function fetchAndListMovies() {
    resetPeliculas();
    resetMoviesContainer();
    let request = new Request(url + 'api/cartelera/peliculas', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        peliculas = await response.json();
        fetchAndListCarteleras();
        fetchAndListSalas();
        fetchAndListTickets();
        setTimeout(() => {
            loadMoviesListing();
        }, 400);
    })();
}
function fetchAndListCarteleras() {
    resetCarteleras();
    let request = new Request(url + 'api/cartelera/carteleras', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        carteleras = await response.json();
    })();
}
function fetchAndListSalas() {
    resetSalas();
    let request = new Request(url + 'api/cartelera/salas', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        salas = await response.json();
    })();
}
function fetchAndListTickets() {
    resetTickets();
    //resetMoviesContainer();
    let request = new Request(url + 'api/cartelera/tickets', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        tickets = await response.json();
        //loadMoviesListing();

    })();
}
function resetPeliculas() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    peliculas = [];
}
function resetCarteleras() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    carteleras = [];
}
function resetSalas() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    salas = [];
}
function resetTickets() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    tickets = [];
}
function resetSeatsArray() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    seatsArray = [];
}
function resetTotalPagar() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    totalPagar = 0;
    $('#total').empty().html(0);
}
function view(movieName, idPelicula, precio) {
    //Aqui va el request al API para que retorne los datos de la pelicula indicada en el ID
    listaHorariosJSON = carteleras;
    $("#modalLoginLabel").empty();
    $("#modalLoginLabel").text(movieName + Array(20).fill('\xa0').join('') + ' Horarios disponibles');
    var listaHorarios = $("#lista-horarios").empty();//Lista de horarios del modal se limpia //table

    listaHorariosJSON.forEach((item) => {
        if (idPelicula === item.pelicula) {
            var newListItem = $("<li></li>");
            newListItem.html('<a href="javascript:void(0);" id="horario">' + item.fecha_funcion
                    + ' Hora Inicio ' + item.hora_inicio + ' Hora fin ' + item.hora_fin + '</a>');
            newListItem.find('#horario').on('click', () => {
                butacas(movieName, item, precio);
            });
            listaHorarios.append(newListItem);
        }
    });
    $('#modalHorarios').modal('show');
}
var precioSeat = 0;
var seatSelected = 0;
function resetPrecioSeat() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    precioSeat = 0;
}
function resetSeatSelected() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    seatSelected = 0;
    $('#count').empty().html(0);
}
function butacas(movieName, movieCartelera, preciom) {
    resetSeatsArray(); //movieName
    resetPrecioSeat();
    resetSeatSelected();
    resetTotalPagar();

    $("#exampleModalToggleLabel2").empty();
    $("#exampleModalToggleLabel2").text(movieName + Array(20).fill('\xa0').join('') + '  Butacas disponibles');
    //exampleModalToggleLabel2
    var informacionButacasJSON = {
        idCartelera: "",
        cantidadAsientos: "",
        precio: "",
        ocupados: []
    };
    informacionButacasJSON.precio = preciom;
    precioSeat = parseInt(preciom);
    salas.forEach((itemS) => {
        if (movieCartelera.sala === itemS.sala) {
            informacionButacasJSON.cantidadAsientos = itemS.butacas;
            informacionButacasJSON.idCartelera = movieCartelera.id;
            //return;
        }
    });
    tickets.forEach((item) => {
        if (movieCartelera.id === item.cartelera) {
            var seat = [];
            seat = item.butaca.split(",");
            for (var i = 0; i < seat.length; i++) {
                var butaka = seat[i];
                informacionButacasJSON.ocupados.push(butaka);
            }
            //return;
        }
    });

    loadSeats(informacionButacasJSON);

    $('#modalButacas').modal('show');
}
function loadSeats(informacionButacasJSON) {//Recibe JSON con informacion necesaria

    resetSeats(); //Lo unico que hace es borrar el arreglo donde guardan los asientos seleccionados por el usuario
    resetSeatsArray(); //movieName
    resetPrecioSeat();
    resetSeatSelected();
    resetTotalPagar();

    $("#screen-seats-container").remove();//Borra el 'body' del modal para que este no se duplique cada vez que se abre la pestaña de butacas

    //Carga de variables desde JSON
    var cantidadAsientos = informacionButacasJSON.cantidadAsientos;
    precioSeat = informacionButacasJSON.precio;
    var ocupiedSeats = informacionButacasJSON.ocupados;

    var totalColumnas = 8; //Total de columnas de la sala
    const totalFilas = parseInt(cantidadAsientos / totalColumnas);
    const asientosUltimaFilaIncompleta = cantidadAsientos % totalColumnas;//Cuantos ascientos estan en la ultima fila

    var container = $("<div class='container' id='screen-seats-container'></div>");
    var screen = $('<div class = "screen"></div>');
    container.append(screen);

    for (i = 0; i < totalFilas; i++) {//Crea las filas y columnas de las butacas
        var newRow = $("<div class='row'></div>");
        for (j = 0; j < totalColumnas; j++) {
            var id = i + "-" + j;
            var newSeat = $("<div class='seat' id=" + id + "></div>");
            if (ocupiedSeats.includes(id)) {
                newSeat.addClass("occupied");
            }

            newRow.append(newSeat);
        }
        container.append(newRow);
    }
    $('#modalButacas').find("#comprar").on("click", () => {
        comprar(informacionButacasJSON.idCartelera);//pase el id de la cartelera al cual se hace la compra
        $('#modalButacas').find("#comprar").off('click');
    });
    // $("#comprar").click(comprar(informacionButacasJSON.idCartelera));
    $(".theather-container").append(container);
    $(".seat:not(.occupied)").click(setSelected_Unselected);//Agrega listener para poder cambiar los seleccionados
}
function resetSeats() { //Esta funcion solo se utiliza para volver a poner el array donde se guardan los asientos 
    seatsArray = [];
}
function addSeat() {
    totalPagar += parseInt(precioSeat);
    seatSelected += 1;
    changeTicketInfo();
}
function removeSeat() {
    totalPagar -= parseInt(precioSeat);
    seatSelected -= 1;
    changeTicketInfo();
}
function changeTicketInfo() {
    $("#count").html(seatSelected);
    $("#total").html(totalPagar);
}
function addSeatToArray(id) {
    seatsArray.push(id);
    console.log(seatsArray);
}
function removeSeatFromArray(id) {
    var pos = seatsArray.indexOf(id);
    seatsArray.splice(pos, 1);
    console.log(seatsArray);
}
function setSelected_Unselected() {
    var classes = $(this).attr("class");
    var classList = classes.split(/\s+/);
    if (classList.includes("selected")) {
        var id = $(this).attr("id");
        $(this).removeClass("selected");
        removeSeat();
        removeSeatFromArray(id);

    } else {
        var id = $(this).attr("id");
        $(this).addClass("selected");
        addSeat();
        addSeatToArray(id);
    }
}
function comprar(idCartelera) {

    try {
        usuario = sessionStorage.getItem("Usuario");
    } catch (exception) {
        //console.log(exception);
    }

    if (usuario !== null) {
        // var ticket;
        let request = new Request(url + 'api/usuario/' + idCartelera + '/comprar', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(seatsArray)});
        (async () => {
            const response = await fetch(request);
            if (!response.ok) {
                errorMessage(response.status, $("#loginDialog #errorDiv"));
                return;
            }
            ticket = await response.json();
            generatePDF(ticket[0], ticketHeaders, ticketKeys);
            setTimeout(() => {
                $('#modalButacas').modal('hide');
                $('#modalHorarios').modal('hide');
            }, 400);
        })();

        fetchAndListTickets();
        fetchAndListMovies();
        resetSeatsArray(); //movieName
        resetPrecioSeat();
        resetSeatSelected();
        resetTotalPagar();


    } else {
        // while (usuario === null) {
        $('#modalRegistro').modal('show');
        //$('#modalLogin').modal('show');
        //sessionStorage.setItem('Usuario', JSON.stringify(usuario));
        try {
            usuario = sessionStorage.getItem("Usuario");
        } catch (exception) {
            //console.log(exception);
        }
//            sessionStorage.setItem('Usuario', JSON.stringify(usuario));
//            $('#modalLogin').modal('hide');
        // }
    }
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
        //imageD.src = 'data:image/png;base64,' + image.base64Image;
        //document.body.appendChild(imageD);    
        //image.src = 'data:image/png;base64,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';
        //document.body.appendChild(image);
    })();
    var fs = require('fs');
// string generated by canvas.toDataURL()
    var img = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0"
            + "NAAAAKElEQVQ4jWNgYGD4Twzu6FhFFGYYNXDUwGFpIAk2E4dHDRw1cDgaCAASFOffhEIO"
            + "3gAAAABJRU5ErkJggg==";
// strip off the data: url prefix to get just the base64-encoded bytes
    var data = img.replace(/^data:image\/\w+;base64,/, "");
    var buf = new Buffer(data, 'base64');
    fs.writeFile('image.png', buf);
}

function resetMoviesContainer() {//Simplemente borra lo que tiene el array del Json
    $("#movie-cards-container").empty();
}

function buscar() {
    try {
        usuario = sessionStorage.getItem("Usuario");
        usuario = JSON.parse(usuario);
    } catch (exception) {

    }
    if (usuario === null || usuario.type === "CLIENTE") {
        var x = $("#textoB").val();
        var low = x.toUpperCase();
        resetPrecioSeat();
        resetSeatSelected();
        resetSeatsArray();
        resetTotalPagar();
        resetMoviesContainer();
        peliculas.forEach((item) => {
            var a = item.nombre.toUpperCase();
            if (a.includes(low)) {
                clientSearch(item);

            } else if (x === "" || x === " ") {
                loadMoviesListing();
            }
        });
    } else if (usuario.type === "ADMINISTRATOR") {
        var x = $("#textoB").val();
        var low = x.toUpperCase();
        resetPrecioSeat();
        resetSeatSelected();
        resetSeatsArray();
        resetTotalPagar();
        resetMoviesContainer();
        peliculas.forEach((item) => {
            var a = item.nombre.toUpperCase();
            if (a.includes(low)) {
                adminSearch(item);
            } else if (x === "" || x === " ") {
                loadMoviesListingAdmin();
            }
        });

    }
}

function clientSearch(item) {
    var listaPeliculasContainer = $("#movie-cards-container");
    var movieID = item.id;
    var movieName = item.nombre;
    var movieDuration = item.duracion;
    var movieDescripcion = item.descripcion;
    var newListItem = $("<div />");
    var clientCard = `<div class="col">
                        <div class="card shadow-sm">
                            
                            <img   src="` + url + `api/cartelera/` + movieID + `/imagen" class="card-img-top" alt="">
                            <div class="card-body">
                                <p class="card-text">`
            + movieDescripcion +
            `</p>
                                <div class="d-flex justify-content-between align-items-center" >
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-sm btn-outline-secondary" id="view-movie">
                                            Ver pelicula
                                        </button>
                                        
                                    </div>
                                    <small class="text-muted">` + movieDuration + `</small>
                                </div>
                            </div>
                        </div>
                    </div>`;
    newListItem.html(clientCard);
        newListItem.find("#view-movie").on("click", () => {
            view(movieName, movieID, item.precio);
        });
        var btn = newListItem.find("#view-movie");
        btn.hide();
        newListItem.on("mouseover", () => {
            btn.show();
        });
        newListItem.on("mouseleave", () => {
            btn.hide();
        });
        listaPeliculasContainer.append(newListItem);
}
function adminSearch(item) {
    var listaPeliculasContainer = $("#movie-cards-container");
    var movieID = item.id;
    var movieName = item.nombre;
    var movieDuration = item.duracion;
    var movieDescripcion = item.descripcion;//"data:image/jpg;base64,${image.base64Image}"
    var movieStatus = item.estado;
    var buttonDelete = `<button id="admin-movie-action" type="button" class="btn btn-danger movie-action">Quitar de cartelera</button>`;
    var buttonAdd = `<button id="admin-movie-action" type="button" class="btn btn-success movie-action">Agregar a cartelera</button>`;
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
}

function load() {
    fetchAndListMovies();
    $("#buscaboton").click(buscar);

}

$(load);