package com.lee.activemodeofserial.service;

import com.lee.activemodeofserial.entity.SerialPortInfo;
import gnu.io.NoSuchPortException;

import java.io.IOException;
import java.util.TooManyListenersException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SensorCommunicationActiveModeOfSerialServiceImpl extends SerialPort implements SensorCommunicationActiveModeOfSerialService {
    private ScheduledExecutorService service;

    // 读取串口信息
    @Override
    protected void read(byte[] command) {
        for (byte bt : command)
            System.out.print(Integer.toHexString(bt & 0xff ) + " ");
        System.out.println();
    }

    @Override
    public void start(SerialPortInfo serialPortInfo, int timer) {
        try {
            connect(serialPortInfo);

            service = Executors.newSingleThreadScheduledExecutor();
            // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
            service.scheduleAtFixedRate(runnable, 10, timer, TimeUnit.SECONDS);

        } catch (NoSuchPortException e) {
            e.printStackTrace();
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void restart(SerialPortInfo serialPortInfo, int timer) {
        close();
        start(serialPortInfo, timer);
    }

    @Override
    public void close() {
        if (!service.isShutdown())
            service.shutdown();
        System.out.println(service.isShutdown());

        if (isConnected())
            super.close();
        System.out.println(isConnected());
    }

    // 任务内容
    private Runnable runnable = new Runnable() {
        public void run() {
            try {
                System.out.println("Hello !!");
                write("Hello Serial Port".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}
