package com.lee.activemodeofserial.service;

import com.lee.activemodeofserial.entity.SerialPortInfo;
import gnu.io.NoSuchPortException;

import java.io.IOException;
import java.util.TooManyListenersException;

public class SensorCommunicationPassiveModeOfSerialServiceImpl extends SerialPort implements SensorCommunicationPassiveModeOfSerialService {

    @Override
    protected void read(byte[] command) {
        for (byte bt : command)
            System.out.print(Integer.toHexString(bt & 0xff) + " ");
        System.out.println();

        System.out.println("执行业务 ...");

        try {
            write(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(SerialPortInfo serialPortInfo) throws NoSuchPortException, TooManyListenersException {
        connect(serialPortInfo);
    }

    @Override
    public void restart(SerialPortInfo serialPortInfo) throws NoSuchPortException, TooManyListenersException {
        close();
        start(serialPortInfo);
    }

    @Override
    public void close() {
        if (isConnected())
            super.close();
    }
}
