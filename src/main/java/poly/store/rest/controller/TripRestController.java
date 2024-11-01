package poly.store.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import poly.store.entity.Trip;
import poly.store.service.TripService;

import java.util.List;

@RestController
@RequestMapping("/rest/trips") // Đường dẫn cơ sở cho các yêu cầu REST
public class TripRestController {

    @Autowired
    private TripService tripService; // Dịch vụ chuyến đi

    // Lấy tất cả chuyến đi
    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = tripService.findAll();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    // Lấy chuyến đi theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id) {
        Trip trip = tripService.findById(id);
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

    // Tạo chuyến đi mới
    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {
        Trip createdTrip = tripService.create(trip);
        return new ResponseEntity<>(createdTrip, HttpStatus.CREATED);
    }

    // Cập nhật chuyến đi
    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable Long id, @RequestBody Trip trip) {
        trip.setId(id); // Đặt ID cho chuyến đi để cập nhật
        Trip updatedTrip = tripService.update(trip);
        return new ResponseEntity<>(updatedTrip, HttpStatus.OK);
    }

    // Xóa chuyến đi theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        tripService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Trả về 204 No Content
    }
}
