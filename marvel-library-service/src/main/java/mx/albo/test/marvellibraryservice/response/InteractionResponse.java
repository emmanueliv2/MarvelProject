package mx.albo.test.marvellibraryservice.response;

import lombok.Data;
import mx.albo.test.marvellibraryservice.model.Characters;

import java.util.List;

@Data
public class InteractionResponse {

    private String lastSync;
    private List<Characters> characters;

}
