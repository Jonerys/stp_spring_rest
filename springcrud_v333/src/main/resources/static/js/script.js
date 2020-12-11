
function changeVisibility(sid, hid) {
    let showThis = document.getElementById(sid);
    showThis.style.visibility = "hidden";
    let hideThis = document.getElementById(hid);
    hideThis.style.visibility = "visible";
}