package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.LocationStates;

public interface LocationStatesRepository extends JpaRepository<LocationStates, Integer> {
	
	public static final String GET_COUNTRIES = "SELECT country FROM Location_States";

	@Query(value = GET_COUNTRIES, nativeQuery = true)
	public List<String> getAllCountries();
	
	public static final String GET_LATEST_DEATH_COUNTS = "SELECT latest_Total_Deaths FROM Location_States";

	@Query(value = GET_LATEST_DEATH_COUNTS, nativeQuery = true)
	public List<Integer> getAllLatestDeathCount();
	
}
