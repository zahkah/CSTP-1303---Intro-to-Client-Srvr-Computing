package com.packt.cardatabase.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends CrudRepository<Car, Long> {

	// Fetch cars by color
	List<Car> findByColor(String color);
	// Fetch cars by year
	List<Car> findByYear(int year);
	// Fetch cars by brand and model
	List<Car> findByBrandAndModel(String brand, String
	model);
	// Fetch cars by brand and sort by year
	List<Car> findByBrandOrderByYearAsc(String brand);
	
	@Query("select c from Car c where c.brand like %?1")
	List<Car> findByBrand(String brand);
}