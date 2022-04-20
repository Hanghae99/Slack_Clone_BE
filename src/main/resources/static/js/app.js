var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        stompClient.subscribe('/topic/greetings'+getChatId(), function (greeting) {
            console.log("서버에서 받는 메세지 : " + greeting.body)
            showGreeting(JSON.parse(greeting.body).message);
        });

    });
}
function getChatId(){
    let chatRoomIdArray = window.location.href.split("/")
    let chatRoomId = chatRoomIdArray[chatRoomIdArray.length-1];
    return "/"+chatRoomId;
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    let data ={
        'message' : $("#message").val(),
         'roomId'  : getChatId()

    }
    console.log(data);
    stompClient.send("/app/hello", {}, JSON.stringify(data));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        // 기본동작 방지
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

