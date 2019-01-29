package hhk;

import com.hhk.datamodel.Car;
import com.hhk.datamodel.ParkingLot;
import com.hhk.datamodel.Ticket;
import com.hhk.exception.CarNotExistOrTakedException;
import com.hhk.exception.ParkingLotIsFullException;
import com.hhk.exception.TicketErrorException;
import org.junit.Assert;
import org.junit.Test;

/**
 * 作为一个停车场，能够存车、取车。
 * @author hhk
 */
public class ParkingLotTest {

    /**
     * Given 一个有空位的停车场
     * when 客户停车
     * then 停车成功，客户拿到一张车票
     */
    @Test
    public void should_return_ticket_when_parking_given_having_parkinglot_freespace(){
        ParkingLot parkingLot=new ParkingLot(1);
        Car car=new Car();

        Ticket ticket=parkingLot.park(car);
        Assert.assertNotNull(ticket);
    }
    /**
     * Given 一个已停满车的停车场
     * when 客户停车
     * then 停车失败，提示客户：车位已满
     */
    @Test(expected = ParkingLotIsFullException.class)
    public void should_throw_parkinglot_full_exception_when_parking_given_parkinglot_fullspace(){
        ParkingLot  parkingLot=new ParkingLot(0);
        Car car=new Car();
        parkingLot.park(car);
    }
    /**
     * Given 客户拿票取自己停的车
     * when 客户取车
     * then 取车成功
     */
    @Test
    public void should_return_car_when_take_car_given_ticket(){
        ParkingLot  parkingLot=new ParkingLot(1);
        Car myCar = new Car();
        Ticket ticket = parkingLot.park(myCar);
        Car takedCar = parkingLot.takeCar(ticket);
        Assert.assertEquals(myCar,takedCar);
    }
    /**
     * Given 客户无票取车
     * when 客户取车
     * then 取车失败
     */
    @Test(expected = TicketErrorException.class)
    public void should_return_car_when_take_car_given_nothing(){
        ParkingLot  parkingLot=new ParkingLot(1);
        parkingLot.takeCar(null);
    }
    /**
     * Given 客户车已取走，再次拿相同的票取车
     * when 客户取车
     * then 取车失败，提示客户：车已被取走
     */
    @Test(expected = CarNotExistOrTakedException.class)
    public void should_throw_car_not_exist_or_taked_exception_when_take_car_given_same_ticket(){
        ParkingLot  parkingLot=new ParkingLot(1);
        Ticket ticket = parkingLot.park(new Car());
        Car car = parkingLot.takeCar(ticket);
        Assert.assertNotNull(car);
        parkingLot.takeCar(ticket);
    }

}
