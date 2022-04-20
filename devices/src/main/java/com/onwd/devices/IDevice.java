package com.onwd.devices;

import java.io.Serializable;

public interface IDevice extends Serializable {
    String getName();

    String getFirmwareVersion();

    float getBatteryLevel();

    DeviceStatus getStatus();

    @DeviceType int getType();
}
