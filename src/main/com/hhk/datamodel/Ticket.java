package com.hhk.datamodel;
/**
 * @author hehuikang
 * @description
 * @date 2019-02-15 13:25
 */
public class Ticket {

    private int parkingLotNum;//停车场编号

    public int getParkingLotNum() {
        return parkingLotNum;
    }

    public Ticket(){}

    public Ticket(int parkingLotNum){
        this.parkingLotNum = parkingLotNum;
    }
}
