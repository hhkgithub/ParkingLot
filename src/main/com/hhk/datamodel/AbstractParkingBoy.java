package com.hhk.datamodel;

import com.hhk.exception.NoParkingLotAvailableException;
import com.hhk.interfaces.ParkingDispatch;

import java.util.*;
/**
 * @author hehuikang
 * @description
 * @date 2019-02-15 13:25
 */
public abstract class AbstractParkingBoy implements ParkingDispatch {

    private List<ParkingLot> parkingLotList = new ArrayList<>();//纳入管理的停车场

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public void setParkingLotList(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    @Override
    public final Ticket parkingDispatch(Car car) {
        if(this.parkingLotList.size() == 0){
            throw new NoParkingLotAvailableException();
        }
        return doParkingDispatch(car,this.parkingLotList);
    }

    protected abstract Ticket doParkingDispatch(Car car,List<ParkingLot> parkingLotList);

}
