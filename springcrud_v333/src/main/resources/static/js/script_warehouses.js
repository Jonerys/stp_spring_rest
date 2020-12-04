function getData() {
    $.ajax({
        url: "/getwarehouseslist",
        type: "GET",
        success: function (data) {
            let table = document.getElementById("main_table");
            table.replaceChild(document.createElement("tbody"), table.children[0]);
            let body = table.children[0];
            let tr = document.createElement("tr");
            let td = document.createElement("th");
            td.innerText = "ИД"
            tr.appendChild(td)
            td = document.createElement("th");
            td.innerText = "Название"
            tr.appendChild(td)
            body.appendChild(tr);
            for (let i = 0; i < data.length; i++) {
                let el = data[i]
                tr = document.createElement("tr")
                td = document.createElement("td")
                td.innerText = el["id"]
                tr.appendChild(td);
                td = document.createElement("td")
                td.innerText = el["name"]
                tr.appendChild(td)
                body.appendChild(tr)
            }
        }, error: function (jqXHR, textStatus, errorThrown) {
        }
    });
}
function changeVisibility(sid, hid) {
    let showThis = document.getElementById(sid)
    showThis.style.visibility = "hidden";
    let hideThis = document.getElementById(hid)
    hideThis.style.visibility = "visible";
    switch(hid){
        case 'add':
            reset(1);
            break;
        case 'update':
            reset(2);
            break;
        case 'delete':
            reset(3);
            break;
        default:
            break;
    }
}