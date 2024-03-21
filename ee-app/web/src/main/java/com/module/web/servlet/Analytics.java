package com.module.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.model.TrafficFlowDataCarrier;
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


        LinkedList<TrafficFlowDataCarrier> trafficFlowAnalysisList = new LinkedList<>();

        for (int i = 1; i < 25; i++) {
            if (i % 2 == 0)
                trafficFlowAnalysisList.add(new TrafficFlowDataCarrier(i, data.getCalcuatedTrafficFlowByTime(i)));
        }
        for (TrafficFlowDataCarrier carrier : trafficFlowAnalysisList
        ) {
            System.out.println(carrier.getTimeInterval());
            System.out.println(carrier.getValue());
        }

        // Set the linked list as an attribute in the request object
        request.setAttribute("dataList", data.getAllReadings());
        request.setAttribute("averageVehicleSpeed", data.getAverageVehicleSpeed());
        request.setAttribute("averageTravelSpeed", data.getAverageTravelSpeed());
        request.setAttribute("dataFlowAnalysis", data.getTrafficFlowAnalysis());
        request.setAttribute("trafficFlowFromLastHour", data.getCalcuatedTrafficFlowByTime(1));
        request.setAttribute("trafficFlowFromLast5Hours", data.getCalcuatedTrafficFlowByTime(5));
        request.setAttribute("trafficFlowFromLast12Hours", data.getCalcuatedTrafficFlowByTime(12));
        request.setAttribute("trafficFlowFromLast24Hours", data.getCalcuatedTrafficFlowByTime(24));
        ObjectMapper mapper = new ObjectMapper();
        request.setAttribute("trafficFlowAnalysisList",trafficFlowAnalysisList);
        request.setAttribute("name", "ragbag");
        try {
            request.getRequestDispatcher("analytics_view.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

}
