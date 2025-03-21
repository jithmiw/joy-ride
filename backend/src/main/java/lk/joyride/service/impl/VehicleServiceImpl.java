package lk.joyride.service.impl;

import lk.joyride.dto.VehicleDTO;
import lk.joyride.entity.Vehicle;
import lk.joyride.repo.VehicleRepo;
import lk.joyride.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveVehicle(VehicleDTO dto) {
        if (vehicleRepo.existsById(dto.getReg_no())) {
            throw new RuntimeException("Vehicle already registered");
        }
        switch (dto.getStatus()) {
            case "1":
                dto.setStatus("Available");
                break;
            case "2":
                dto.setStatus("Not Available");
                break;
            case "3":
                dto.setStatus("Reserved");
                break;
        }
        vehicleRepo.save(mapper.map(dto, Vehicle.class));
    }

    @Override
    public void updateVehicle(VehicleDTO dto) {
        if (!vehicleRepo.existsById(dto.getReg_no())) {
            throw new RuntimeException("No such a vehicle, Please enter valid registration no");
        }
        switch (dto.getStatus()) {
            case "1":
                dto.setStatus("Available");
                break;
            case "2":
                dto.setStatus("Not Available");
                break;
            case "3":
                dto.setStatus("Reserved");
                break;
        }
        vehicleRepo.save(mapper.map(dto, Vehicle.class));
    }

    @Override
    public void deleteVehicle(String reg_no) {
        if (!vehicleRepo.existsById(reg_no)) {
            throw new RuntimeException("No such a vehicle, Please enter valid registration no");
        }
        vehicleRepo.deleteById(reg_no);
    }

    @Override
    public ArrayList<VehicleDTO> getAllVehicles() {
        List<Vehicle> all = vehicleRepo.findAll();
        return mapper.map(all, new TypeToken<ArrayList<VehicleDTO>>() {
        }.getType());
    }

    @Override
    public VehicleDTO findVehicleByRegNo(String reg_no) {
        Vehicle vehicle = vehicleRepo.findById(reg_no).get();
        return mapper.map(vehicle, VehicleDTO.class);
    }
}
