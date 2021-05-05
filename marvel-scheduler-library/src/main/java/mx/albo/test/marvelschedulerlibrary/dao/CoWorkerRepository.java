package mx.albo.test.marvelschedulerlibrary.dao;

import mx.albo.test.marvelschedulerlibrary.model.CoWorker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoWorkerRepository extends CrudRepository<CoWorker, Integer> {
}
