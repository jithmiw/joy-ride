package lk.joyride.controller;

import lk.joyride.dto.VehicleImageDetailDTO;
import lk.joyride.service.VehicleImageDetailService;
import lk.joyride.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicleImageDetail")
@CrossOrigin
public class VehicleImageDetailController {

    @Autowired
    private VehicleImageDetailService vehicleImageDetailService;

    @GetMapping(path = "/{reg_no}")
    public ResponseUtil getVehicleImagesByRegNo(@PathVariable String reg_no) {
        VehicleImageDetailDTO vehicleImageDetailDTO = vehicleImageDetailService.getVehicleImageDetailByRegNo(reg_no);
        return new ResponseUtil(200, "Vehicle images loaded successfully", vehicleImageDetailDTO);
    }
}
