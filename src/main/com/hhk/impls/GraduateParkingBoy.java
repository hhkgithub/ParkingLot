package com.hhk.impls;


import com.hhk.datamodel.AbstractParkingBoy;
import com.hhk.datamodel.Car;
import com.hhk.datamodel.ParkingLot;
import com.hhk.datamodel.Ticket;

import java.util.*;
/**
 * @author hehuikang
 * @description  出入职场的停车小弟
 * @date 2019-02-15 13:25
 */
public class GraduateParkingBoy extends AbstractParkingBoy {

    @Override
    public Ticket doParkingCarDispatch(Car car, List<ParkingLot> parkingLotList) {

        for (ParkingLot parkingLot : parkingLotList) {
            if(!parkingLot.getParked()){
                return parkingLot.park(car);
            }
        }
        for (ParkingLot parkingLot : parkingLotList) {
            parkingLot.setParked(false);
        }
        return parkingLotList.get(0).park(car);
//        Map<Integer,ParkingLot> parkingLotMap =  super.getParkingLotMap();
//        int noParkedNum = 0;//判断未停车的停车场的数量
//        for(int i : parkingLotMap.keySet()){
//            ParkingLot parkingLot = parkingLotMap.get(i);
//            if(!parkingLot.getParked()){
//                return parkingLot.park(car);
//            }
//            noParkedNum++;
//            if(noParkedNum == parkingLotMap.size()){//判断未停车的停车场的数量，如果和停车场的数量一致，说明一轮停车结束，重置是否已停车的标识
//                parkingLot.setParked(false);
//            }
//        }
//        return ((ParkingLot)((TreeMap)parkingLotMap).firstEntry().getValue()).park(car);

//        for (int i = 0; i < parkingLotList.size() - 1; i++) {
//            ParkingLot preParkingLot = parkingLotList.get(i);
//            ParkingLot afterParkingLot = parkingLotList.get(i + 1);
//            if(afterParkingLot.getPakringCarMap().size() - preParkingLot.getPakringCarMap().size() < 0){
//                return afterParkingLot.park(car);
//
//            }else if (parkingLotList.size()  == i + 2){
//                return parkingLotList.get(0).park(car);
//            }
//        }
    }
}
