package lk.joyride.controller;

import lk.joyride.dto.VehicleDTO;
import lk.joyride.service.VehicleService;
import lk.joyride.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseUtil saveVehicle(@ModelAttribute VehicleDTO dto) {
        vehicleService.saveVehicle(dto);
        return new ResponseUtil(200, "Vehicle added successfully", null);
    }

    @PutMapping
    public ResponseUtil updateVehicle(@RequestBody VehicleDTO dto) {
        vehicleService.updateVehicle(dto);
        return new ResponseUtil(200, "Reg no: " + dto.getReg_no() + " vehicle updated successfully", null);
    }

    @DeleteMapping(params = {"reg_no"})
    public ResponseUtil deleteVehicle(@RequestParam String reg_no) {
        vehicleService.deleteVehicle(reg_no);
        return new ResponseUtil(200, "Reg no: " + reg_no + " vehicle deleted successfully", null);
    }

    @GetMapping(params = {"reg_no"})
    public ResponseUtil findVehicleByRegNo(@RequestParam String reg_no) {
        return new ResponseUtil(200, "Loaded successfully", vehicleService.findVehicleByRegNo(reg_no));
    }

    @GetMapping
    public ResponseUtil getAllVehicles() {
        return new ResponseUtil(200, "Loaded successfully", vehicleService.getAllVehicles());
    }
}
