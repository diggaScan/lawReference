package com.sunland.lawreference.bean;

/**
 * Created by PeitaoYe
 * On 2018/8/27
 **/
public class BaseRequestBean {
    private String yhdm;
    private String imei;
    private String imsi;
    private String pdaTime;
    private String gpsX;
    private String gpsY;


    public String getYhdm() {
        return yhdm;
    }

    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getPdaTime() {
        return pdaTime;
    }

    public void setPdaTime(String pdaTime) {
        this.pdaTime = pdaTime;
    }

    public String getGpsX() {
        return gpsX;
    }

    public void setGpsX(String gpsX) {
        this.gpsX = gpsX;
    }

    public String getGpsY() {
        return gpsY;
    }

    public void setGpsY(String gpsY) {
        this.gpsY = gpsY;
    }
}
