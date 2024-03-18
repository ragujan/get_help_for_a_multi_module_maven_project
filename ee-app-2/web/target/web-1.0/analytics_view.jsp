<%@ page import="java.util.LinkedList" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %><%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 3/16/2024
  Time: 11:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <style>
        .big-bold-text {
            font-size: 32px;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div>
    <canvas id="myChart"></canvas>
</div>
<section>
    <h1>Traffic Flow Data</h1>

    <div style="display: flex; gap: 20px;justify-content: center;align-items: center">
        <h2>Last Hour</h2>
        <c:forEach var="flow" items="${trafficFlowFromLastHour}">
            <p id="trafficFlowLastHour" class="big-bold-text">${flow}</p>
        </c:forEach>

        <h2>Last 5 Hours</h2>
        <c:forEach var="flow" items="${trafficFlowFromLast5Hours}">
            <p id="trafficFlowLast5Hours" class="big-bold-text">${flow}</p>
        </c:forEach>

        <h2>Last 12 Hours</h2>
        <c:forEach var="flow" items="${trafficFlowFromLast12Hours}">
            <p id="trafficFlowLast12Hours" class="big-bold-text">${flow}</p>
        </c:forEach>

        <h2>Last 24 Hours</h2>
        <c:forEach var="flow" items="${trafficFlowFromLast24Hours}">
            <p id="trafficFlowLast24Hours" class="big-bold-text">${flow}</p>
        </c:forEach>

    </div>
</section>

<section style="padding: 20px">

    <h1>Average vehicle speed</h1>
    <div>
        <c:out value="${averageVehicleSpeed}"/>
    </div>
</section>
<section style="padding: 20px">

    <h1>Average Travel Speed </h1>
    <div>
        <c:out value="${averageTravelSpeed}"/>
    </div>
</section>


<h1>IoT Data:</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Device ID</th>
        <th>Registered At</th>
        <th>Speed</th>
        <th>Traffic Light Signal</th>
        <th>Longitude</th>
        <th>Latitude</th>
        <th>Direction</th>
    </tr>
    </thead>
    <tbody>
    <!-- Loop through the list of IoTDataHolder objects using EL -->
    <c:forEach items="${dataList}" var="data">
        <tr>
            <td>${data.id}</td>
            <td>${data.deviceId}</td>
            <td>${data.registeredAt}</td>
            <td>${data.speed}</td>
            <td>${data.trafficLightSignal}</td>
            <td>${data.longitude}</td>
            <td>${data.latitude}</td>
            <td>${data.direction}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%--data flow analysis--%>


</body>


<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>


    <%
ObjectMapper mapper = new ObjectMapper();
String trafficFlowJson = mapper.writeValueAsString(request.getAttribute("trafficFlowAnalysisList"));
%>
    const dataStr = '<%=trafficFlowJson%>'
    console.log(JSON.parse(dataStr))
    const data = JSON.parse(dataStr);
    const timeStamps = data.map(obj => obj.timeInterval);
    const values = data.map(obj => obj.value);

    const plugin = {
        id: 'customCanvasBackgroundColor',
        beforeDraw: (chart, args, options) => {
            const {ctx} = chart;
            ctx.save();
            ctx.globalCompositeOperation = 'destination-over';
            ctx.fillStyle = options.color || '#99ffff';
            ctx.fillRect(0, 0, chart.width, chart.height);
            ctx.restore();
        }
    };


    const ctx = document.getElementById('myChart');

    new Chart(ctx, {
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
</script>


<script src="test.js">

</script>
</html>
