<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>School | Knownora</title>
    <link rel="shortcut icon" href="../../favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="../../assets/style/general.css">
    <link rel="stylesheet" href="../../assets/style/app.css">
</head>
<body>
    <div id="profile-sidebar-wrapper">
        <div id="profile-sidebar">
            <div>
                <div id="profile-sidebar-header">
                    <div id="profile-sidebar-account">
                        <img src="https://cdn.niklasberger.com/knownora/example-pictures/male01.jpg" alt="Your Profile" title="Your Profile">
                        <div>
                            <b>Your Name</b><br>
                            <span>your.name@example.edu</span>
                        </div>
                    </div>
                    <div id="profile-sidebar-close-button-wrapper">
                        <button id="profile-sidebar-close-button" title="Close Sidebar" onclick="toggleProfileSidebar(false)">
                            <img src="../../assets/icons/xmark-solid-0c0501.svg" alt="Close Sidebar">
                        </button>
                    </div>
                </div>
                <a class="underlined-link profile-sidebar-link" href="../profile">
                    <img src="../../assets/icons/user-solid-0c0501.svg" alt="My Profile">
                    My Profile
                    <div></div>
                </a>
                <a class="underlined-link profile-sidebar-link" href="../settings">
                    <img src="../../assets/icons/gear-solid-0c0501.svg" alt="Settings">
                    Settings
                    <div></div>
                </a>
                <a class="underlined-link profile-sidebar-link" onclick="signOut()">
                    <img src="../../assets/icons/arrow-right-from-bracket-solid-0c0501.svg" alt="Settings">
                    Sign Out
                    <div></div>
                </a>
            </div>
            <div>
                <span>Knownora v1.0.0 BETA</span><br>
                <span>Made with ❤️ by <a class="underlined-link" href="https://github.com/niklasbergercom/knownora" target="_blank" style="display: inline-block">Niklas Berger<div></div></a></span><br>
                <span>Published under the <a class="underlined-link" href="https://www.gnu.org/licenses/gpl-3.0.en.html" target="_blank" style="display: inline-block">GNU GPLv3<div></div></a> license</span>
            </div>
        </div>
    </div>
    <header>
        <div>
            <img src="../../assets/images/knownora.svg" alt="Knownora Icon">
            <ul id="header-menu-left">
                <li><a class="underlined-link" href="../">Home<div></div></a></li>
                <li><a class="underlined-link" href="../chat">Chat<div></div></a></li>
                <li><a class="underlined-link" href="#">Calendar<div></div></a></li>
                <li><a class="underlined-link" href="../classes">Classes<div></div></a></li>
                <li><a class="underlined-link underlined-link-focused" href="../school">School<div></div></a></li>
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
                <img src="https://cdn.niklasberger.com/knownora/example-pictures/male01.jpg" alt="Your Profile" title="Your Profile" onclick="toggleProfileSidebar(true)">
            </div>
        </div>
    </header>
    <main id="simple-main">
        <h1>Coming soon...</h1>
    </main>
    <script src="../../assets/scripts/app.js"></script>
</body>
</html>