package ejb.remote;

import jakarta.ejb.Remote;

import java.io.Serializable;
import java.util.List;

@Remote
public interface IoTDeviceManager extends Serializable {
    List<String> getDeviceIds();
}
