package restboard.restboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restboard.restboard.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
}
