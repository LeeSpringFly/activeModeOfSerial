package com.lee.activemodeofserial.service;

import com.lee.activemodeofserial.entity.SerialPortInfo;

public interface SensorCommunicationActiveModeOfSerialService {
    void start(SerialPortInfo serialPortInfo, int timer);
    void close();
    void restart(SerialPortInfo serialPortInfo, int timer);
}
