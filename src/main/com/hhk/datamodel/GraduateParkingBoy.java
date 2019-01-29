package com.hhk.datamodel;

import com.hhk.exception.NoParkingLotAvailableException;

import java.util.List;

public class GraduateParkingBoy extends ParkingBoy {
    /**
     * 停车调度
     */
    @Override
    public void doParkingDispatch(Car car) {
        List<ParkingLot> parkingLotList =  super.getParkingLotList();
        if(parkingLotList.size() == 0){
            throw new NoParkingLotAvailableException();
        }
        for (int i = 0; i < parkingLotList.size() - 1; i++) {
            ParkingLot preParkingLot = parkingLotList.get(i);
            ParkingLot afterParkingLot = parkingLotList.get(i+1);
            if(afterParkingLot.getPakringCarMap().size() - preParkingLot.getPakringCarMap().size() < 0){
                afterParkingLot.park(car);
                break;
            }
            parkingLotList.get(0).park(car);
        }
    }
}
