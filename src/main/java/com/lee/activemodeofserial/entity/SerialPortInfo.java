package com.lee.activemodeofserial.entity;

public class SerialPortInfo extends EntityBase {
    private Integer baudRate;
    private Integer dataBits;
    private Integer stopBits;
    private Integer parity;

    public SerialPortInfo() {
        this("COM1", 9600, 8, 1, 0);
    }

    public SerialPortInfo(String name, Integer baudRate, Integer dataBits,
                      Integer stopBits, Integer parity) {
        super(0L, name);
        this.baudRate = baudRate;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;
    }

    public Integer getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(Integer baudRate) {
        this.baudRate = baudRate;
    }

    public Integer getDataBits() {
        return dataBits;
    }

    public void setDataBits(Integer dataBits) {
        this.dataBits = dataBits;
    }

    public Integer getStopBits() {
        return stopBits;
    }

    public void setStopBits(Integer stopBits) {
        this.stopBits = stopBits;
    }

    public Integer getParity() {
        return parity;
    }

    public void setParity(Integer parity) {
        this.parity = parity;
    }
}
