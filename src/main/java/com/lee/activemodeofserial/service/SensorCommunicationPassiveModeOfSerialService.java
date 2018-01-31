package com.lee.activemodeofserial.service;

import com.lee.activemodeofserial.entity.SerialPortInfo;
import gnu.io.NoSuchPortException;

import java.util.TooManyListenersException;

public interface SensorCommunicationPassiveModeOfSerialService {
    void start(SerialPortInfo serialPortInfo) throws NoSuchPortException, TooManyListenersException;
    void restart(SerialPortInfo serialPortInfo) throws NoSuchPortException, TooManyListenersException;
    void close();
}
