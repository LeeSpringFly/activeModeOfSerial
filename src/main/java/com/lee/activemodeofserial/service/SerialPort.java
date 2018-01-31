package com.lee.activemodeofserial.service;

import com.lee.activemodeofserial.entity.SerialPortInfo;
import gnu.io.NRSerialPort;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.TooManyListenersException;

public abstract class SerialPort {
    private NRSerialPort serial;
    private DataOutputStream out;

    protected void connect(SerialPortInfo serialPortInfo) throws NoSuchPortException, TooManyListenersException {
        serial = new NRSerialPort(serialPortInfo.getName(), serialPortInfo.getBaudRate());
        if (!serial.connect()) {
            System.out.println("Error: 端口已经被占用");
            return;
        }

        DataInputStream ins = new DataInputStream(serial.getInputStream());
        out = new DataOutputStream(serial.getOutputStream());

        serial.addEventListener(new SerialReader(ins));
        serial.notifyOnDataAvailable(true);
    }

    public void close() {
        serial.disconnect();
    }

    protected void write(byte[] msg) throws IOException {
        out.write(msg);
    }

    protected void write(List<byte[]> msgArray) throws IOException {
        try {
            for (byte[] msg : msgArray)
                write(msg);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected boolean isConnected() {
        return serial.isConnected();
    }

    class SerialReader implements SerialPortEventListener {
        private InputStream in;
        private byte[] buffer;

        public SerialReader(InputStream in) {
            this.in = in;
        }

        public void serialEvent(SerialPortEvent serialPortEvent) {
            int data;
            int len = 0;
            try {
                buffer = new byte[in.available()];
                while ((data = in.read()) > -1) {
                    buffer[len++] = (byte) data;
                }
                for (byte bt : buffer)
                    System.out.print(Integer.toHexString(bt) + " ");
                System.out.println();

                read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    abstract void read(byte[] command);

}
