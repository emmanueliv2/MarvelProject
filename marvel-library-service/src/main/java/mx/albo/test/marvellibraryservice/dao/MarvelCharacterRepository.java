package mx.albo.test.marvellibraryservice.dao;

import mx.albo.test.marvellibraryservice.model.MarvelCharacter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarvelCharacterRepository extends CrudRepository<MarvelCharacter, Integer> {

    MarvelCharacter findByName(String name);

}
