package placeholder.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(of = {"id"})

public class PostDto {
    private int userId;
    private int id;
    private String title;
    private String body;

}
