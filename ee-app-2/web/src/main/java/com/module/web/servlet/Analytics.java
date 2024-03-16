package com.module.web.servlet;

import ejb.impl.IoTDeviceReadingData;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedList;


@WebServlet(name = "Analytics", value = "/analytics")
public class Analytics extends HttpServlet {

    @EJB
    IoTDeviceReadingData data;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.getWriter().write("hey bro");
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Item 1");
        linkedList.add("Item 2");
        linkedList.add("Item 3");

        // Set the linked list as an attribute in the request object
        request.setAttribute("linkedList", linkedList);

        request.setAttribute("dataList",data.getAllReadings());

        request.setAttribute("name", "ragbag");
        try {
            request.getRequestDispatcher("analytics_view.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
