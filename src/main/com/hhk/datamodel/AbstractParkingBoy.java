package com.hhk.datamodel;

import com.hhk.exception.NoParkingLotAvailableException;
import com.hhk.interfaces.ParkingLotDispatch;

import java.util.*;
/**
 * @author hehuikang
 * @description 停车小弟调度名下的停车场
 * @date 2019-02-15 13:25
 */
public abstract class AbstractParkingBoy implements ParkingLotDispatch {

    private String parkingBoyNum;
    private List<ParkingLot> parkingLotList = new ArrayList<>();//纳入管理的停车场

    public String getParkingBoyNum() {
        return parkingBoyNum;
    }

    public void setParkingBoyNum(String parkingBoyNum) {
        this.parkingBoyNum = parkingBoyNum;
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public void setParkingLotList(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    @Override
    public final Ticket parkingLotDispatch(Car car) {
        if(this.parkingLotList.size() == 0){
            throw new NoParkingLotAvailableException();
        }
        return doParkingCarDispatch(car,this.parkingLotList);
    }

    protected abstract Ticket doParkingCarDispatch(Car car,List<ParkingLot> parkingLotList);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractParkingBoy that = (AbstractParkingBoy) o;
        return parkingBoyNum.equals(that.parkingBoyNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingBoyNum);
    }
}
