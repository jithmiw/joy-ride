package lk.joyride.service.impl;

import lk.joyride.dto.VehicleImageDetailDTO;
import lk.joyride.entity.VehicleImageDetail;
import lk.joyride.repo.VehicleImageDetailRepo;
import lk.joyride.service.VehicleImageDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class VehicleImageDetailServiceImpl implements VehicleImageDetailService {

    @Autowired
    private VehicleImageDetailRepo vehicleImageDetailRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveVehicleImageDetail(VehicleImageDetailDTO dto) {
        VehicleImageDetail vehicleImageDetail = mapper.map(dto, VehicleImageDetail.class);
        vehicleImageDetailRepo.save(vehicleImageDetail);
    }

    @Override
    public VehicleImageDetailDTO getVehicleImageDetailByRegNo(String reg_no) {
        VehicleImageDetail vehicleImageDetail = vehicleImageDetailRepo.findVehicleImageDetailByReg_no(reg_no);
        if (vehicleImageDetail != null) {
            return mapper.map(vehicleImageDetail, VehicleImageDetailDTO.class);
        }
        return null;
    }
}
