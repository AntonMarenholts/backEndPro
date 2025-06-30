package imagga.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
public class TagDto {
private double confidence;
private Map<String,String> tag;
}
