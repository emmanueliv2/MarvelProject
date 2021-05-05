package mx.albo.test.marvellibraryservice.model;

import lombok.Data;

import java.util.List;

@Data
public class Characters {

    private String nameCharacter;
    private List<String> comics;

}
