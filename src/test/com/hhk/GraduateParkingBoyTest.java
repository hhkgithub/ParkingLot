package hhk;

import com.hhk.datamodel.*;
import com.hhk.exception.NoParkingLotAvailableException;
import com.hhk.impls.GraduateParkingBoy;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;


/**
 * @author hehuikang
 * @description  * 作为一个初入职场的停车小弟（Graduate Parking Boy），
 *               * 我能够将车按顺序停放到多个停车场，并可以取出。
 * @date 2019-02-15 13:25
 */
public class GraduateParkingBoyTest {

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
     * Given m个停车场，n辆车：m=n 例如，2个停车场，2辆车
     * When  停车小弟负责按顺序停放给定的2辆车
     * Then  每个停车场各停一辆
     */
    @Test
    public void should_every_parkinglot_have_one_car_when_parked_given_two_parkinglot_and_two_car(){
        ParkingLot firstParkingLot = new ParkingLot(10,1);
        ParkingLot secondParkingLot = new ParkingLot(10,2);
        AbstractParkingBoy graduateParkingBoy = new GraduateParkingBoy();

        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(firstParkingLot);
        parkingLotList.add(secondParkingLot);
        graduateParkingBoy.setParkingLotList(parkingLotList);
//        Map<Integer,ParkingLot> parkingLotMap = new TreeMap<Integer,ParkingLot>();
//        parkingLotMap.put(firstParkingLot.getNum(),firstParkingLot);
//        parkingLotMap.put(secondParkingLot.getNum(),secondParkingLot);
//        graduateParkingBoy.setParkingLotMap(parkingLotMap);

        Car firstCar = new Car();
        Car secondCar = new Car();
        graduateParkingBoy.parkingLotDispatch(firstCar);
        graduateParkingBoy.parkingLotDispatch(secondCar);

        for (ParkingLot parkingLot : graduateParkingBoy.getParkingLotList()) {
            Assert.assertEquals(parkingLot.getPakringCarMap().size(),1);
        }
    }
    /**
     * Given m个停车场，n辆车：m < n 例如，3个停车场，5辆车
     * When  停车小弟负责按顺序停放给定的5辆车
     * Then  x = n % m  第1个至第x个停车场停的车数量一致，第x+1至第m个停车场停的车数量一致且前者任一停车场停车数量比后者任一停车场停车数量多1
     * 即，第1个至第2个停车场停车数量为2，第3个停车场停车数量为1
     */
    @Test
    public void should_first_parkinglot_have_two_car_and_second_and_three_parkinglot_have_one_car_when_parked_given_three_parkinglot_and_four_car(){
        ParkingLot firstParkingLot = new ParkingLot(10,1);
        ParkingLot secondParkingLot = new ParkingLot(10,2);
        ParkingLot threeParkingLot = new ParkingLot(10,3);
        AbstractParkingBoy graduateParkingBoy = new GraduateParkingBoy();

        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(firstParkingLot);
        parkingLotList.add(secondParkingLot);
        parkingLotList.add(threeParkingLot);
        graduateParkingBoy.setParkingLotList(parkingLotList);

//        Map<Integer,ParkingLot> parkingLotMap = new TreeMap<Integer,ParkingLot>();
//        parkingLotMap.put(firstParkingLot.getNum(),firstParkingLot);
//        parkingLotMap.put(secondParkingLot.getNum(),secondParkingLot);
//        parkingLotMap.put(threeParkingLot.getNum(),threeParkingLot);
//        graduateParkingBoy.setParkingLotMap(parkingLotMap);


        Car firstCar = new Car();
        Car secondCar = new Car();
        Car threeCar = new Car();
        Car fourCar = new Car();
        Car fiveCar = new Car();

        graduateParkingBoy.parkingLotDispatch(firstCar);
        graduateParkingBoy.parkingLotDispatch(secondCar);
        graduateParkingBoy.parkingLotDispatch(threeCar);
        graduateParkingBoy.parkingLotDispatch(fourCar);
        graduateParkingBoy.parkingLotDispatch(fiveCar);

        Assert.assertEquals(graduateParkingBoy.getParkingLotList().get(0).getPakringCarMap().size(),2);
        Assert.assertEquals(graduateParkingBoy.getParkingLotList().get(1).getPakringCarMap().size(),2);
        Assert.assertEquals(graduateParkingBoy.getParkingLotList().get(2).getPakringCarMap().size(),1);
    }

    /**
     * Given 3个停车场，其中第1个和第2个停车场已分别停有2辆车，第3个停车场停有1辆车
     * When  停车小弟负责按顺序停放新来的2辆车
     * Then  第1个停车场停有3辆车，第2个和第3个停车场分别停有2辆车
     */
    @Test
    public void should_first_parkinglot_have_three_car_and_second_and_three_parkinglot_have_two_car_when_parked_given_three_parkinglot_and_two_car(){
        ParkingLot firstParkingLot = new ParkingLot(10,1);
        firstParkingLot.park(new Car());
        firstParkingLot.park(new Car());

        ParkingLot secondParkingLot = new ParkingLot(10,2);
        secondParkingLot.park(new Car());
        secondParkingLot.park(new Car());

        ParkingLot threeParkingLot = new ParkingLot(10,3);
        threeParkingLot.park(new Car());
        threeParkingLot.setParked(false);

        AbstractParkingBoy graduateParkingBoy = new GraduateParkingBoy();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(firstParkingLot);
        parkingLotList.add(secondParkingLot);
        parkingLotList.add(threeParkingLot);
        graduateParkingBoy.setParkingLotList(parkingLotList);

//        Map<Integer,ParkingLot> parkingLotMap = new TreeMap<Integer,ParkingLot>();
//        parkingLotMap.put(firstParkingLot.getNum(),firstParkingLot);
//        parkingLotMap.put(secondParkingLot.getNum(),secondParkingLot);
//        parkingLotMap.put(threeParkingLot.getNum(),threeParkingLot);
//        graduateParkingBoy.setParkingLotMap(parkingLotMap);

        Car firstCar = new Car();
        Car secondCar = new Car();
        graduateParkingBoy.parkingLotDispatch(firstCar);
        graduateParkingBoy.parkingLotDispatch(secondCar);

        Assert.assertEquals(graduateParkingBoy.getParkingLotList().get(0).getPakringCarMap().size(),3);
        Assert.assertEquals(graduateParkingBoy.getParkingLotList().get(1).getPakringCarMap().size(),2);
        Assert.assertEquals(graduateParkingBoy.getParkingLotList().get(2).getPakringCarMap().size(),2);

    }


    /**
     * Given 3个停车场，其中一个停放着客户的车辆
     * When  客户拿票取车
     * Then  取车成功
     */
    @Test
    public void should_return_my_car_when_taked_given_ticket(){
        ParkingLot firstParkingLot = new ParkingLot(10,1);

        ParkingLot secondParkingLot = new ParkingLot(10,2);

        ParkingLot threeParkingLot = new ParkingLot(10,3);

        AbstractParkingBoy graduateParkingBoy = new GraduateParkingBoy();

        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(firstParkingLot);
        parkingLotList.add(secondParkingLot);
        parkingLotList.add(threeParkingLot);
        graduateParkingBoy.setParkingLotList(parkingLotList);

//        Map<Integer,ParkingLot> parkingLotMap = new TreeMap<Integer,ParkingLot>();
//        parkingLotMap.put(firstParkingLot.getNum(),firstParkingLot);
//        parkingLotMap.put(secondParkingLot.getNum(),secondParkingLot);
//        parkingLotMap.put(threeParkingLot.getNum(),threeParkingLot);
//        graduateParkingBoy.setParkingLotMap(parkingLotMap);
        Car firstCar = new Car();
        Car secondCar = new Car();
        Car threeCar = new Car();

        Ticket firstTicket = graduateParkingBoy.parkingLotDispatch(firstCar);
        Ticket secondTicket = graduateParkingBoy.parkingLotDispatch(secondCar);
        Ticket threeTicket = graduateParkingBoy.parkingLotDispatch(threeCar);

        Assert.assertEquals(firstCar,graduateParkingBoy.getParkingLotList().get(0).getPakringCarMap().get(firstTicket));
        Assert.assertEquals(secondCar,graduateParkingBoy.getParkingLotList().get(1).getPakringCarMap().get(secondTicket));
        Assert.assertEquals(threeCar,graduateParkingBoy.getParkingLotList().get(2).getPakringCarMap().get(threeTicket));

    }

}
