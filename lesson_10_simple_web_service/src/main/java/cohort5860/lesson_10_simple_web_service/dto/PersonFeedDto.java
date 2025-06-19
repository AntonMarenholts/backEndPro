package cohort5860.lesson_10_simple_web_service.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonFeedDto {
    private String fullName;
    @Singular
    private List<String> foods;
}
