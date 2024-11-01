package poly.store.service;

import poly.store.entity.Account;
import poly.store.entity.Trip;

import java.util.List;

public interface TripService {
    Trip findById(Long id);  // Retrieve a trip by its ID

    List<Trip> findAll();    // Retrieve all trips

    Trip create(Trip trip);   // Create a new trip

    Trip update(Trip trip);   // Update an existing trip

    void delete(Long id);     // Delete a trip by its ID

    // Optionally, you can add more methods related to trips
    List<Trip> findByVehicleId(Long vehicleId); // Example: Get trips for a specific vehicle
    
	Account getInfoAuth();
}
