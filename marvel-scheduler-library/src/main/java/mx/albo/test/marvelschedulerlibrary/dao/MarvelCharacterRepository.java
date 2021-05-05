package mx.albo.test.marvelschedulerlibrary.dao;

import mx.albo.test.marvelschedulerlibrary.model.MarvelCharacter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarvelCharacterRepository extends CrudRepository<MarvelCharacter, Integer> {

}
