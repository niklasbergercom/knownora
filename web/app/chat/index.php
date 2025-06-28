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
    <header>
        <div>
            <img src="../../assets/images/knownora.svg" alt="Knownora Icon">
            <ul id="header-menu-left">
                <li><a class="underlined-link" href="../">Home<div></div></a></li>
                <li><a class="underlined-link underlined-link-focused" href="#">Chat<div></div></a></li>
                <li><a class="underlined-link" href="../calendar">Calendar<div></div></a></li>
                <li><a class="underlined-link" href="../classes">Classes<div></div></a></li>
                <li><a class="underlined-link" href="../school">School<div></div></a></li>
            </ul>
        </div>
        <div>
            <div id="header-search-wrapper">
                <label for="header-search-input">
                    <img src="../../assets/icons/magnifying-glass-solid-0c0501.svg" alt="Search">
                </label>
                <div>
                    <input type="text" placeholder="Search..." id="header-search-input">
                    <div></div>
                </div>
            </div>
            <div id="my-profile-button-wrapper">
                <img src="https://cdn.niklasberger.com/knownora/example-pictures/male01.jpg" alt="Your Profile" title="Your Profile">
            </div>
        </div>
    </header>
    <main>
        <div id="chat-sidebar">
            <h2>People & Groups</h2>
            <div id="chat-focused">
                <div>
                    <img src="https://cdn.niklasberger.com/knownora/example-pictures/male02.jpg" alt="John Appleseed" title="John Appleseed">
                </div>
                <div>
                    <b>John Appleseed</b><br>
                    <span><span>You:</span>&nbsp;Lorem Ipsum Dolor Sit Amet</span>
                </div>
            </div>
            <div>
                <div>
                    <img src="https://cdn.niklasberger.com/knownora/example-pictures/male03.jpg" alt="Joe Public" title="Joe Public">
                </div>
                <div>
                    <b>Joe Public</b><br>
                    <span>Lorem Ipsum Dolor Sit Amet</span>
                </div>
            </div>
            <div>
                <div>
                    <img src="https://cdn.niklasberger.com/knownora/example-pictures/female01.jpg" alt="Samantha Sample" title="Samantha Sample">
                </div>
                <div>
                    <b>Samantha Sample</b><br>
                    <span><span>You:</span>&nbsp;Lorem Ipsum Dolor Sit Amet</span>
                </div>
            </div>
            <div>
                <div>
                    <img src="https://cdn.niklasberger.com/knownora/example-pictures/female02.jpg" alt="Alan Smithee" title="Alan Smithee">
                </div>
                <div>
                    <b>Alan Smithee</b><br>
                    <span>Lorem Ipsum Dolor Sit Amet</span>
                </div>
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
</body>
</html>