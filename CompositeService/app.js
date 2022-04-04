function test(nummer) {
    switch (nummer) {
        case 1:
            test1();
            break;
        case 2:
            test2();
            break;
        case 3:
            test3();
            break;
        case 4:
            test4();
            break;
        case 5:
            test5();
            break;
        case 6:
            test6();
            break;
        case 7:
            test7();
            break;
    }
}

/**
 * Test 1 test het aanmaken van een klant, rechtstreeks naar de klant API
 */
function test1() {
    let start = Date.now();
    let adres = new Adres(982, 'Kerkstraat', '36B', '1081HA', 'Amsterdam');
    let klant = new Klant(9832489, 'John Doe', '1990-02-06', adres);

    let json = JSON.stringify(klant);
    $.ajax({
        url: 'http://localhost:8080/update',
        method: "POST",
        contentType: "application/json;",
        data: json,
        success: function (data) {
            console.log(data);
            $("#1").append("Test 1: success<br>");
            let eind = Date.now();
            $("#tijd").append("Test 1 duurde " + (eind - start) + " milliseconden<br>");
        },
        error: function (data) {
            console.log(data);
            $("#1").append("Test 1: FAILED, " + data.responseText + "<br>");
        }
    });
}

/**
 * Test 2 test het aanmaken van een artikel, rechtstreeks naar de Artikelen API
 */
function test2() {
    let start = Date.now();

    let json = JSON.stringify({
        titel: "Apple iPhone 14",
        omschrijving: "De nieuwste Smartphone van Apple",
        prijs: 1299.00
    });
    $.ajax({
        url: 'http://localhost:8081/update',
        method: "POST",
        contentType: "application/json",
        data: json,
        success: function (data) {
            console.log(data);
            $("#2").append("Test 2: success<br>");
            let eind = Date.now();
            $("#tijd").append("Test 2 duurde " + (eind - start) + " miliseconden<br>");
        },
        error: function (data) {
            console.log(data);
            $("#2").append("Test 2: FAILED, " + data.responseText + "<br>");

        }
    })
}

/**
 * Test 3 test het plaatsen van een bestelling via de composite service
 */
async function test3() {
    let amount = 100;
    let start = Date.now();
    const fun = async () => {
        await $.ajax({
            url: "http://localhost:8083/order/place",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                bestelling: {titel: 'Apple iPhone 14'},
                klant: {naam: "John Doe", geboortedatum: '1990-02-06'},
                bestelnummer: '50849840'
            }),
            success: function (data) {

            },
            error: function (data) {
                console.log(data);
                $("#3").append("Test 3: FAILED, " + data.responseText + "<br>");
            },
            async: true
        })
    }
    let callsArray = [];
    for (let i = 0; i < amount; i++) {
        callsArray.push(fun());
    }
    await Promise.all(callsArray);
    let eind = Date.now();
    let time = eind - start;
    $("#duur").append("Totale duur van Test 3 is " + time + " milliseconden dat is " + (time / amount) + " milliseconden per call<br>");
}

function test4() {
    let start = Date.now();
    let adres = new Adres(982, 'Kerkstraat', '36B', '1081HA', 'Amsterdam');
    let klant = new Klant(9832489, 'John Doe', '1990-02-06', adres);

    let json = JSON.stringify(klant);
    $.ajax({
        url: 'http://localhost:8083/klant/update',
        method: "POST",
        contentType: "application/json;",
        data: json,
        success: function (data) {
            console.log(data);
            $("#4").append("Test 4: success<br>");
            let eind = Date.now();
            $("#tijd").append("Test 4 duurde " + (eind - start) + " milliseconden<br>");
        },
        error: function (data) {
            console.log(data);
            $("#4").append("Test 4: FAILED, " + data.responseText + "<br>");
        }
    });
}

function test5() {
    let start = Date.now();

    let json = JSON.stringify({
        titel: "Apple iPhone 14",
        omschrijving: "De nieuwste Smartphone van Apple",
        prijs: 1299.00
    });
    $.ajax({
        url: 'http://localhost:8083/artikelen/update',
        method: "POST",
        contentType: "application/json",
        data: json,
        success: function (data) {
            console.log(data);
            $("#5").append("Test 5: success<br>");
            let eind = Date.now();
            $("#tijd").append("Test 5 duurde " + (eind - start) + " miliseconden<br>");
        },
        error: function (data) {
            console.log(data);
            $("#2").append("Test 5: FAILED, " + data.responseText + "<br>");

        }
    })
}

async function test6() {
    let amount = 100;
    let start = Date.now();
    const fun = async () => {
        await $.ajax({
            url: "http://localhost:8083/order/place/monolithic",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                bestelling: {titel: 'Apple iPhone 14'},
                klant: {naam: "John Doe", geboortedatum: '1990-02-06'},
                bestelnummer: '50849840'
            }),
            success: function (data) {

            },
            error: function (data) {
                console.log(data);
                $("#3").append("Test 6: FAILED, " + data.responseText + "<br>");
            },
            async: true
        })
    }
    let callsArray = [];
    for (let i = 0; i < amount; i++) {
        callsArray.push(fun());
    }
    await Promise.all(callsArray);
    let eind = Date.now();
    let time = eind - start;
    $("#duur").append("Totale duur van Test 6 is " + time + " milliseconden dat is " + (time / amount) + " milliseconden per call<br>");
}

function test7() {

}

class Klant {
    id;
    naam;
    geboortedatum;
    adres;


    constructor(id, naam, geboortedatum, adres) {
        this.id = id;
        this.naam = naam;
        this.geboortedatum = geboortedatum;
        this.adres = adres;
    }
}

class Adres {
    id;
    straat;
    huisnummer;
    postcode;
    stad;


    constructor(id, straat, huisnummer, postcode, stad) {
        this.id = id;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.stad = stad;
    }
}