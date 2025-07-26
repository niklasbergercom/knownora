function pageBuild() {

    const chatList = document.getElementById("chat-sidebar-list");

    fetch("/api/chat/get-my-chats", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            userId: getCookie("knownoraUserId"),
            sessionId: getCookie("knownoraSessionId"),
            sessionToken: getCookie("knownoraSessionToken"),
            limit: 50
        })
    }).then((response) => {
        response.json().then((json) => {
            if (response.ok) {
                chatList.innerHTML = ""; // Clear existing chats

                if (json.length === 0) {
                    chatList.innerHTML = '<p>No chats yet. Start a new conversation.</p>';
                    return;
                }

                json.forEach(chat => {
                    const chatItem = document.createElement("div");
                    chatItem.id = `chat-item-${chat.id}`;
                    chatItem.innerHTML = `
                        <div>
                            <img src="${chat.picture}" alt="${chat.name}" title="${chat.name}">
                        </div>
                        <div>
                            <b>${chat.name}</b><br>
                            <span>${chat.preview}</span>
                        </div>
                    `;
                    chatItem.onclick = () => {
                        openChat(chat.id)
                    };
                    chatList.appendChild(chatItem);
                });
            } else {
                console.error("Error fetching chats:", json[0]);
            }
        }).catch((error) => {
            chatList.innerHTML = '<p>Error loading chats. Please try again later.</p>';
            console.error(error);
        })
    }).catch((error) => {
        chatList.innerHTML = '<p>Error loading chats. Please try again later.</p>';
        console.error(error);
    })

}


// function openChat(chatId) {
//
//     document.getElementById("chat-focused").id = "";
//     try {
//         document.getElementById("chat-item-" + chatId).id = "chat-focused";
//     } catch (error) {
//         console.warn(error)
//     }
//
//     const chatContent = document.getElementById("chat-content");
//
//     fetch("/api/chat/get-chat-messages", {
//         method: "POST",
//         headers: {
//             "Content-Type": "application/json"
//         },
//         body: JSON.stringify({
//             userId: getCookie("knownoraUserId"),
//             sessionId: getCookie("knownoraSessionId"),
//             sessionToken: getCookie("knownoraSessionToken"),
//             chatId: chatId,
//             limit: 50
//         })
//     })
//
// }