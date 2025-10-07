<!DOCTYPE html>
<html>
<head>
    <title>Test page</title>
    <link rel="stylesheet" href="/chat/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="/chat/jquery-3.1.1.min.js"></script>
    <script src="/chat/stomp.umd.min.js"></script>
    <script src="/chat/websocket.js"></script>
</head>
<body>
https://javarush.com/quests/lectures/questspring.level04.lecture44
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
    enabled. Please enable
    Javascript and reload this page!</h2></noscript>

<div style="margin:15px;">
    User From
    <select name="selectfrom" id="userfrom">
        <option value="1">User1</option>
        <option value="2">User2</option>
        <option value="3">User3</option>
    </select>

    User To
    <select name="selectto" id="userto">
        <option value="1">User1</option>
        <option value="2">User2</option>
        <option value="3">User3</option>
    </select>
    <button id="js-button" class="btn btn-outline-primary">Send</button>
</div>
<div id="main-content" class="container">
    <div class="row">
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <label for="connect">WebSocket connection:</label>
                    <button id="connect" class="btn btn-default" type="submit">Connect</button>
                    <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Disconnect
                    </button>
                </div>
            </form>
        </div>
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <label for="name">What is your name?</label>
                    <input type="text" id="name" class="form-control" placeholder="Your name here...">
                </div>
                <button id="send" class="btn btn-default" type="submit">Send</button>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="conversation" class="table table-striped">
                <thead>
                <tr>
                    <th>Greetings</th>
                </tr>
                </thead>
                <tbody id="greetings">
                </tbody>
            </table>
        </div>
    </div>
</div>
<div>
    <p>LOG</p>
    <div id="log"></div>
</div>
</body>
</html>