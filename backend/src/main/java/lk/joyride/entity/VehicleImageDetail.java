package lk.joyride.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class VehicleImageDetail {
    @Id
    private String vehicle_reg_no;

    private String image_one;
    private String image_two;
    private String image_three;
    private String image_four;
}
