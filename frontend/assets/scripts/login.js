function signIn() {

    event.preventDefault();

    const email = document.getElementById("login-email").value;
    const password = document.getElementById("login-password").value;

    document.getElementById("login-email-message").innerHTML = "";
    document.getElementById("login-password-message").innerHTML = "";

    let dataValid = true;

    // Check if email and password are not empty
    if (email === "" || email == null) {
        document.getElementById("login-email-message").innerHTML = "This field is required";
        dataValid = false;
    } else if (!/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/.test(email)) {
        document.getElementById("login-email-message").innerHTML = "Please enter a valid email address";
        dataValid = false;
    }

    if (password === "" || password == null) {
        document.getElementById("login-password-message").innerHTML = "This field is required";
        dataValid = false;
    } else if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,64}$/.test(password)) {
        document.getElementById("login-password-message").innerHTML = "Password must be 8 characters long, contain one uppercase letter, one lowercase letter, one number and one special character";
        dataValid = false;
    }

    if (!dataValid) {
        return;
    }

    document.getElementById("login-button").disabled = true;
    document.getElementById("login-button").innerHTML = "Signing in...";

    fetch("/api/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            email: email,
            password: password
        })
    }).then((response) => {
        response.json().then((json) => {
            if (response.ok) {
                setCookie("knownoraUserId", json.userId, 365);
                setCookie("knownoraSessionId", json.id, 7);
                setCookie("knownoraSessionToken", json.token, 7);
                window.location.href = "/app";
            } else {
                document.getElementById("login-button").disabled = false;
                document.getElementById("login-button").innerHTML = "Sign In";
                document.getElementById("login-general-message").innerHTML = json[0];
            }
        }).catch((error) => {
            document.getElementById("login-button").disabled = false;
            document.getElementById("login-button").innerHTML = "Sign In";
            document.getElementById("login-general-message").innerHTML = "An unknown error occurred. This is likely not your fault. Please try again later or contact an administrator if the issue persists.";
        })

    }).catch((error) => {
        document.getElementById("login-button").disabled = false;
        document.getElementById("login-button").innerHTML = "Sign In";
        document.getElementById("login-general-message").innerHTML = "An unknown error occurred. This is likely not your fault. Please try again later or contact an administrator if the issue persists.";
    })

}