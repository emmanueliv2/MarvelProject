package mx.albo.test.marvelschedulerlibrary.service;

import mx.albo.test.marvelschedulerlibrary.model.MarvelCharacter;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface LibrarySevice {

    String updateLibrary(Date lastModified);

}
