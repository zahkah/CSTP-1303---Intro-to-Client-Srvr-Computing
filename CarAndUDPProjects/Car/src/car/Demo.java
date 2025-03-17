package car;

public class Demo {
	
	public static void main(String[] args) { 

		// 1) Builder pattern using Director
		Director director = new Director();
		CarBuilder builder = new CarBuilder();
        director.constructsCityCar(builder);
        Car car = builder.getResult();
        System.out.println("Car built:\n" + car.getCarType());
		
        // 2) Builder pattern using the chain		
		Car car2 = new Car.CarBuilder2().setCarType(CarType.SPORT_CAR)
				.setTransmission(Transmission.MANUAL).setSeats(5).build();
		 System.out.println("Car built:\n" + car2.getCarType());
		
	}


}
