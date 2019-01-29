package hhk;

import com.hhk.datamodel.Car;
import com.hhk.datamodel.GraduateParkingBoy;
import com.hhk.datamodel.ParkingBoy;
import com.hhk.datamodel.ParkingLot;
import com.hhk.exception.NoParkingLotAvailableException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 作为一个初入职场的停车小弟（Graduate Parking Boy），
 * 我能够将车按顺序停放到多个停车场，并可以取出。
 * @author hhk
 */
public class ParkingLotHasGraduateParkingBoyTest {

    /**
     * Given 0个停车场，1辆车
     * When  停车小弟负责按停放给定的1辆车
     * Then  提示：无可供使用的停车场
     */
    @Test(expected = NoParkingLotAvailableException.class)
    public void should_throw_no_parking_lot_available_when_parked_given_zero_parkinglot_and_one_car(){
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy();
        Car firstCar = new Car();
        graduateParkingBoy.parkingDispatch(firstCar);
    }

    /**
     * Given 2个停车场，2辆车
     * When  停车小弟负责按顺序停放给定的2辆车
     * Then  每个停车场各停一辆
     */
    @Test
    public void should_every_parkinglot_have_one_car_when_parked_given_two_parkinglot_and_two_car(){
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(firstParkingLot);
        parkingLotList.add(secondParkingLot);
        graduateParkingBoy.setParkingLotList(parkingLotList);

        Car firstCar = new Car();
        Car secondCar = new Car();
        graduateParkingBoy.parkingDispatch(firstCar);
        graduateParkingBoy.parkingDispatch(secondCar);

        Assert.assertEquals(firstParkingLot.getPakringCarMap().size(),1);
        Assert.assertEquals(secondParkingLot.getPakringCarMap().size(),1);
    }

}
