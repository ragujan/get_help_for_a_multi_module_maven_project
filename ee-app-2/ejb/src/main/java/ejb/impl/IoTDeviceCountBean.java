package ejb.impl;

import core.DeviceManagerUtil;
import ejb.remote.IoTDeviceCount;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class IoTDeviceCountBean implements IoTDeviceCount {
    @Override
    public List<String> getDeviceIds() {

        return null;
//        return DeviceManagerUtil.getIoTDevices(DeviceManagerUtil.getConnection());
    }
}
