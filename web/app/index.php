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
                <li><a class="underlined-link underlined-link-focused">Home<div></div></a></li>
                <li><a class="underlined-link">Chat<div></div></a></li>
                <li><a class="underlined-link">Calendar<div></div></a></li>
                <li><a class="underlined-link">Classes<div></div></a></li>
                <li><a class="underlined-link">School<div></div></a></li>
            </ul>
        </div>
        <div>
            <div id="my-profile-button-wrapper">
                <img src="https://cdn.niklasberger.com/knownora/example-pictures/male01.jpg" alt="Your Profile" title="Your Profile">
            </div>
        </div>
    </header>
    <main>
        <h1 class="no-padding no-margin">Good Morning, Name</h1>
        <div id="app-home-upcoming-assignments">
            <h2>Upcoming Assignments</h2>
            <!-- TODO: Design checkboxes -->
            <div id="app-home-upcoming-assignments-grid">
                <div class="app-home-upcoming-assignments-divider"><div><b>TODAY</b> &nbsp; Jun 20</div><div></div></div>
                <div class="app-home-upcoming-assignments-entry">
                    <input type="checkbox" class="app-home-checkbox">
                    <div class="class-nametag class-nametag-ch"><div>CH</div></div>
                    <span>Example Assignment 1</span>
                </div>
                <div class="app-home-upcoming-assignments-divider"><div><b>TOMORROW</b> &nbsp; Jun 21</div><div></div></div>
                <div class="app-home-upcoming-assignments-entry">
                    <input type="checkbox" class="app-home-checkbox">
                    <div class="class-nametag class-nametag-d"><div>D</div></div>
                    <span>Example Assignment 2</span>
                </div>
                <div class="app-home-upcoming-assignments-entry">
                    <input type="checkbox" class="app-home-checkbox">
                    <div class="class-nametag class-nametag-m"><div>M</div></div>
                    <span>Example Assignment 3</span>
                </div>
                <div class="app-home-upcoming-assignments-divider"><div><b>MONDAY</b> &nbsp; Jun 23</div><div></div></div>
                <div class="app-home-upcoming-assignments-entry">
                    <input type="checkbox" class="app-home-checkbox">
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
<!--        <script>-->
<!--            const assignmentsElement = document.getElementById("app-home-upcoming-assignments-grid")-->
<!--            assignmentsElement.innerHTML = "";-->
<!--            // Make a for loop for the numbers -15 to 15-->
<!--            for (let x = -15; x <= 15; x++) {-->
<!--                for (let y = -15; y <= 15; y++) {-->
<!--                    let element = document.createElement("div");-->
<!--                    element.className = "app-home-upcoming-assignments-entry";-->
<!--                    element.innerHTML = `-->
<!--                        <input type="checkbox" class="app-home-checkbox app-home-checkbox-${x}-${y}" checked=true>-->
<!--                        <div class="class-nametag class-nametag-ch"><div>CH</div></div>-->
<!--                        <span>X = ${x} AND Y = ${y}</span>-->
<!--                    `;-->
<!--                    assignmentsElement.appendChild(element);-->
<!--                    // Add this CSS rule: .app-home-checkbox-${x}-${y}::after { transform: rotate(45deg) translateY(${y}px) translateX(${x}px); }-->
<!--                    const style = document.createElement("style");-->
<!--                    style.innerHTML = `-->
<!--                        .app-home-checkbox-${x}-${y}::after {-->
<!--                            transform: rotate(45deg) translateY(${y}px) translateX(${x}px);-->
<!--                        }-->
<!--                    `;-->
<!--                    document.head.appendChild(style);-->
<!--                }-->
<!--            }-->
<!--        </script>-->
    </main>
</body>
</html>