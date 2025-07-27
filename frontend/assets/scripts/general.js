function setCookie(name, value, days) {
    const expires = new Date(Date.now() + days*24*60*60*1000).toUTCString();
    document.cookie = name + "=" + encodeURIComponent(value) + "; expires=" + expires + "; path=/";
}

function getCookie(name) {
    const value = "; " + document.cookie;
    const parts = value.split("; " + name + "=");
    if (parts.length === 2) { return decodeURIComponent(parts.pop().split(";").shift()); }
    return null;
}

function togglePopup(popupId) {
    const popupElement = document.getElementById("popup-wrapper");
    if (popupId === false) {

        popupElement.innerHTML = "";
        popupElement.style.opacity = "0";
        setTimeout(() => {
            popupElement.style.display = "none";
        }, 200);

    } else if (typeof popupId === "string" && popupId.trim() !== "") {

        fetch("/assets/components/popups/" + popupId + ".php").then((response) => {
            if (response.ok) {
                response.text().then((html) => {
                    popupElement.innerHTML = html;
                    popupElement.style.opacity = "0";
                    popupElement.style.display = "flex";
                    setTimeout(() => {
                        popupElement.style.opacity = "1";
                    }, 1);
                })
            }
        })

    } else {
        console.error("Invalid popupId provided:", popupId);
    }
}