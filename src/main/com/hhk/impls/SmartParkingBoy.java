package com.hhk.impls;

import com.hhk.datamodel.AbstractParkingBoy;
import com.hhk.datamodel.Car;
import com.hhk.datamodel.ParkingLot;
import com.hhk.datamodel.Ticket;

import java.util.List;
/**
 * @author hehuikang
 * @description 聪明的停车小弟
 * @date 2019-02-15 13:25
 */
public class SmartParkingBoy extends AbstractParkingBoy {
    @Override
    public Ticket doParkingCarDispatch(Car car, List<ParkingLot> parkingLotList) {
        parkingLotList.sort((o1, o2) -> o2.getRestSpace() - o1.getRestSpace());
        return parkingLotList.get(0).park(car);
    }
}
