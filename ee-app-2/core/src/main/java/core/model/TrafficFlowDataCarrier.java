package core.model;

public class TrafficFlowDataCarrier {
        double timeInterval;
        String value;

        public TrafficFlowDataCarrier(double timeInterval, String value) {
            this.timeInterval = timeInterval;
            this.value = value;
        }

        public double getTimeInterval() {
            return timeInterval;
        }

        public void setTimeInterval(double timeInterval) {
            this.timeInterval = timeInterval;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
}
