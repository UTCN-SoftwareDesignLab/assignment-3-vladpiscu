var stompClient = null;


function connect() {
    var socket = new SockJS('/exampleEndpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/queue/reply', function (message) {
            var msg="<li>"+JSON.parse(message.body).text+"</li>";
            $("#result").append(msg);
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}