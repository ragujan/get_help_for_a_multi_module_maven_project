<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 3/18/2024
  Time: 3:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            font-size: 80%;
            background-color: #1f1f1f;
        }

        #wrapper {
            width: 960px;
            margin: auto;
            text-align: left;
            color: #d9d9d9;
        }

        p {
            text-align: left;
        }

        .button {
            display: inline;
            color: #fff;
            background-color: #f2791d;
            padding: 8px;
            margin: auto;
            border-radius: 8px;
            -moz-border-radius: 8px;
            -webkit-border-radius: 8px;
            box-shadow: none;
            border: none;
        }

        .button:hover {
            background-color: #ffb15e;
        }

        .button a, a:visited, a:hover, a:active {
            color: #fff;
            text-decoration: none;
        }

        #addDevice {
            text-align: center;
            width: 960px;
            margin: auto;
            margin-bottom: 10px;
        }

        #addDeviceForm {
            text-align: left;
            width: 400px;
            margin: auto;
            padding: 10px;
        }

        #addDeviceForm span {
            display: block;
        }

        #content {
            margin: auto;
            width: 960px;
        }

        .device {
            width: 180px;
            height: 110px;
            margin: 10px;
            padding: 16px;
            color: #fff;
            vertical-align: top;
            border-radius: 8px;
            -moz-border-radius: 8px;
            -webkit-border-radius: 8px;
            display: inline-block;
        }

        .device.off {
            background-color: #c8cccf;
        }

        .device span {
            display: block;
        }

        .deviceName {
            text-align: center;
            font-weight: bold;
            margin-bottom: 12px;
        }

        .removeDevice {
            margin-top: 12px;
            text-align: center;
        }

        .device.Appliance {
            background-color: #5eb85e;
        }

        .device.Appliance a:hover {
            color: #a1ed82;
        }

        .device.Electronics {
            background-color: #0f90d1;
        }

        .device.Electronics a:hover {
            color: #4badd1;
        }

        .device.Lights {
            background-color: #c2a00c;
        }

        .device.Lights a:hover {
            color: #fad232;
        }

        .device.Other {
            background-color: #db524d;
        }

        .device.Other a:hover {
            color: #ff907d;
        }

        .device a {
            text-decoration: none;
        }

        .device a:visited, a:active, a:hover {
            color: #fff;
        }

        .device a:hover {
            text-decoration: underline;
        }


    </style>
</head>
<body>
<div id="wrapper">
    <h1>Java Websocket Home</h1>
    <p>Welcome to the Java WebSocket Home. Click the Add a device button to start adding devices.</p>
    <br/>
    <div id="addDevice">
        <%--        <form id="addDeviceForm">--%>
        <div id="form">
            <h3>Add a new device</h3>
            <span>Name: <input type="text" name="device_name" id="device_name"></span>
            <span>Type:
                        <select id="device_type">
                            <option name="type" value="Appliance">Appliance</option>
                            <option name="type" value="Electronics">Electronics</option>
                            <option name="type" value="Lights">Lights</option>
                            <option name="type" value="Other">Other</option>
                        </select></span>

            <span>Description:<br/>
                        <textarea name="description" id="device_description" rows="2" cols="50"></textarea>
                    </span>

            <input type="button" class="button" value="Add" onclick=formSubmit()>

            <%--        </form>--%>
        </div>
    </div>
    <br/>
    <h3>Currently connected devices:</h3>
    <div id="content">
    </div>
</div>
<script src="test.js"></script>
</body>
</html>
