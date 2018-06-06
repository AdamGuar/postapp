var stompClient = null;

function connect() {
    var socket = new SockJS('https://concurrent-postprocesor-rest.herokuapp.com/sockets');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/model', function (model) {
            updateModel(JSON.parse(model.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendModelData(model) {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function updateModel(message) {
    //TODO
}