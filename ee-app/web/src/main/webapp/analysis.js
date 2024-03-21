window.onload = init;
var socket = new WebSocket("ws://localhost:8080/ragbagtest/analytics-live");
socket.onmessage = onMessage;
console.log(socket.onmessage)

function onMessage(event) {
    var device = JSON.parse(event.data);
    console.log(device)
    if (document.getElementById("trafficFlowLastHour")) {
        const elTrafficFlowLastHour = document.getElementById("trafficFlowLastHour");
        const elTrafficFlowLast5Hour = document.getElementById("trafficFlowLast5Hours");
        const elTrafficFlowLast12Hour = document.getElementById("trafficFlowLast12Hours");
        const elTrafficFlowLast24Hour = document.getElementById("trafficFlowLast24Hours");
        elTrafficFlowLastHour.innerHTML = device.trafficFlowFromLastHour;
        elTrafficFlowLast5Hour.innerHTML = device.trafficFlowFromLast5Hours;
        elTrafficFlowLast12Hour.innerHTML = device.trafficFlowFromLast12Hours;
        elTrafficFlowLast24Hour.innerHTML = device.trafficFlowFromLast24Hours;
    }

    if(document.getElementById("averageVehicleSpeed")){
        const averageVehicleSpeed = document.getElementById("averageVehicleSpeed");
        const averageTravelSpeed = document.getElementById("averageTravelSpeed");
        averageVehicleSpeed.innerHTML = device.averageVehicleSpeed;
        averageTravelSpeed.innerHTML = device.averageTravelSpeed;
    }
    ctx.getContext('2d').clearRect(0,0,ctx.width,ctx.height);
    myChart.destroy();
    // return;
    // const newCanv = document.createElement("canvas");
    // newCanv.id = "myChart";
    // document.getElementById("canvasContainer").appendChild(newCanv);
    const data = JSON.parse(device.trafficFlowAnalysisList);
    const timeStamps = data.map(obj => obj.timeInterval);
    const values = data.map(obj => obj.value);
    myChart = new Chart(ctx, {
        type: 'bubble',
        backgroundColor: "#FFB1C1",
        data: {
            labels: timeStamps,
            datasets: [{
                label: '# Traffic Flow Analysis',
                data: values,
                borderWidth: 5,
                backgroundColor: "#FF0000"
            }],
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            },
            plugins: {
                customCanvasBackgroundColor: {
                    color: 'lightGreen',
                }
            }
        },
        plugins: [plugin],
    });
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