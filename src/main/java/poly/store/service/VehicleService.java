package poly.store.service;

import poly.store.entity.Account;
import poly.store.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> findAll(); // Lấy tất cả xe

    Vehicle create(Vehicle vehicle); // Thêm xe

    Vehicle update(Long id, Vehicle vehicleDetails); // Cập nhật xe

    void delete(Long id); // Xóa xe

    Vehicle findById(Long id); // Tìm xe theo ID
    
    Account getInfoAuth(); // Lấy thông tin xác thực tài khoản
}
