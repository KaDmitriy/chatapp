/**
* Настройки при загрузки страницы
*/
function documentready(){

    /**
    * Формирование списка пользователей
    */
    $.ajax({
      method: "GET",
      url: "/user/list"
    })
    .done(function( data ) {
        //alert( "Data Saved: " + data[0].name );
        $('#userto').append('<option value="0">Выберете пользователя</option>');

        $.each(data, function(index, value) {
            //console.log("Item at index " + index + ": " + value.name);
            $('#userto').append('<option value="'+value.id+'">'+value.name+'</option>');

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

    var subscribeUser = '/user/'+USERID+'/topic/message';
    $("#log").append("<p>INFO: subscribeUser: " + subscribeUser + "</p>");
    stompClient.subscribe(subscribeUser, (message) => {
        showGreeting(">>> USERID/topic/message >>> "+JSON.parse(message.body).content);
    });

    stompClient.subscribe('/user/'+USERID+'/topic/to', (greeting) => {
        showGreeting(">>> /user/USERID/topic/to >>> "+JSON.parse(greeting.body).content);
    });

    stompClient.subscribe('/topic/to', (greeting) => {
        showGreeting(">>> !!! /topic/to >>> "+JSON.parse(greeting.body).content);
    });

    stompClient.subscribe('/user/topic/to', (greeting) => {
        showGreeting(">>> /user/topic/to >>> "+JSON.parse(greeting.body).content);
    });

    //JOB
    stompClient.subscribe('/user/topic/call/ischeck', (сallOutCheck) => {
        showGreeting(">>>topic/call/ischeck >>> check:"+JSON.parse(сallOutCheck.body).check);
    });

    stompClient.subscribe('/topic/call/ischeck', (сallOutCheck) => {
        showGreeting(">>>topic/call/check >>> check:"+JSON.parse(сallOutCheck.body).check);
    });

    stompClient.subscribe('/user/'+USERID+'/topic/call/in', (сallOutCheck) => {
        showGreeting(">>>'/topic/call/in >>> callUserID:"+JSON.parse(сallOutCheck.body).callUserID);
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
        destination: "/chat/send",
        body: JSON.stringify({'text': $("#name").val(), 'chatUID':'000d5319-06f6-48f1-a3bc-44506e957926', 'toIDUser':SELECTUSERIDCHAT})
    });
}

function sendMessage(userId) {
    stompClient.publish({
        destination: "/user/"+userId+"/hello",
        body: JSON.stringify({'name': $("#name").val()})
    });
}

function funCallOutIn() {
    stompClient.publish({
        destination: "/app/call/from",
        body: JSON.stringify({'userFromId': USERID, 'userToId':SELECTUSERIDCHAT})
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
    $( "#callOutIn" ).click(() => funCallOutIn());


    $('#userto').change(function(){
        var userFrom = $('#userfrom').val();
        var userTo = $('#userto').val();
        SELECTUSERIDCHAT = userTo;
        $.ajax({
              method: "GET",
              url: "/user/chat",
              data: {
                fromUserID : USERID,
                toUserID : userTo
              }
        })
        .done(function( data ) {
            registrationChat(data);
        });
    });

    //SessionID
    //const sessionId = readCookie('JSESSIONID');
    $("#sessionid").append(COOCIESERVER);

});


/**
* Регистрация чата
* curChat : {uid: "UUID", name: "String", fromUSer: { id: Int, name: "String" }, toUSer: { id: Int, name: "String" }}
*/
function registrationChat(curChat){
    console.log(curChat);

    //alert('from: ' + userFrom + " ; to:"+userTo);
    //stompClient.subscribe('/user/'+userFrom+'/greetings', (greeting) => {
    //showGreeting(JSON.parse(greeting.body).content);

    //sendMessage(userTo);
    //});

}




