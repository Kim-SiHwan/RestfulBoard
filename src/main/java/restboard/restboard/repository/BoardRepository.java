package restboard.restboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import restboard.restboard.domain.Board;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    @Query("select b from Board b order by b.id desc ")
    public List<Board> getBoardsDesc();
}
