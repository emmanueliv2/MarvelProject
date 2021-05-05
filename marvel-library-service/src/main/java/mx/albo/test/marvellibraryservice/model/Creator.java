package mx.albo.test.marvellibraryservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "creator")
public class Creator {

    @Id
    @Column(name = "id_creator")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idCreator;
    @Column(name = "name")
    private String name;
    @Column(name = "rol")
    private String rol;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comic")
    @JsonIgnore
    private Comic comic;

}
