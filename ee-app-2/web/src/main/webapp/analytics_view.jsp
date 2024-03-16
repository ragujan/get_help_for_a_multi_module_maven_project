<%@ page import="java.util.LinkedList" %><%--
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
</head>
<body>
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
</body>
</html>
