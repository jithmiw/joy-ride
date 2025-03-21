package lk.joyride.service.impl;

import lk.joyride.repo.DriverRepo;
import lk.joyride.repo.DriverScheduleRepo;
import lk.joyride.service.DriverScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DriverScheduleServiceImpl implements DriverScheduleService {

    @Autowired
    private DriverScheduleRepo driverScheduleRepo;

    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public String generateNewScheduleId() {
        String schedule_id = "";
        schedule_id = driverScheduleRepo.getLastScheduleId();
        if (schedule_id != null) {
            int newRentalId = Integer.parseInt(schedule_id.replace("SID-", "")) + 1;
            return String.format("SID-%03d", newRentalId);
        }
        return "SID-001";
    }
}
