function adminSignup() {

    event.preventDefault();

    const name = document.getElementById("login-name").value;
    const email = document.getElementById("login-email").value;
    const password = document.getElementById("login-password").value;
    const passwordRepeat = document.getElementById("login-password-repeat").value;

    document.getElementById("login-name-message").innerHTML = "";
    document.getElementById("login-email-message").innerHTML = "";
    document.getElementById("login-password-message").innerHTML = "";
    document.getElementById("login-password-repeat-message").innerHTML = "";

    let dataValid = true;

    if (name === "" || name == null) {
        document.getElementById("login-name-message").innerHTML = "This field is required";
        dataValid = false;
    }

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

    if (passwordRepeat === "" || passwordRepeat == null) {
        document.getElementById("login-password-repeat-message").innerHTML = "This field is required";
        dataValid = false;
    } else if (password !== passwordRepeat) {
        document.getElementById("login-password-repeat-message").innerHTML = "Passwords do not match";
        dataValid = false;
    }

    if (!dataValid) {
        return;
    }

    document.getElementById("login-button").disabled = true;
    document.getElementById("login-button").innerHTML = "Creating account...";

    fetch("/api/setup/create-admin-account", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            friendlyName: name,
            email: email,
            password: password
        })
    }).then((response) => {
        response.json().then((json) => {
            if (response.ok) {
                setCookie("knownoraUserId", json.userId, 365);
                setCookie("knownoraSessionId", json.id, 7);
                setCookie("knownoraSessionToken", json.token, 7);
                window.location.href = "../../app";
            } else {
                document.getElementById("login-button").disabled = false;
                document.getElementById("login-button").innerHTML = "Create account";
                document.getElementById("login-general-message").innerHTML = json[0];
            }
        }).catch((error) => {
            document.getElementById("login-button").disabled = false;
            document.getElementById("login-button").innerHTML = "Create account";
            document.getElementById("login-general-message").innerHTML = "An unknown error occurred. This is likely not your fault. Please try again later or contact an administrator if the issue persists.";
        })

    }).catch((error) => {
        document.getElementById("login-button").disabled = false;
        document.getElementById("login-button").innerHTML = "Create account";
        document.getElementById("login-general-message").innerHTML = "An unknown error occurred. This is likely not your fault. Please try again later or contact an administrator if the issue persists.";
    })

}


function selectTemplate() {

    event.preventDefault();

    const templateI = document.querySelector('input[name="i-am"]:checked').value;
    const templateFor = document.querySelector('input[name="for"]:checked').value;

    document.getElementById("template-button").disabled = true;
    document.getElementById("template-button").innerHTML = "Selecting...";

    fetch("/api/setup/select-template", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            i: templateI,
            for: templateFor
        })
    }).then((response) => {
        response.json().then((json) => {
            if (response.ok) {
                window.location.href = "../template";
            } else {
                document.getElementById("login-button").disabled = false;
                document.getElementById("login-button").innerHTML = "Select";
                document.getElementById("login-general-message").innerHTML = json[0];
            }
        }).catch((error) => {
            document.getElementById("login-button").disabled = false;
            document.getElementById("login-button").innerHTML = "Select";
            document.getElementById("login-general-message").innerHTML = "An unknown error occurred. This is likely not your fault. Please try again later or contact an administrator if the issue persists.";
        })

    }).catch((error) => {
        document.getElementById("login-button").disabled = false;
        document.getElementById("login-button").innerHTML = "Select";
        document.getElementById("login-general-message").innerHTML = "An unknown error occurred. This is likely not your fault. Please try again later or contact an administrator if the issue persists.";
    })

}