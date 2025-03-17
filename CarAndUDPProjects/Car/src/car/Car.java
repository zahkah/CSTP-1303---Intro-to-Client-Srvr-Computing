package car;

public class Car {
	private final CarType carType;
	private final int seats;
	private final Transmission transmission;
	// color
	//tv
	// can Fly
	// 
	// 
	public static class CarBuilder2 {
		private CarType carType;
		private int seats;
		private Transmission transmission;
		
		public CarBuilder2(CarType carType, int seats, Transmission transmission) {
			this.carType = carType;
			this.seats = seats;
			this.transmission = transmission;
		}
		
		public CarBuilder2() {
			// TODO Auto-generated constructor stub
		}

		public CarBuilder2 setCarType(CarType carType) {
			this.carType = carType;
			return this;
		}
		public CarBuilder2 setSeats(int seats) {
			this.seats = seats;
			return this;
		}
		public CarBuilder2 setTransmission(Transmission transmission) {
			this.transmission = transmission;
			return this;
		}
		
		
		public Car build() {
			return new Car(this);
		}
		
		
	}
	
	public Car(CarBuilder2 builder) {
		this.carType = builder.carType;
		this.seats = builder.seats;
		this.transmission = builder.transmission;
		
	}
	
	public Car(CarType carType, int seats, Transmission transmission) {
		this.carType = carType; 
		this.seats = seats;
		this.transmission = transmission;
	}

	public String getCarType() {
		// TODO Auto-generated method stub
		return this.carType.toString();
	}
}
