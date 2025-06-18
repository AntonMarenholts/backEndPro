package car.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@ToString
public class CarDto {

    private String manufacturer;
    private int year;
    private Set<String> model;


}
