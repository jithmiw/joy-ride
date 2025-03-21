package lk.joyride.repo;

import lk.joyride.entity.VehicleImageDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleImageDetailRepo extends JpaRepository<VehicleImageDetail, String> {

    @Query(value = "SELECT * FROM VehicleImageDetail WHERE vehicle_reg_no=?1", nativeQuery = true)
    VehicleImageDetail findVehicleImageDetailByReg_no(String reg_no);
}
