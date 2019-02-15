package com.hhk.impls;

import com.hhk.datamodel.AbstractParkingBoy;
import com.hhk.datamodel.Car;
import com.hhk.datamodel.ParkingLot;
import com.hhk.datamodel.Ticket;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author hehuikang
 * @description 超级停车小弟
 * @date 2019-02-15 13:25
 */
public class SuperParkingBoy extends AbstractParkingBoy {

    @Override
    protected Ticket doParkingCarDispatch(Car car, List<ParkingLot> parkingLotList) {
        parkingLotList.sort((o1, o2) -> {
            double vacancyRate1 = o1.getRestSpace() / (o1.getAllSpace()*1.0);
            double vacancyRate2 = o2.getRestSpace() / (o2.getAllSpace()*1.0);
            BigDecimal b1 = new BigDecimal(Double.toString(vacancyRate1));
            BigDecimal b2 = new BigDecimal(Double.toString(vacancyRate2));
            return b2.compareTo(b1);
        });
        return parkingLotList.get(0).park(car);
    }
}
