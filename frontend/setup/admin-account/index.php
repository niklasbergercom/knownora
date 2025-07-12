<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Create an Admin Account | Knownora</title>
    <link rel="shortcut icon" href="../../favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="../../assets/style/general.css">
    <link rel="stylesheet" href="../../assets/style/login.css">
</head>
<body>
    <main>
        <div id="login-logo">
            <img src="../../assets/images/knownora.svg" alt="Knownora Icon">
            <span>Knownora</span>
        </div>
        <div id="login-main">
            <h1>Create an Admin Account</h1>
            <?php
            // Disable error reporting for production
            error_reporting(0);
            // Fetch http://backend/private/setup/status
            try {
                $setupStatus = file_get_contents('http://backend:8080/private/setup/status');
                if ($setupStatus == 'null') {
                    echo "
                            <p>To get started, create an admin account.</p>
                            <form>
                                <form>
                                    <label for='login-name'>Full Name * <span id='login-name-message' class='form-error'></span></label><br>
                                    <input type='text' id='login-name' placeholder='Alan Smithee' required><br>
                                    <label for='login-email'>Email * <span id='login-email-message' class='form-error'></span></label><br>
                                    <input type='email' id='login-email' placeholder='your.name@example.edu' required><br>
                                    <label for='login-password'>Password * <span id='login-password-message' class='form-error'></span></label><br>
                                    <input type='password' id='login-password' placeholder='••••••••••' required>
                                    <label for='login-password-repeat'>Repeat Password * <span id='login-password-repeat-message' class='form-error'></span></label><br>
                                    <input type='password' id='login-password-repeat' placeholder='••••••••••' required>
                                    <button type='submit' class='simple-button' id='login-button' onclick='adminSignup()'>Create account</button>
                                    <div class='form-error' id='login-general-message'></div>
                                </form>
                            </form>
                        ";
                } elseif ($setupStatus == '') {
                    echo "
                            <p>There was an error while trying to fetch the setup status. Please make sure the backend container is running and try again.</p>
                            <div>
                                <button class=\"simple-button\" onclick=\"window.location.reload()\">Retry</button>
                            </div>
                        ";
                } else {
                    echo "
                            <p>An admin account has already been created. You can click the button below to go to the sign-in page.</p>
                            <p>If you forgot your password, click \"I forgot my password\" on the sign-in page.</p>
                            <div>
                                <button class=\"simple-button\" onclick=\"window.location.href = '../../app/login'\">Go to Login</button>
                            </div>
                        ";
                }
            } catch (Exception $e) {
                echo "
                            <p>There was an error while trying to fetch the setup status. Please make sure the backend container is running and try again.</p>
                            <div>
                                <button class=\"simple-button\" onclick=\"window.location.reload()\">Retry</button>
                            </div>
                    ";
            }
            ?>
        </div>
    </main>
    <script src="../../assets/scripts/general.js"></script>
    <script src="../../assets/scripts/setup.js"></script>
</body>
</html>