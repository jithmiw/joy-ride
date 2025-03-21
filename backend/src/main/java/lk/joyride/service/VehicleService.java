package lk.joyride.service;

import lk.joyride.dto.VehicleDTO;

import java.util.ArrayList;

public interface VehicleService {
    void saveVehicle(VehicleDTO dto);

    void updateVehicle(VehicleDTO dto);

    void deleteVehicle(String reg_no);

    ArrayList<VehicleDTO> getAllVehicles();

    VehicleDTO findVehicleByRegNo(String reg_no);
}
