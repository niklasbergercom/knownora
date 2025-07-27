<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Chat | Knownora</title>
    <link rel="shortcut icon" href="../../favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="../../assets/style/general.css">
    <link rel="stylesheet" href="../../assets/style/app.css">
    <link rel="stylesheet" href="../../assets/style/app-chat.css">
</head>
<body>
    <div id="popup-wrapper"></div>
    <?php include '../../assets/components/sidebar.php' ?>
    <?php include '../../assets/components/app-header.php' ?>
    <main>
        <div id="chat-sidebar">
            <div id="chat-sidebar-list">
                <p>Loading...</p>
            </div>
            <div id="chat-create-wrapper">
                <button class="simple-button" id="chat-create-button" title="Create Chat" onclick="togglePopup('create-chat')">
                    <img src="../../assets/icons/plus-solid-0c0501.svg" alt="Create Chat">Create Chat
                </button>
            </div>
        </div>
        <div id="chat-main">
            <div id="chat-no-chat-opened">
                <div>
                    <b>No chat opened</b><br>
                    Try opening a chat or starting a new conversation.
                </div>
            </div>
        </div>
    </main>
    <script src="../../assets/scripts/general.js"></script>
    <script src="../../assets/scripts/app.js"></script>
    <script src="../../assets/scripts/app-chat.js"></script>
</body>
</html>