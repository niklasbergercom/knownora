<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Calendar | Knownora</title>
    <link rel="shortcut icon" href="../../favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="../../assets/style/general.css">
    <link rel="stylesheet" href="../../assets/style/app.css">
    <link rel="stylesheet" href="../../assets/style/app-cal.css">
</head>
<body>
    <?php include '../../assets/components/sidebar.php' ?>
    <?php include '../../assets/components/app-header.php' ?>
    <main>
        <div id="cal-sidebar">
            <h2>My Classes</h2>
            <ul id="cal-class-list">
                <li>
                    <input type="checkbox" class="simple-checkbox">
                    <div class="class-nametag class-nametag-ch"><div>CH</div></div>
                    <span>Chemie (10A)</span>
                </li>
                <li>
                    <input type="checkbox" class="simple-checkbox">
                    <div class="class-nametag class-nametag-d"><div>D</div></div>
                    <span>Deutsch (10A)</span>
                </li>
                <li>
                    <input type="checkbox" class="simple-checkbox">
                    <div class="class-nametag class-nametag-m"><div>M</div></div>
                    <span>Mathematik (10A)</span>
                </li>
            </ul>
            <h2>My Calendars</h2>
            <ul id="cal-class-list">
                <li>
                    <input type="checkbox" class="simple-checkbox">
                    <span>Personal</span>
                </li>
                <li>
                    <input type="checkbox" class="simple-checkbox">
                    <span>A Linked iCal</span>
                </li>
            </ul>
            <div id="cal-create-wrapper">
                <button class="simple-button" id="event-create-button" title="Create Event" onclick="createCalEvent()">
                    <img src="../../assets/icons/plus-solid-0c0501.svg" alt="Create Event">Create Event
                </button>
                <button class="simple-button" id="cal-create-button" title="Create Calendar" onclick="createCal()">
                    <img src="../../assets/icons/plus-solid-0c0501.svg" alt="Create Calendar">Create Calendar
                </button>
            </div>
        </div>
        <div id="cal-main">
            <div id="cal-navbar">
                <button class="simple-button" title="Previous Month" id="cal-navbar-prev"><img src="../../assets/icons/arrow-left-solid-0c0501.svg" alt="Previous Month" width="15px"></button>
                <button class="simple-button" title="Next Month" id="cal-navbar-next"><img src="../../assets/icons/arrow-right-solid-0c0501.svg" alt="Next Month" width="15px"></button>
                <span><b>June</b> 2025</span>
            </div>
            <div id="cal-view">
                <div id="cal-view-weekdays">
                    <div>MON</div><div>TUE</div><div>WED</div><div>THU</div><div>FRI</div><div>SAT</div><div>SUN</div>
                </div>
                <div id="cal-view-days">
                    <div class="cal-view-unfocused">26</div><div class="cal-view-unfocused">27</div><div class="cal-view-unfocused">28</div><div class="cal-view-unfocused">29</div><div class="cal-view-unfocused">30</div><div class="cal-view-unfocused">31</div><div>1</div>
                    <div>
                        2
                        <div>
                            <div class="class-nametag class-nametag-ch"><div>CH</div></div>
                            Some Event Lorem Ipsum Dolor Sit Amet
                        </div>
                        <div>
                            <div class="class-nametag class-nametag-m"><div>M</div></div>
                            Some Event Lorem Ipsum Dolor Sit Amet
                        </div>
                        <div>
                            +1 more
                        </div>
                    </div>   <div>3</div><div>4</div><div>5</div><div>6</div><div>7</div><div>8</div>
                    <div>9</div><div>10</div><div>11</div><div>12</div><div>13</div><div>14</div><div>15</div>
                    <div>16</div><div>17</div><div>18</div><div>19</div><div>20</div><div>21</div><div>22</div>
                    <div>23</div><div>24</div><div>25</div><div>26</div><div>27</div><div>28</div><div>29</div>
                    <div>30</div><div class="cal-view-unfocused">1</div><div class="cal-view-unfocused">2</div><div class="cal-view-unfocused">3</div><div class="cal-view-unfocused">4</div><div class="cal-view-unfocused">5</div><div class="cal-view-unfocused">6</div>
                </div>
            </div>
        </div>
    </main>
    <script src="../../assets/scripts/general.js"></script>
    <script src="../../assets/scripts/app.js"></script>
    <script src="../../assets/scripts/app-calendar.js"></script>
</body>
</html>