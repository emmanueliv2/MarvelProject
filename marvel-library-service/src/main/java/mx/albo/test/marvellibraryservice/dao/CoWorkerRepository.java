package mx.albo.test.marvellibraryservice.dao;

import mx.albo.test.marvellibraryservice.model.CoWorker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoWorkerRepository extends CrudRepository<CoWorker, Integer> {

    @Query(value = "select c.name \n" +
            "  from sys.co_worker cw inner join sys.comic c on (cw.id_comic = c.id_comic) \n" +
            " where c.id_marvel_character = :idMarvelCharacter \n" +
            "   and cw.name = :name", nativeQuery = true)
    List<String> getComicsByIdCharacterAndCoWorker(@Param(value = "idMarvelCharacter") Integer idMarvelCharacter,
                                                   @Param(value = "name") String name);
}
