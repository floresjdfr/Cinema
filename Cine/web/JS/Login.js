var url = "http://localhost:8080/Cine/";


function login() {
    //if (!loginValidar()) return;
    usuario = {
        id: $("#id").val(),
        password: $("#password").val(),
        type: "LOGIN"
    };
    let request = new Request(url + 'api/usuario/login', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(usuario)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#loginDialog #errorDiv"));
            return false;
        }
        usuario = await response.json();
        sessionStorage.setItem('Usuario', JSON.stringify(usuario));
        $('#modalLogin').modal('hide');
        switch (usuario.type) {
            case 'ADMINISTRATOR':
                showAdminOptions();
                break;
            case 'CLIENTE':
                showClientOptions();
                break;
        }
    })();
    $('#id').val('');
    $('#password').val('');
}

function showAdminOptions() {
    usuario = JSON.parse(sessionStorage.getItem('Usuario'));
    $('#nav-register-btn').hide();
    $('#nav-login-btn').hide();
    $('#nav-usuario-dropdown').html(`Administrador: ` + usuario.id);

    $('#nav-dropdown-compras').hide();
    $('#nav-dropdown-compras-divider').hide();

    $('#nav-dropdown-pelicula').show();
    $('#nav-dropdown-pelicula-divider').show();

    $('#nav-dropdown-sala').show();
    $('#nav-dropdown-sala-divider').show();

    $('#nav-dropdown-cartelera').show();
    $('#nav-dropdown-cartelera-divider').show();

    $('#nav-usuario-dropdown').show();

    fetchAndListMoviesAdmin();
}

function showClientOptions() {
    usuario = JSON.parse(sessionStorage.getItem('Usuario'));
    $('#nav-register-btn').hide();
    $('#nav-login-btn').hide();
    $('#nav-usuario-dropdown').html(usuario.id);


    $('#nav-dropdown-compras').show();
    $('#nav-dropdown-compras-divider').show();

    $('#nav-dropdown-pelicula').hide();
    $('#nav-dropdown-pelicula-divider').hide();


    $('#nav-dropdown-tickets-btn').hide();
    $('#nav-dropdown-tickets-divider').hide();


    $('#nav-dropdown-sala').hide();
    $('#nav-dropdown-sala-divider').hide();

    $('#nav-dropdown-cartelera').hide();
    $('#nav-dropdown-cartelera-divider').hide();

    $('#nav-usuario-dropdown').show();
    //fetchAndListMovies();
}
function showNotLoggedOptions() {
    $('#nav-usuario-dropdown').hide();
}
function showLogoutOptions() {//Muetra las opciones despues de logout
    $('#nav-register-btn').show();
    $('#nav-login-btn').show();
    $('#nav-usuario-dropdown').hide();


}

function loginValidar() {
    $("#loginForm").addClass("was-validated");
    return $("#loginForm").get(0).checkValidity();
}

function logout() {
    let request = new Request(url + 'api/usuario/logout', {method: 'DELETE', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#loginDialog #errorDiv"));
            return;
        }
        sessionStorage.removeItem('Usuario');
        fetchAndListMovies();
        showLogoutOptions();
    })();
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

function loadNavVarOptions() {
    showNotLoggedOptions();
    $('#login-aceptar-btn').click(login);
    $('#nav-dropdown-logout').on('click', logout);
}

$(loadNavVarOptions);