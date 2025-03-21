package lk.joyride.controller;

import lk.joyride.dto.AdminDTO;
import lk.joyride.dto.CustomerDTO;
import lk.joyride.dto.DriverDTO;
import lk.joyride.dto.UserDTO;
import lk.joyride.service.AdminService;
import lk.joyride.service.CustomerService;
import lk.joyride.service.DriverService;
import lk.joyride.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    CustomerService customerService;

    @Autowired
    DriverService driverService;

    @Autowired
    AdminService adminService;

    @PostMapping
    public ResponseUtil verifyUser(@RequestBody UserDTO userDTO) {

        CustomerDTO customerDTO = customerService.verifyCustomer(userDTO.getUsername(), userDTO.getPassword());

        if (customerDTO == null) {
            AdminDTO adminDTO = adminService.verifyAdmin(userDTO.getUsername(), userDTO.getPassword());
            if (adminDTO == null) {
                DriverDTO driverDTO = driverService.verifyDriver(userDTO.getUsername(), userDTO.getPassword());
                if (!(driverDTO == null)) {
                    return new ResponseUtil(200, "Driver", driverDTO);
                } else {
                    throw new RuntimeException("You have entered an invalid username or password. Please try again.");
                }
            } else {
                return new ResponseUtil(200, "Admin", adminDTO);
            }
        } else {
            return new ResponseUtil(200, "Customer", customerDTO);
        }
    }
}
