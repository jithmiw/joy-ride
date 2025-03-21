package lk.joyride.repo;

import lk.joyride.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DriverRepo extends JpaRepository<Driver, String> {

    Driver findDriverByUsernameAndPassword(String username, String password);

    @Query(value = "SELECT * FROM Driver WHERE nic_no=?1", nativeQuery = true)
    Driver findDriverByNic_no(String nic_no);
}
