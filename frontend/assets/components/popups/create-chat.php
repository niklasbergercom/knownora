<div id="popup-inner">
    <button onclick="togglePopup(false)" id="popup-close">
        <img src="/assets/icons/xmark-solid-0c0501.svg" alt="Close" title="Close Popup">
    </button>
    <h1>Create a new conversation</h1>
    <form id="create-chat-form">
        <label for="create-chat-search">Search by email or name</label><br>
        <input type="text" placeholder="Type here..." id="create-chat-search" oninput="createChatSearchUsers()" name="create-chat-search" required>
    </form>
    <div id="create-chat-results">
        <p>Start typing, suggestions will appear here.</p>
    </div>
</div>