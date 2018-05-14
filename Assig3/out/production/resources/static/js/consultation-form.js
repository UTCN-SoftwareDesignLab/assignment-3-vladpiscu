var stompClient = null;

function connect() {
    var socket = new SockJS('/exampleEndpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

window.onload=connect();
window.onbeforeunload = disconnect();