package poly.store.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.store.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("select v from Vehicle v where v.vehicleNumber = ?1") // Corrected to match the field name in Vehicle entity
    Optional<Vehicle> findByVehicleNumber(String vehicleNumber); // Method name updated for clarity
}
