function documentready(){
    $.ajax({
      method: "GET",
      url: "/user/list"
    })
    .done(function( data ) {
        //alert( "Data Saved: " + data[0].name );

        $.each(data, function(index, value) {
                console.log("Item at index " + index + ": " + value.name);
                 $('#userto').append('<option value="value.id">'+value.name+'</option>');
            });

      });
}

const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/gs-guide-websocket'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
	$("#log").append("<p>INFO: " + frame + "</p>");
    stompClient.subscribe('/topic/greetings', (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
	$("#log").append("<p>Error: " + 'Error with websocket' + error + "</p>");
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
	$("#log").append("<p>Error: " + 'Broker reported error: ' + frame.headers['message'] + "</p>");
	$("#log").append("<p>Error: " + 'Additional details: ' + frame.body + "</p>");
};

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
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
	$("#log").append("<p>INFO: Disconnected</p>");
}

function sendName() {
    stompClient.publish({
        destination: "/app/hello",
        body: JSON.stringify({'name': $("#name").val()})
    });
}

function sendMessage(userId) {
    stompClient.publish({
        destination: "/user/"+userId+"/hello",
        body: JSON.stringify({'name': $("#name").val()})
    });
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());


    $('#js-button').click(function(){
        var userFrom = $('#userfrom').val();
        var userTo = $('#userto').val();
        alert('from: ' + userFrom + " ; to:"+userTo);

        stompClient.subscribe('/user/'+userFrom+'/greetings', (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);

        sendMessage(userTo);
    });

    });
});




