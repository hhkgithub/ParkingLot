package com.hhk.interfaces;

import com.hhk.datamodel.AbstractParkingBoy;
import com.hhk.datamodel.Car;
import com.hhk.datamodel.Ticket;

/**
 * @author hehuikang
 * @description
 * @date 2019-02-15 14:20
 */
public interface ParkingBoyDispatch {
    /**
     * 停车场经理调度停车小弟停车
     * @param car
     * @return Ticket
     */
    Ticket parkingBoyDispatch(Car car, AbstractParkingBoy parkingBoy);
}
