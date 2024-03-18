window.onload = init;
var socket = new WebSocket("ws://localhost:8080/ragbagtest/analytics-live");
socket.onmessage = onMessage;
console.log(socket.onmessage)
function onMessage(event) {
    var device = JSON.parse(event.data);
    console.log(device)
    if (device.action === "add") {
        // printDeviceElement(device);
    }

}

function addDevice(name, type, description) {
    var DeviceAction = {
        action: "add",
        name: name,
        type: type,
        description: description
    };
    socket.send(JSON.stringify(DeviceAction));
}

function removeDevice(element) {
    var id = element;
    var DeviceAction = {
        action: "remove",
        id: id
    };
    socket.send(JSON.stringify(DeviceAction));
}

function toggleDevice(element) {
    var id = element;
    var DeviceAction = {
        action: "toggle",
        id: id
    };
    socket.send(JSON.stringify(DeviceAction));
}

function printDeviceElement(device) {
    var content = document.getElementById("content");

    var deviceDiv = document.createElement("div");
    deviceDiv.setAttribute("id", device.id);
    deviceDiv.setAttribute("class", "device " + device.type);
    content.appendChild(deviceDiv);

    var deviceName = document.createElement("span");
    deviceName.setAttribute("class", "deviceName");
    deviceName.innerHTML = device.name;
    deviceDiv.appendChild(deviceName);

    var deviceType = document.createElement("span");
    deviceType.innerHTML = "<b>Type:</b> " + device.type;
    deviceDiv.appendChild(deviceType);

    var deviceStatus = document.createElement("span");
    if (device.status === "On") {
        deviceStatus.innerHTML = "<b>Status:</b> " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn off</a>)";
    } else if (device.status === "Off") {
        deviceStatus.innerHTML = "<b>Status:</b> " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn on</a>)";
        //deviceDiv.setAttribute("class", "device off");
    }
    deviceDiv.appendChild(deviceStatus);

    var deviceDescription = document.createElement("span");
    deviceDescription.innerHTML = "<b>Comments:</b> " + device.description;
    deviceDiv.appendChild(deviceDescription);

    var removeDevice = document.createElement("span");
    removeDevice.setAttribute("class", "removeDevice");
    removeDevice.innerHTML = "<a href=\"#\" OnClick=removeDevice(" + device.id + ")>Remove device</a>";
    deviceDiv.appendChild(removeDevice);
}



function formSubmit() {
    var form = document.getElementById("form");
    var name = document.getElementById("device_name").value;
    var type = document.getElementById("device_type").value;
    var description = document.getElementById("device_description").value;
    // hideForm();
    // document.getElementById("addDeviceForm").reset();
    addDevice(name, type, description);
}

function init() {

}