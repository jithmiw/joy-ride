package lk.joyride.controller;

import lk.joyride.dto.VehicleDTO;
import lk.joyride.dto.RentalDetailDTO;
import lk.joyride.service.DriverScheduleService;
import lk.joyride.service.RentalDetailService;
import lk.joyride.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/rentalDetail")
@CrossOrigin
public class RentalDetailController {

    @Autowired
    private RentalDetailService rentalDetailService;

    @Autowired
    private DriverScheduleService driverScheduleService;

    @GetMapping(path = "/generateRentalId")
    public ResponseUtil generateRentalId() {
        return new ResponseUtil(200, "Rental id generated", rentalDetailService.generateNewRentalId());
    }

    @GetMapping(params = {"pick_up_date", "return_date"})
    public ResponseUtil searchAvailableVehiclesForReservation(@RequestParam String pick_up_date, @RequestParam String return_date) {
        ArrayList<VehicleDTO> availableVehicles = rentalDetailService.searchAvailableVehiclesForReservation(pick_up_date, return_date);
        return new ResponseUtil(200, "Loaded successfully", availableVehicles);
    }

    @GetMapping(path = "/getRentalRequests")
    public ResponseUtil getRentalRequests() {
        return new ResponseUtil(200, "Successfully Loaded", rentalDetailService.getRentalRequests());
    }

    @GetMapping(params = {"customer_nic"})
    public ResponseUtil getRentalRequestsByCustomerNic(@RequestParam String customer_nic) {
        return new ResponseUtil(200, "Successfully Loaded", rentalDetailService.getRentalRequestsByCustomerNic(customer_nic));
    }

    @GetMapping(path = "/countRequests", params = {"customer_nic"})
    public ResponseUtil countRentalRequestsByCustomerNic(@RequestParam String customer_nic) {
        return new ResponseUtil(200, "Successful", rentalDetailService.countRentalRequestsByCustomerNic(customer_nic));
    }

    @PutMapping(params = {"rental_id"})
    public ResponseUtil acceptRentalRequest(@RequestParam String rental_id) {
        RentalDetailDTO rental = rentalDetailService.getRentalDetailByRentalId(rental_id);
        rental.setRental_status("Accepted");
        rentalDetailService.updateRentalDetail(rental);
        return new ResponseUtil(200, "Rental request " + rental_id + " accepted", null);
    }

    @PutMapping(params = {"rental_id", "reason"})
    public ResponseUtil denyRentalRequest(@RequestParam String rental_id, @RequestParam String reason) {
        RentalDetailDTO rental = rentalDetailService.getRentalDetailByRentalId(rental_id);
        rental.setRental_status("Denied, " + reason);
        rentalDetailService.updateRentalDetail(rental);
        return new ResponseUtil(200, "Rental request " + rental_id + " denied", null);
    }

    @PutMapping(path = "/cancelRequest", params = {"rental_id"})
    public ResponseUtil cancelRentalRequest(@RequestParam String rental_id) {
        RentalDetailDTO rental = rentalDetailService.getRentalDetailByRentalId(rental_id);
        rental.setRental_status("Cancelled");
        rentalDetailService.updateRentalDetail(rental);
        return new ResponseUtil(200, "Rental request " + rental_id + " cancelled", null);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil makeReservation(@RequestBody RentalDetailDTO dto) {
        String scheduleId = null;
        if (dto.getDriver_status().equals("Yes")) {
            scheduleId = driverScheduleService.generateNewScheduleId();
        }
        rentalDetailService.saveRentalDetail(dto, scheduleId);
        return new ResponseUtil(200, "Rental Request sent successfully", null);
    }
}
