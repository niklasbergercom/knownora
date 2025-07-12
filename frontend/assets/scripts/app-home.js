function pageBuild() {

    document.getElementById("sidebar-name").innerHTML = myInfo.friendlyName;
    document.getElementById("sidebar-email").innerHTML = myInfo.email;
    document.getElementById("sidebar-picture").src = myInfo.picture;
    document.getElementById("sidebar-open-image").src = myInfo.picture;
    document.getElementById("greeting-name").innerHTML = ", " + myInfo.friendlyName;

}