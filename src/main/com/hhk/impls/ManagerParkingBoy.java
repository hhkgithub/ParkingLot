package com.hhk.impls;

import com.hhk.datamodel.AbstractParkingBoy;
import com.hhk.datamodel.Car;
import com.hhk.datamodel.ParkingLot;
import com.hhk.datamodel.Ticket;
import com.hhk.exception.NoPermissionDispatchThisParkingBoyException;
import com.hhk.interfaces.ParkingBoyDispatch;

import java.util.ArrayList;
import java.util.List;
/**
 * @author hehuikang
 * @description 停车经理
 * @date 2019-02-15 13:25
 */
public class ManagerParkingBoy extends AbstractParkingBoy implements ParkingBoyDispatch {

    private List<AbstractParkingBoy> parkingBoyList = new ArrayList<>();

    public List<AbstractParkingBoy> getParkingBoyList() {
        return parkingBoyList;
    }

    public void setParkingBoyList(List<AbstractParkingBoy> parkingBoyList) {
        this.parkingBoyList = parkingBoyList;
    }

    @Override
    protected Ticket doParkingCarDispatch(Car car, List<ParkingLot> parkingLotList) {
        return new Ticket();
    }

    @Override
    public Ticket parkingBoyDispatch(Car car, AbstractParkingBoy parkingBoy) {
        if (parkingBoyList.contains(parkingBoy)){
            return parkingBoy.parkingLotDispatch(car);
        }
        throw new NoPermissionDispatchThisParkingBoyException();//无权调度指定的停车小弟
    }
}
