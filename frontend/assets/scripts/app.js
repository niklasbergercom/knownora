let profileSidebarOpened = false;
let myInfo = {}

function toggleProfileSidebar(state = undefined) {
    const profileSidebarWrapper = document.getElementById('profile-sidebar-wrapper');
    const profileSidebar = document.getElementById('profile-sidebar');
    if (state === true || (state === undefined && !profileSidebarOpened)) {
        profileSidebarWrapper.style.display = "flex";
        setTimeout(() => {
            profileSidebarWrapper.style.backgroundColor = "rgba(0, 0, 0, 0.5)";
            profileSidebar.style.transform = "translateX(0)";
        }, 1)
        profileSidebarOpened = true;
    } else if (state === false || (state === undefined && profileSidebarOpened)) {
        profileSidebarWrapper.style.backgroundColor = "rgba(0, 0, 0, 0)";
        profileSidebar.style.transform = "translateX(100%)";
        setTimeout(() => {
            profileSidebarWrapper.style.display = "none";
        }, 200); // Match the transition duration in CSS
        profileSidebarOpened = false;
    }
}

function signOut() {
    fetch("/api/auth/logout", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            userId: getCookie("knownoraUserId"),
            sessionId: getCookie("knownoraSessionId"),
            sessionToken: getCookie("knownoraSessionToken")
        })
    }).then((response) => {
        setCookie("knownoraUserId", "", -1);
        setCookie("knownoraSessionId", "", -1);
        setCookie("knownoraSessionToken", "", -1);
        window.location.href = "/app/login?popup=logout-success";
    }).catch((error) => {
        setCookie("knownoraUserId", "", -1);
        setCookie("knownoraSessionId", "", -1);
        setCookie("knownoraSessionToken", "", -1);
        window.location.href = "/app/login?popup=logout-success";});
}

function defaultPageBuild() {
    document.getElementById("sidebar-name").innerHTML = myInfo.friendlyName;
    document.getElementById("sidebar-email").innerHTML = myInfo.email;
    document.getElementById("sidebar-picture").src = myInfo.picture;
    document.getElementById("sidebar-open-image").src = myInfo.picture;

    const pathLinkRelations = {
        "/app/": "header-link-app",
        "/app/chat/": "header-link-chat",
        "/app/calendar/": "header-link-calendar",
        "/app/classes/": "header-link-classes",
        "/app/school/": "header-link-school"
    }

    const currentPath = window.location.pathname;

    try {
        let linkId = pathLinkRelations[currentPath];
        if (linkId === null) { linkId = pathLinkRelations[currentPath + "/"]; }
        document.getElementById(linkId).classList.add("underlined-link-focused");
    } catch (error) {
        console.error("Error setting active link:", error);
    }
}

fetch("/api/account/my-info", {
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify({
        userId: getCookie("knownoraUserId"),
        sessionId: getCookie("knownoraSessionId"),
        sessionToken: getCookie("knownoraSessionToken")
    })
}).then((response) => {
    response.json().then((json) => {
        if (response.ok) {
            myInfo = json;
            if (myInfo.picture === "" || myInfo.picture === null) {
                myInfo.picture = "/assets/images/account-default.svg";
            }
            try {
                defaultPageBuild();
                pageBuild();
            } catch (error) {
                console.error("Error building page:", error);
            }
        } else if (json[0] === "Session expired") {
            window.location.href = "/app/login?popup=session-expired";
        } else {
            window.location.href = "/app/login";
        }
    }).catch((error) => {
        window.location.href = "/app/login";
    });
}).catch((error) => {
    console.error(error);
})