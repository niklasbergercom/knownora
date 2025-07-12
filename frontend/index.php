<?php

// Disable caching and error reporting
error_reporting(0);
header('Cache-Control: no-store, no-cache, must-revalidate, max-age=0');
header('Pragma: no-cache');
header('Expires: 0');

// Check if the setup is already completed
$setupStatus = @file_get_contents('http://backend:8080/private/setup/status');
if ($setupStatus !== 'account') {
    header('Location: ./setup', true, 301);
    exit();
}

// Redirect to login page
header('Location: ./app/login', true, 301);
exit();