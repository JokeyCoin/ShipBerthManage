package com.itheima.domain;
import java.io.Serializable;
public class Record implements Serializable {
    private Integer id;        //停泊记录id
    private String shipModel;   //停泊船只的型号
    private String shipSin;   //停泊船只的唯一识别号
    private String shipCaptain;   //停泊船只的船长
    private double shipNetWeight;   //船只入港时的净重
    private String areaIban; //停泊区域编号
    private String berthTime; //入港停泊的时间
    private String exitTime; //离港的时间
    private double fee;//船只停泊费用

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getShipModel() {
        return shipModel;
    }
    public void setShipModel(String shipModel) {
        this.shipModel = shipModel;
    }
    public String getShipSin() {
        return shipSin;
    }
    public void setShipSin(String shipSin) {
        this.shipSin = shipSin;
    }
    public String getShipCaptain() {
        return shipCaptain;
    }
    public void setShipCaptain(String shipCaptain) {
        this.shipCaptain = shipCaptain;
    }
    public double getShipNetWeight() {
        return shipNetWeight;
    }
    public void setShipNetWeight(double shipNetWeight) {
        this.shipNetWeight = shipNetWeight;
    }
    public String getAreaIban() {
        return areaIban;
    }
    public void setAreaIban(String areaIban) {
        this.areaIban = areaIban;
    }
    public String getBerthTime() {
        return berthTime;
    }
    public void setBerthTime(String berthTime) {
        this.berthTime = berthTime;
    }
    public String getExitTime() {
        return exitTime;
    }
    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }
    public double getFee() {
        return fee;
    }
    public void setFee(double fee) {
        this.fee = fee;
    }
}
