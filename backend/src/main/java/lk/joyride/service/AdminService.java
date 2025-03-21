package lk.joyride.service;

import lk.joyride.dto.AdminDTO;

public interface AdminService {

    AdminDTO verifyAdmin(String username, String password);
}
