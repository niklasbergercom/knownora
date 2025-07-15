<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up | Knownora</title>
    <link rel="shortcut icon" href="../../../favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="../../../assets/style/general.css">
    <link rel="stylesheet" href="../../../assets/style/login.css">
</head>
<body>
    <div id="login-popup">
        <div id="login-popup-inner"></div>
        <div id="login-popup-progress">
            <div></div>
        </div>
    </div>
    <main>
        <div id="login-logo">
            <img src="../../../assets/images/knownora.svg" alt="Knownora Icon">
            <span>Knownora</span>
        </div>
        <div id="login-main">
            <h1>Create an Account</h1>
            <form>
                <label for="login-name">Full Name * <span id="login-name-message" class="form-error"></span></label><br>
                <input type="text" id="login-name" placeholder="Alan Smithee" required><br>
                <label for="login-email">Email * <span id="login-email-message" class="form-error"></span></label><br>
                <input type="email" id="login-email" placeholder="your.name@example.edu" required><br>
                <label for="login-password">Password * <span id="login-password-message" class="form-error"></span></label><br>
                <input type="password" id="login-password" placeholder="••••••••••" required>
                <label for="login-password-repeat">Repeat Password * <span id="login-password-repeat-message" class="form-error"></span></label><br>
                <input type="password" id="login-password-repeat" placeholder="••••••••••" required>
                <label for="login-grade">Grade * <span id="login-grade-message" class="form-error"></span></label>
                <select id="login-grade" required>
                    <option value="" disabled selected>Select your grade</option>
                    <option value="0">Staff / Not a student</option>
                </select><br>
                <button type="submit" class="simple-button" id="login-button" onclick="signUp()">Continue</button>
                <div class="form-error" id="login-general-message"></div>
            </form>
            <div>
                <a href="../" class="underlined-link">Sign in instead<div></div></a>
            </div>
        </div>
    </main>
    <script src="../../../assets/scripts/general.js"></script>
    <script src="../../../assets/scripts/signup.js"></script>
    <script>
        <?php
            $signUpDomain = @file_get_contents('http://backend:8080/private/auth/signup-domain');
            if ($signUpDomain == "") {
                $signUpDomain = "null";
            }
            echo "const signUpDomain = '$signUpDomain';";
        ?>
        <?php
            $signUpGrades = @file_get_contents('http://backend:8080/private/auth/signup-grades');
            if ($signUpGrades == "") {
                $signUpGrades = "[]";
            }
            echo "const signUpGrades = $signUpGrades;";
        ?>
    </script>
</body>
</html>