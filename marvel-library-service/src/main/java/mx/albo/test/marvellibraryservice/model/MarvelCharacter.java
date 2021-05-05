package mx.albo.test.marvellibraryservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "marvel_character")
public class MarvelCharacter {

    @Id
    @Column(name = "id_marvel_character")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idMarvelCharacter;
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "resource_uri")
    private String resourceURI;
    @Column(name = "last_sync")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSync;
    @JsonManagedReference
    @OneToMany(mappedBy = "marvelCharacter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comic> comics;

}
