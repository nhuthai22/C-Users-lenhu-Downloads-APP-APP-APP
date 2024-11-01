package poly.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.store.entity.Account;
import poly.store.entity.Vehicle;
import poly.store.dao.VehicleRepository;
import poly.store.service.VehicleService;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle update(Long id, Vehicle vehicleDetails) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(id);
        if (existingVehicle.isPresent()) {
            Vehicle vehicle = existingVehicle.get();
            vehicle.setVehicleNumber(vehicleDetails.getVehicleNumber());
            vehicle.setVehicleType(vehicleDetails.getVehicleType());
            vehicle.setSeatCount(vehicleDetails.getSeatCount());
            vehicle.setStatus(vehicleDetails.getStatus());
            return vehicleRepository.save(vehicle);
        } else {
            throw new RuntimeException("Vehicle not found with id " + id);
        }
    }

    @Override
    public void delete(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id " + id));
    }

    @Override
    public Account getInfoAuth() {
        // Mock implementation of authenticated user information.
        Account account = new Account();
        account.setUsername("authenticatedUser");
        account.setFullname("Authenticated User");
        account.setEmail("authenticated@example.com");
        account.setRole(true); // Assuming true for admin role, false for regular user
        return account;
    }
}
