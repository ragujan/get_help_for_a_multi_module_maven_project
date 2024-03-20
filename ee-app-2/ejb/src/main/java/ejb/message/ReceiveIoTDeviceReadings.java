package ejb.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.model.IoTDataHolder;
import core.dto.IoTDeviceReadingStoreBeanDTO;
import core.model.TrafficFlowDataCarrier;
import ejb.impl.ClientSessionHandler;
import ejb.impl.DbConnectionBean;
import ejb.impl.IoTDeviceReadingData;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.LinkedList;
import java.util.List;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "transmitIoTReadings"),
        }
)
public class ReceiveIoTDeviceReadings implements MessageListener {
    @EJB
    DbConnectionBean dbConnectionBean;
    @EJB
    ClientSessionHandler clientSessionHandler;

    @EJB
    IoTDeviceReadingData data;

    @Override
    public void onMessage(Message message) {
        System.out.println("object is printed out");
        try {

            InitialContext context = new InitialContext();
            IoTDeviceReadingStoreBeanDTO dto = message.getBody(IoTDeviceReadingStoreBeanDTO.class);

            dbConnectionBean.addNewDevice(dto);
//            clientSessionHandlerBean.sendTextMessage("added a new dto bro");

            List<IoTDataHolder> dataList = data.getAllReadings();
            double averageVehicleSpeed = data.getAverageVehicleSpeed();
            double averageTravelSpeed = data.getAverageTravelSpeed();
            double dataFlowAnalysis = data.getTrafficFlowAnalysis();
            String trafficFlowFromLastHour = data.getCalcuatedTrafficFlowByTime(1);
            String trafficFlowFromLast5Hours = data.getCalcuatedTrafficFlowByTime(5);
            String trafficFlowFromLast12Hours = data.getCalcuatedTrafficFlowByTime(12);
            String trafficFlowFromLast24Hours = data.getCalcuatedTrafficFlowByTime(24);

            LinkedList<TrafficFlowDataCarrier> trafficFlowAnalysisList = new LinkedList<>();

            for (int i = 1; i < 25; i++) {
                if (i % 2 == 0)
                    trafficFlowAnalysisList.add(new TrafficFlowDataCarrier(i, data.getCalcuatedTrafficFlowByTime(i)));
            }
            ObjectMapper mapper = new ObjectMapper();
// Convert trafficFlowAnalysisList to JSON
            String trafficFlowAnalysisListJson = null;
            String dataListJson = null;
            try {
                dataListJson = mapper.writeValueAsString(dataList);
                trafficFlowAnalysisListJson = mapper.writeValueAsString(trafficFlowAnalysisList);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }



            JsonObjectBuilder builder = Json.createObjectBuilder();
//            clientSessionHandlerBean.sendTextMessage("added a new dto bro");

// Add variables to the JSON object
            builder.add("dataList", dataListJson);
            builder.add("averageVehicleSpeed", averageVehicleSpeed);
            builder.add("averageTravelSpeed", averageTravelSpeed);
            builder.add("dataFlowAnalysis", dataFlowAnalysis);
            builder.add("trafficFlowFromLastHour", trafficFlowFromLastHour);
            builder.add("trafficFlowFromLast5Hours", trafficFlowFromLast5Hours);
            builder.add("trafficFlowFromLast12Hours", trafficFlowFromLast12Hours);
            builder.add("trafficFlowFromLast24Hours", trafficFlowFromLast24Hours);
            builder.add("trafficFlowAnalysisList", trafficFlowAnalysisListJson);

// Build the JSON object
            JsonObject jsonObject = builder.build();
            clientSessionHandler.sendObjects(jsonObject);


        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }
}
