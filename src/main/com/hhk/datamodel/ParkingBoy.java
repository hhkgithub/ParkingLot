package com.hhk.datamodel;

import com.hhk.exception.NoParkingLotAvailableException;
import com.hhk.interfaces.ParkingDispatch;

import java.util.ArrayList;
import java.util.List;

public abstract class ParkingBoy implements ParkingDispatch {

    //停车小弟手下管理的停车场
    private List<ParkingLot> parkingLotList = new ArrayList<>();

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public void setParkingLotList(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    @Override
    public  void parkingDispatch(Car car) {
        if(parkingLotList.size() == 0){
            throw new NoParkingLotAvailableException();
        }
        doParkingDispatch(car);
    }

    protected abstract void doParkingDispatch(Car car);

}
