let profileSidebarOpened = false;

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