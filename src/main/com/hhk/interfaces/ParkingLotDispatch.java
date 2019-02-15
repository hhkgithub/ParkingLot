package com.hhk.interfaces;

import com.hhk.datamodel.Car;
import com.hhk.datamodel.Ticket;
/**
 * @author hehuikang
 * @description 调度
 * @date 2019-02-15 13:25
 */
public  interface ParkingLotDispatch {
     /**
      * 停车小弟停车
      * @param car
      * @return Ticket
      */
     Ticket parkingLotDispatch(Car car);
}
