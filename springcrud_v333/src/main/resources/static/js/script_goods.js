function getData() {
    $.ajax({
        url: "/getgoodslist",
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
            refreshSelect("select_u", data);
            refreshSelect("select_d", data);
            $('#select_u').trigger('change');
        }, error: function (jqXHR, textStatus, errorThrown) {
        }
    });
}
function refreshSelect(selID, data){
    let sel = document.getElementById(selID);
    while (sel.children.length != 0) {
        sel.removeChild(sel.firstChild);
    }
    for (let i = 0; i < data.length; i++) {
        let optSel = document.createElement("option");
        optSel.innerText = data[i]["id"];
        sel.appendChild(optSel);
    }
    sel.selectedIndex = 0;
}
function refreshField() {



}
function sendData(url, method, var1, var2) {
    $.ajax({
        url: url,
        method: method,
        data: {
            id: var1,
            name: var2
        },
        dataType: "json",
        success: function (){
        },
        error: function () {
            getData();
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
            break;
        case 'update':
            break;
        case 'delete':
            break;
        default:
            break;
    }
}