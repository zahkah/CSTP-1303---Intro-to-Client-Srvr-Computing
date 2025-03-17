package car;

public class CarBuilder implements Builder {
	private CarType carType;
	private int seats;
	private Transmission transmission;
	
	@Override
	public void setCarType(CarType type) {
		this.carType = type;
	}
	
	@Override
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	@Override
	public void setTransmission(Transmission transimission) {
		this.transmission = transimission;
	}
	
	public Car getResult() {
		return new Car(this.carType, this.seats, this.transmission);
	}
}
