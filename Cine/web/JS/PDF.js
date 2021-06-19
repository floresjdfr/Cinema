/*
ticketHeaders = {
    id: 'Ticket #', 
    nombre: 'Cliente', 
    sala: 'Pelicula', 
    pelicula: 'Sala', 
    fecha: 'Fecha', 
    hora: 'Hora',
    asiento: 'Asientos'
};

ticket1 = {
    id: 1234,
    nombre: "Jose David Flores",
    sala: "01",
    pelicula: "Spiderman",
    fecha: "Hoy",
    hora: "3:00pm",
    asiento: ["3-1", "3-2", "3-3"]
};
*/

function buildTicketBody(data, headers, keys) {
    var content = [];
    keys.forEach(function (key) {
        texto = headers[key] + ": " + data[key];
        content.push({text: texto});
    });
    return content;
}

function generateTicket(data, headers, keys) { 
    return {
        content: [
            buildTicketBody(data, headers, keys)
        ]
    };
}

function generatePDF(data, headers, keys) { //data = informacion ticket, headers = titulos de los campos del tiquete, keys = keys del map 'data' y 'headers' (tienen que ser las mismas keys en ambos)
    var docDefinition = generateTicket(data, headers, keys);
    pdfMake.createPdf(docDefinition).download();
}




