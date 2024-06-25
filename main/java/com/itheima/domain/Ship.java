package com.itheima.domain;
import java.io.Serializable;
public class Ship implements Serializable {
    private Integer id;         //船只编号
    private String sin;         //船舶识别号
    private String model;       //船只型号
    private Integer tonnage;    //船只吨位(0→小型;1→中型;2→大型船只)
    private double netWeight;   //净重(通常小型≤10，中型[100,1000],大型为10,000~1,000,000吨)
    private String captainId;       //船长ID
    private String captainName;     //船只船长
    private Integer status;//船只状态(0→已出港；1→停泊中；2→登记出港(结算停泊费用))
    private String currentLocation;     //船只当前位置(status为0时,该值为null,否则为ship_bething表中的iban值)
    private String berthTime; //船只进港停泊时间
    private String exitTime; //船只出港时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSin() {
        return sin;
    }

    public void setSin(String sin) {
        this.sin = sin;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getTonnage() {
        return tonnage;
    }

    public void setTonnage(Integer tonnage) {
        this.tonnage = tonnage;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }

    public String getCaptainId() {
        return captainId;
    }

    public void setCaptainId(String captainId) {
        this.captainId = captainId;
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
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
}
