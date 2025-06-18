package car.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class CarRestoreAppl {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CarDto vw = mapper.readValue(new File("vw.json"), CarDto.class);
        System.out.println(vw);
    }
}
