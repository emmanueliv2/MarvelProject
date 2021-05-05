package mx.albo.test.marvelschedulerlibrary.dao;

import mx.albo.test.marvelschedulerlibrary.model.Creator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorRepository extends CrudRepository<Creator, Integer> {
}
