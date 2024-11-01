package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.store.entity.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {

	List<Trip> findByVehicleId(Long vehicleId);}
