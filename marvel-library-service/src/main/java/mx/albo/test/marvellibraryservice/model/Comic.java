package mx.albo.test.marvellibraryservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "comic")
public class Comic {

    @Id
    @Column(name = "id_comic")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idComic;
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "resource_uri")
    private String resourceURI;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marvel_character")
    @JsonIgnore
    private MarvelCharacter marvelCharacter;

    @JsonManagedReference
    @OneToMany(mappedBy = "comic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Creator> creators;

    @JsonManagedReference
    @OneToMany(mappedBy = "comic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CoWorker> coWorkers;

}
