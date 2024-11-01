package poly.store.service.impl;

import poly.store.entity.Account;
import poly.store.entity.Trip;
import poly.store.service.TripService;
import poly.store.dao.TripRepository; // Ensure this is the correct import
import poly.store.dao.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private AccountDAO adao;
    
    @Override
    public Trip findById(Long id) {
        return tripRepository.findById(id).orElse(null); // Use Optional for null safety
    }

    @Override
    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    @Override
    public Trip create(Trip trip) {
        return tripRepository.save(trip); // Save a new trip
    }

    @Override
    public Trip update(Trip trip) {
        // Check if the trip exists before updating
        if (tripRepository.existsById(trip.getId())) {
            return tripRepository.save(trip); // Update the trip
        }
        return null; // or throw an exception if the trip doesn't exist
    }

    @Override
    public void delete(Long id) {
        tripRepository.deleteById(id); // Delete a trip by its ID
    }

    @Override
    public List<Trip> findByVehicleId(Long vehicleId) {
        return tripRepository.findByVehicleId(vehicleId); // Assuming you have this method in your repository
    }
    
    @Override
    public Account getInfoAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            throw new IllegalStateException("No authenticated user found!");
        }
        return adao.FindByUserName(auth.getName())
                   .orElseThrow(() -> new UsernameNotFoundException(auth.getName() + " not found!"));
    }
}
