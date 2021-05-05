package mx.albo.test.marvelschedulerlibrary.mapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MarvelMappingServiceImp implements MarvelMappingService{

    private static final Logger log = LoggerFactory.getLogger(MarvelMappingServiceImp.class);

    @Override
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

}
