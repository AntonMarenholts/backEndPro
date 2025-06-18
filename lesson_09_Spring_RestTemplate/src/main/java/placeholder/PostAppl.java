package placeholder;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import placeholder.dto.PostDto;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class PostAppl {
    public static void main(String[] args) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI url = new URI("https://jsonplaceholder.typicode.com/posts?eserId7");
        RequestEntity<String> request = new RequestEntity<>(HttpMethod.GET, url);
        ResponseEntity<PostDto[]> response = restTemplate.exchange(request, PostDto[].class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders().get("Content-Type"));
        Arrays.stream(response.getBody()).forEach(post -> System.out.println("Title: " + post.getTitle()));

    }
}
