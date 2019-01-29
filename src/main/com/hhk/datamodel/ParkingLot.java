package com.hhk.datamodel;


import com.hhk.exception.ParkingLotIsFullException;
import com.hhk.exception.CarNotExistOrTakedException;
import com.hhk.exception.TicketErrorException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private int restSpace;//剩余空车位
    private Map<Ticket, Car> parkingCarMap = new HashMap();//停的车的集合
    //private boolean parked;//记录一个循环里该停车场是否已停车

    public Map<Ticket, Car> getPakringCarMap() {
        return parkingCarMap;
    }

    public int getRestSpace() {
        return restSpace;
    }

    public ParkingLot(int restSpace) {
        this.restSpace=restSpace;
    }

    public Ticket park(Car car) {
        if(getRestSpace() == 0 ){
            throw new ParkingLotIsFullException();
        }
        Ticket ticket = new Ticket();
        parkingCarMap.put(ticket,car);
        return ticket;
    }

    public Car takeCar(Ticket ticket){
        if(ticket == null ){
            throw new TicketErrorException();
        }
        Car takeCar = parkingCarMap.get(ticket);
        if (takeCar == null){
            throw new CarNotExistOrTakedException();
        }
        parkingCarMap.remove(ticket);
        return takeCar;
    }
}
