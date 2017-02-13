package org.oneuse.dainbow.goals;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface GoalRepository extends CrudRepository<Goal, Long> {
    Optional<Goal> findById(long id);
}
