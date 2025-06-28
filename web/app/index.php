<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Welcome | Knownora</title>
    <link rel="shortcut icon" href="../favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="../assets/style/general.css">
    <link rel="stylesheet" href="../assets/style/app.css">
    <link rel="stylesheet" href="../assets/style/app-home.css">
</head>
<body>
    <header>
        <div>
            <img src="../assets/images/knownora.svg" alt="Knownora Icon">
            <ul id="header-menu-left">
                <li><a class="underlined-link underlined-link-focused" href="#">Home<div></div></a></li>
                <li><a class="underlined-link" href="chat">Chat<div></div></a></li>
                <li><a class="underlined-link" href="calendar">Calendar<div></div></a></li>
                <li><a class="underlined-link" href="classes">Classes<div></div></a></li>
                <li><a class="underlined-link" href="school">School<div></div></a></li>
            </ul>
        </div>
        <div>
            <div id="header-search-wrapper">
                <label for="header-search-input">
                    <img src="../assets/icons/magnifying-glass-solid-0c0501.svg" alt="Search">
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
    <main id="simple-main">
        <h1 class="no-padding no-margin">Good Morning, Name</h1>
        <div id="app-home-upcoming-assignments">
            <h2>Upcoming Assignments</h2>
            <!-- TODO: Design checkboxes -->
            <div id="app-home-upcoming-assignments-grid">
                <div class="app-home-upcoming-assignments-divider"><div><b>TODAY</b> &nbsp; Jun 20</div><div></div></div>
                <div class="app-home-upcoming-assignments-entry">
                    <input type="checkbox" class="simple-checkbox">
                    <div class="class-nametag class-nametag-ch"><div>CH</div></div>
                    <span>Example Assignment 1</span>
                </div>
                <div class="app-home-upcoming-assignments-divider"><div><b>TOMORROW</b> &nbsp; Jun 21</div><div></div></div>
                <div class="app-home-upcoming-assignments-entry">
                    <input type="checkbox" class="simple-checkbox">
                    <div class="class-nametag class-nametag-d"><div>D</div></div>
                    <span>Example Assignment 2</span>
                </div>
                <div class="app-home-upcoming-assignments-entry">
                    <input type="checkbox" class="simple-checkbox">
                    <div class="class-nametag class-nametag-m"><div>M</div></div>
                    <span>Example Assignment 3</span>
                </div>
                <div class="app-home-upcoming-assignments-divider"><div><b>MONDAY</b> &nbsp; Jun 23</div><div></div></div>
                <div class="app-home-upcoming-assignments-entry">
                    <input type="checkbox" class="simple-checkbox">
                    <div class="class-nametag class-nametag-d"><div>D</div></div>
                    <span>Example Assignment 4</span>
                </div>
            </div>
        </div>
        <div id="app-home-recent-docs">
            <h2>Recent Documents</h2>
            <div id="app-home-recent-docs-grid">
                <div class="app-home-recent-docs-entry">
                    <img src="../assets/icons/file-pdf-0c0501.svg" alt="PDF file">
                    <div>
                        <b>very_important.pdf</b><br>
                        shared by&nbsp;<span>Niklas Berger</span>&nbsp;in&nbsp;<span>Chemie (10A)</span>
                    </div>
                </div>
            </div>
        </div>
    </main>
</body>
</html>