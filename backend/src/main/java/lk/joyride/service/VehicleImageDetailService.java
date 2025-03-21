package lk.joyride.service;

import lk.joyride.dto.VehicleImageDetailDTO;

public interface VehicleImageDetailService {
    void saveVehicleImageDetail(VehicleImageDetailDTO dto);

    VehicleImageDetailDTO getVehicleImageDetailByRegNo(String reg_no);
}
