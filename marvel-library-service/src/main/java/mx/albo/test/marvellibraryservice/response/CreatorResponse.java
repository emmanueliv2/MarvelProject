package mx.albo.test.marvellibraryservice.response;

import lombok.Data;

import java.util.List;

@Data
public class CreatorResponse {

    private String lastSync;
    private List<String> editors;
    private List<String> writters;
    private List<String> colorist;

}
