package hhk;

import com.hhk.datamodel.AbstractParkingBoy;
import com.hhk.datamodel.Car;
import com.hhk.datamodel.ParkingLot;
import com.hhk.datamodel.Ticket;
import com.hhk.exception.NoParkingLotAvailableException;
import com.hhk.exception.NoPermissionDispatchThisParkingBoyException;
import com.hhk.impls.GraduateParkingBoy;
import com.hhk.impls.ManagerParkingBoy;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hehuikang
 * @description
 * @date 2019-02-15 13:25
 */
public class ManagerParkingBoyTest {
    /**
     * Given 0个停车场，1辆车
     * When  停车经理负责按停放给定的1辆车
     * Then  提示：无可供使用的停车场
     */
    @Test(expected = NoParkingLotAvailableException.class)
    public void should_throw_no_parking_lot_available_when_parked_given_zero_parkinglot_and_one_car(){
        AbstractParkingBoy managerParkingBoy = new ManagerParkingBoy();
        Car firstCar = new Car();
        managerParkingBoy.parkingLotDispatch(firstCar);
    }


    /**
     * Given 1个停车场，1辆车
     * When  停车经理停放给定的1辆车
     * Then  停车成功
     */
    @Test(expected = NoParkingLotAvailableException.class)
    public void should_return_ticket_when_parked_given_one_parkinglot_has_freespace_and_one_car(){
        AbstractParkingBoy managerParkingBoy = new ManagerParkingBoy();
        Car firstCar = new Car();
        Ticket ticket = managerParkingBoy.parkingLotDispatch(firstCar);
        Assert.assertNotNull(ticket);
    }

    /**
     * Given 停车经理名下1名停车小弟
     * When  停车经理调度停车小弟去停车
     * Then  成功停车
     */
    @Test
    public void should_return_ticket_when_parked_given_one_parkinglot_and_one_car(){

        AbstractParkingBoy graduateParkingBoy = new GraduateParkingBoy();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot(10,1,1);
        parkingLotList.add(firstParkingLot);
        graduateParkingBoy.setParkingBoyNum("007");
        graduateParkingBoy.setParkingLotList(parkingLotList);

        ManagerParkingBoy managerParkingBoy = new ManagerParkingBoy();
        List<AbstractParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoyList.add(graduateParkingBoy);
        managerParkingBoy.setParkingBoyList(parkingBoyList);


        Car car = new Car();
        Ticket ticket = managerParkingBoy.parkingBoyDispatch(car,graduateParkingBoy);
        Assert.assertNotNull(ticket);
    }
    /**
     * Given 停车经理名下1名停车小弟
     * When  停车经理调度停车小弟去停放车辆
     * Then  提示：无权限调度该停车小弟
     */
    @Test(expected = NoPermissionDispatchThisParkingBoyException.class)
    public void should_throw_no_permission_dispatch_when_parked_given_zero_parkinglot_and_one_car(){
        AbstractParkingBoy graduateParkingBoy = new GraduateParkingBoy();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot(10,1,1);
        parkingLotList.add(firstParkingLot);
        graduateParkingBoy.setParkingBoyNum("007");
        graduateParkingBoy.setParkingLotList(parkingLotList);

        ManagerParkingBoy managerParkingBoy = new ManagerParkingBoy();
        List<AbstractParkingBoy> parkingBoyList = new ArrayList<>();
        parkingBoyList.add(graduateParkingBoy);
        managerParkingBoy.setParkingBoyList(parkingBoyList);


        Car car = new Car();
        AbstractParkingBoy secondGraduateParkingBoy = new GraduateParkingBoy();
        secondGraduateParkingBoy.setParkingBoyNum("008");
        managerParkingBoy.parkingBoyDispatch(car,secondGraduateParkingBoy);
    }
}
