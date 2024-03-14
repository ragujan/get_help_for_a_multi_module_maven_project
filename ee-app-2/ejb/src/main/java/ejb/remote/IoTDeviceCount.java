package ejb.remote;

import java.io.Serializable;
import java.util.List;

public interface IoTDeviceCount extends Serializable {
    public List<String> getDeviceIds();
}
