package lk.joyride.controller;

import lk.joyride.dto.VehicleImageDetailDTO;
import lk.joyride.dto.CustomerDTO;
import lk.joyride.dto.RentalDetailDTO;
import lk.joyride.service.VehicleImageDetailService;
import lk.joyride.service.CustomerService;
import lk.joyride.service.RentalDetailService;
import lk.joyride.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/files/upload")
@CrossOrigin
public class FileUploadController {

    @Autowired
    CustomerService customerService;

    @Autowired
    VehicleImageDetailService vehicleImageDetailService;

    @Autowired
    RentalDetailService rentalDetailService;

    //    formalized end-point to upload files
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil uploadCustomerNicAndLicense(@RequestPart("file") MultipartFile[] files, @RequestPart("customerNic") String customerNic) {
        try {
            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
            File uploadsDir = new File(projectPath + "/uploads");
            uploadsDir.mkdir();
            File customersDir = new File(uploadsDir + "/customers");
            customersDir.mkdir();
            for (MultipartFile file : files) {
                file.transferTo(new File(customersDir.getAbsolutePath() + "/" + file.getOriginalFilename()));
            }
            CustomerDTO customer = customerService.getCustomerByNic(customerNic);
            customer.setNic_img("uploads/customers/" + files[0].getOriginalFilename());
            customer.setLicense_img("uploads/customers/" + files[1].getOriginalFilename());
            customerService.updateCustomer(customer);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return new ResponseUtil(500, "Sign up failed, Please try again.", null);
        }
        return new ResponseUtil(200, "Signed up successfully", null);
    }

    @PostMapping(path = "/vehicleImages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil uploadVehicleImages(@RequestPart("vehicleImage") MultipartFile[] files, @RequestPart("vehicleRegNo") String vehicleRegNo) {
        try {
            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
            File uploadsDir = new File(projectPath + "/uploads");
            uploadsDir.mkdir();
            File vehiclesDir = new File(uploadsDir + "/vehicles");
            vehiclesDir.mkdir();
            for (MultipartFile file : files) {
                file.transferTo(new File(vehiclesDir.getAbsolutePath() + "/" + file.getOriginalFilename()));
            }
            vehicleImageDetailService.saveVehicleImageDetail(new VehicleImageDetailDTO(vehicleRegNo, "uploads/vehicles/" + files[0].getOriginalFilename(),
                    "uploads/vehicles/" + files[1].getOriginalFilename(), "uploads/vehicles/" + files[2].getOriginalFilename(),
                    "uploads/vehicles/" + files[3].getOriginalFilename()));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return new ResponseUtil(500, "Something went wrong, Please try again later", null);
        }
        return new ResponseUtil(200, "Vehicle added successfully", null);
    }

    @PostMapping(path = "/bankSlipImages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil uploadBankSlipImage(@RequestPart("bankSlipImage") MultipartFile file, @RequestPart("rentalId") String rentalId) {
        try {
            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
            File uploadsDir = new File(projectPath + "/uploads");
            uploadsDir.mkdir();
            File bankSlipsDir = new File(uploadsDir + "/bank-slips");
            bankSlipsDir.mkdir();
            file.transferTo(new File(bankSlipsDir.getAbsolutePath() + "/" + file.getOriginalFilename()));

            RentalDetailDTO rentalDetail = rentalDetailService.getRentalDetailByRentalId(rentalId);
            rentalDetail.setBank_slip_img("uploads/bank-slips/" + file.getOriginalFilename());
            rentalDetailService.updateRentalDetail(rentalDetail);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return new ResponseUtil(500, "Something went wrong, Please try again later", null);
        }
        return new ResponseUtil(200, "Rental request sent successfully", null);
    }
}
