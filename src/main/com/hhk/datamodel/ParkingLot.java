package com.hhk.datamodel;


import com.hhk.exception.ParkingLotIsFullException;
import com.hhk.exception.CarNotExistOrTakedException;
import com.hhk.exception.TicketErrorException;

import java.util.HashMap;
import java.util.Map;
/**
 * @author hehuikang
 * @description
 * @date 2019-02-15 13:25
 */
public class ParkingLot {

    private int allSpace;//总停车位
    private int restSpace;//剩余空车位
    private Map<Ticket, Car> parkingCarMap = new HashMap();//停的车的集合
    private int num;//停车场编号
    private boolean parked;//记录一个循环里该停车场是否已停车

    public boolean getParked() {
        return parked;
    }

    public void setParked(boolean parked) {
        this.parked = parked;
    }

    public int getNum() {
        return num;
    }

    public Map<Ticket, Car> getPakringCarMap() {
        return parkingCarMap;
    }

    public int getAllSpace() {
        return allSpace;
    }

    public int getRestSpace() {
        return restSpace;
    }

    public void setRestSpace(int restSpace) {
        this.restSpace = restSpace;
    }

    public ParkingLot(int restSpace, int num) {
        this.restSpace = restSpace;
        this.num = num;
    }
    public ParkingLot(int allSpace,int restSpace,int num) {
        this.allSpace = allSpace;
        this.restSpace = restSpace;
        this.num = num;
    }

    public Ticket park(Car car) {
        if(this.getRestSpace() <= 0 ){
            throw new ParkingLotIsFullException();
        }
        Ticket ticket = new Ticket(num);
        parkingCarMap.put(ticket,car);
        this.setParked(true);
        this.setRestSpace(this.getRestSpace() - 1);
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
        this.setParked(false);
        this.setRestSpace(this.getRestSpace() + 1);
        return takeCar;
    }
}
