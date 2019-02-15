package hhk;

import com.hhk.datamodel.*;
import com.hhk.exception.NoParkingLotAvailableException;
import com.hhk.impls.GraduateParkingBoy;
import com.hhk.impls.SmartParkingBoy;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
/**
 * @author hehuikang
 * @description
 * @date 2019-02-15 13:25
 */
public class SmartParkingBoyTest {
    /**
     * Given 0个停车场，1辆车
     * When  停车小弟负责按停放给定的1辆车
     * Then  提示：无可供使用的停车场
     */
    @Test(expected = NoParkingLotAvailableException.class)
    public void should_throw_no_parking_lot_available_when_parked_given_zero_parkinglot_and_one_car(){
        AbstractParkingBoy graduateParkingBoy = new GraduateParkingBoy();
        Car firstCar = new Car();
        graduateParkingBoy.parkingLotDispatch(firstCar);
    }
    /**
     * Given 3个停车场，只有一个停车场空闲停车位最多
     * When  停车小弟负责按停放给定的1辆车
     * Then  成功停放在空闲车位数最多的停车场
     */
    @Test
    public void should_first_parkinglot_have_one_car_when_parked_given_three_parkinglot_and_first_parkinglot_hava_max_restspace(){
        AbstractParkingBoy smartParkingBoy = new SmartParkingBoy();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot(3,1);
        ParkingLot secondParkingLot = new ParkingLot(2,2);
        ParkingLot threeParkingLot = new ParkingLot(1,3);
        parkingLotList.add(firstParkingLot);
        parkingLotList.add(secondParkingLot);
        parkingLotList.add(threeParkingLot);
        smartParkingBoy.setParkingLotList(parkingLotList);
        Car car = new Car();
        smartParkingBoy.parkingLotDispatch(car);
        Assert.assertEquals(smartParkingBoy.getParkingLotList().get(0).getPakringCarMap().size(),1);
        Assert.assertEquals(smartParkingBoy.getParkingLotList().get(1).getPakringCarMap().size(),0);
        Assert.assertEquals(smartParkingBoy.getParkingLotList().get(2).getPakringCarMap().size(),0);
    }
    /**
     * Given 3个停车场，其中第二个和第三个停车场空闲停车位最多且相同
     * When  停车小弟负责按停放给定的2辆车
     * Then  成功停放在空闲车位数最多且最早纳入管理的停车场
     */
    @Test
    public void should_second_parkinglot_have_one_car_when_parked_given_three_parkinglot_and_second_and_three_parkinglot_hava_max_restspace(){
        AbstractParkingBoy smartParkingBoy = new SmartParkingBoy();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot firstParkingLot = new ParkingLot(1,1);
        ParkingLot secondParkingLot = new ParkingLot(2,2);
        ParkingLot threeParkingLot = new ParkingLot(2,3);
        parkingLotList.add(firstParkingLot);
        parkingLotList.add(secondParkingLot);
        parkingLotList.add(threeParkingLot);
        smartParkingBoy.setParkingLotList(parkingLotList);

        Car firstCar = new Car();
        smartParkingBoy.parkingLotDispatch(firstCar);
        Assert.assertEquals(smartParkingBoy.getParkingLotList().get(0).getPakringCarMap().size(),1);
        Assert.assertEquals(smartParkingBoy.getParkingLotList().get(0).getNum(),2);

        Car secondCar = new Car();
        smartParkingBoy.parkingLotDispatch(secondCar);
        Assert.assertEquals(smartParkingBoy.getParkingLotList().get(0).getPakringCarMap().size(),1);
        Assert.assertEquals(smartParkingBoy.getParkingLotList().get(0).getNum(),3);
    }
}
