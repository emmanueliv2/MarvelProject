package mx.albo.test.marvellibraryservice.service;

import org.springframework.http.ResponseEntity;

public interface LibrarySevice {

    ResponseEntity<?> getCreators(String name);

    ResponseEntity<?> getCoWorkers(String name);

}
