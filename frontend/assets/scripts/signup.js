function signUp() {

    event.preventDefault();

    const name = document.getElementById("login-name").value;
    const email = document.getElementById("login-email").value;
    const password = document.getElementById("login-password").value;
    const passwordRepeat = document.getElementById("login-password-repeat").value;
    const grade = document.getElementById("login-grade").value;

    document.getElementById("login-name-message").innerHTML = "";
    document.getElementById("login-email-message").innerHTML = "";
    document.getElementById("login-password-message").innerHTML = "";
    document.getElementById("login-password-repeat-message").innerHTML = "";
    document.getElementById("login-grade-message").innerHTML = "";

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

    if (grade === "" || grade == null) {
        document.getElementById("login-grade-message").innerHTML = "This field is required";
        dataValid = false;
    } else if (!signUpGrades.includes(grade)) {
        document.getElementById("login-grade-message").innerHTML = "Please select a valid grade";
        dataValid = false;
    }

    if (!dataValid) {
        return;
    }

    document.getElementById("login-button").disabled = true;
    document.getElementById("login-button").innerHTML = "Please wait...";

    fetch("/api/auth/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            friendlyName: name,
            email: email,
            password: password,
            grade: grade
        })
    }).then((response) => {
        response.json().then((json) => {
            if (response.ok) {

                window.location.href = "/app";
            } else {
                document.getElementById("login-button").disabled = false;
                document.getElementById("login-button").innerHTML = "Continue";
                document.getElementById("login-general-message").innerHTML = json[0];
            }
        }).catch((error) => {
            document.getElementById("login-button").disabled = false;
            document.getElementById("login-button").innerHTML = "Continue";
            document.getElementById("login-general-message").innerHTML = "An unknown error occurred. This is likely not your fault. Please try again later or contact an administrator if the issue persists.";
        })

    }).catch((error) => {
        document.getElementById("login-button").disabled = false;
        document.getElementById("login-button").innerHTML = "Continue";
        document.getElementById("login-general-message").innerHTML = "An unknown error occurred. This is likely not your fault. Please try again later or contact an administrator if the issue persists.";
    })

}


// Wait for the DOM to be fully loaded before executing the script
document.addEventListener("DOMContentLoaded", function () {

    signUpGrades.forEach((grade) => {
        document.getElementById("login-grade").innerHTML += `<option value="${grade}">${grade}</option>`;
    })

});