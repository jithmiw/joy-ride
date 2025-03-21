package lk.joyride.repo;

import lk.joyride.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepo extends JpaRepository<Vehicle, String> {

    List<Vehicle> findVehicleByStatus(String status);
}
