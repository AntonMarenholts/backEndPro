package imagga;

import imagga.dto.TagResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class ImaggaTagAppl {
    public static void main(String[] args) {
        String imgUrl = "https://imagga.com/static/images/tagging/wind-farm-538576_640.jpg";
        String lang = "uk";
        int threshold = 30;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic YWNjXzIwMGI2YjNlMmIyNDVmZDo4NDY0ZDkxMWEzNTRlZGJkNDhhNGM4MmI3NmNkOGQ3Mw==");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.imagga.com/v2/tags")
                .queryParam("image_url",imgUrl)
                .queryParam("language",lang)
                .queryParam("threshold", threshold);
        URI url = builder.build().toUri();
        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, url);
        ResponseEntity<TagResponseDto> response = restTemplate.exchange(request, TagResponseDto.class);
        response.getBody().getResult().getTags().forEach(System.out::println);
    }
}
