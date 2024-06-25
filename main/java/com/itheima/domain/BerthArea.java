package com.itheima.domain;
import java.io.Serializable;

public class BerthArea implements Serializable {
    private int id;
    private String iban;
    private int size;
    private int damaged;
    private int repairsCost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDamaged() {
        return damaged;
    }

    public void setDamaged(int damaged) {
        this.damaged = damaged;
    }

    public int getRepairsCost() {
        return repairsCost;
    }

    public void setRepairsCost(int repairsCost) {
        this.repairsCost = repairsCost;
    }
}
