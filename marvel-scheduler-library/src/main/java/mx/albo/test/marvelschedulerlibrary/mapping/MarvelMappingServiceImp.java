package mx.albo.test.marvelschedulerlibrary.mapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class MarvelMappingServiceImp implements MarvelMappingService{

    private static final Logger log = LoggerFactory.getLogger(MarvelMappingServiceImp.class);

    @Override
    @Retry(name = "marvel-api", fallbackMethod = "marvelApiFailedResponse")
    public JSONObject getMarvelResourceByUri(String uri) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(uri,
                HttpMethod.GET, null, String.class);
        if(response.getStatusCodeValue() == 200){
        	try {
				return new JSONObject(response.getBody());
			} catch (JSONException e) {
                log.error("FAILED TO GET DATA FROM URL {}", uri);
                log.error("ERROR {}", e);
				return null;
			}
        }
        return null;
    }

    public JSONObject marvelApiFailedResponse(String uri, Throwable throwable) {
        log.error("FAILED TO CALL MARVEL API {}", throwable.getMessage());
        return null;
    }

}
