package car;

public class Director {
	public void constructsSportsCar(Builder builder) {
		builder.setCarType(CarType.SPORT_CAR);
		builder.setSeats(2);
		builder.setTransmission(Transmission.AUTOMATIC);
		//
	}
	
	public void constructsCityCar(Builder builder) {
		builder.setCarType(CarType.CITY_CAR);
		builder.setSeats(4);
		builder.setTransmission(Transmission.AUTOMATIC);
		//
	}
	
	// constructSUVManul, SUVSingleSpeed ...
	
}
