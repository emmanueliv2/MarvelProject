package mx.albo.test.marvellibraryservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "co_worker")
public class CoWorker {

    @Id
    @Column(name = "id_co_worker")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idCoWorker;
    @Column(name = "name")
    private String name;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comic")
    @JsonIgnore
    private Comic comic;
}
