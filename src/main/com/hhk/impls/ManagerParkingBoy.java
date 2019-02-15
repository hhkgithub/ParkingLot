package com.hhk.impls;

import com.hhk.datamodel.AbstractParkingBoy;
import com.hhk.datamodel.Car;
import com.hhk.datamodel.ParkingLot;
import com.hhk.datamodel.Ticket;

import java.util.ArrayList;
import java.util.List;
/**
 * @author hehuikang
 * @description
 * @date 2019-02-15 13:25
 */
public class ManagerParkingBoy extends AbstractParkingBoy {

    private List<AbstractParkingBoy> list = new ArrayList<>();

    @Override
    protected Ticket doParkingDispatch(Car car, List<ParkingLot> parkingLotList) {
        return null;
    }
}
