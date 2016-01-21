function getXMLHttpRequest() {
    var xmlHttpReq = false;
    if (window.XMLHttpRequest) {
        xmlHttpReq = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        try {
            xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (exp1) {
            try {
                xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (exp2) {
                xmlHttpReq = false;
            }
        }
    }
    return xmlHttpReq;
}
function addGarmentToOrder(id) {
    var xmlHttpRequest = getXMLHttpRequest();
    xmlHttpRequest.onreadystatechange = getReadyStateHandler(xmlHttpRequest);
    xmlHttpRequest.open("POST", "/store/book?id" + id, true);
    xmlHttpRequest.setRequestHeader("Content-Type",
        "application/x-www-form-urlencoded");
    xmlHttpRequest.send(null);
}

function getReadyStateHandler(xmlHttpRequest) {
    if (xmlHttpRequest.readyState == 4) {
        if (xmlHttpRequest.status == 200) {
            alert("HTTP OK " + xmlHttpRequest.status + ": " + xmlHttpRequest.statusText);
            alert(xmlHttpRequest.responseText);
            document.getElementById("order").innerHTML = xmlHttpRequest.responseText;   //doesn't work for now
        } else {
            alert("HTTP error " + xmlHttpRequest.status + ": " + xmlHttpRequest.statusText);
        }
    }
}