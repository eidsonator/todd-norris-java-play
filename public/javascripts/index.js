var Http = new XMLHttpRequest();
var url = window.location.href + "joke";
Http.open("GET", url);
Http.send();

Http.onreadystatechange = function() {
    var joke = JSON.parse(Http.responseText)["joke"];
    document.getElementById("joke").innerHTML = joke;
};

newJoke = function() {
    Http.open("GET", url);
    Http.send();
};

changeName = function() {
    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var postUrl = window.location.href + "name?firstName=" + firstName + "&lastName=" + lastName;

    var postHttp = new XMLHttpRequest();
    postHttp.open("GET", postUrl, true);
    postHttp.onreadystatechange = function() {
        newJoke();
    };
    postHttp.setRequestHeader('Content-type','application/json; charset=utf-8');
    postHttp.send();
};