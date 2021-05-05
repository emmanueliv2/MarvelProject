package mx.albo.test.marvelschedulerlibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "comic")
public class Comic {

    @Id
    @Column(name = "id_comic")
    private Integer idComic;
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
