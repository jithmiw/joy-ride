package lk.joyride.repo;

import lk.joyride.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, String> {

    Admin findAdminByUsernameAndPassword(String username, String password);
}
