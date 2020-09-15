package restboard.restboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restboard.restboard.controller.BoardController;
import restboard.restboard.domain.Board;
import restboard.restboard.dto.Request;
import restboard.restboard.dto.Response;
import restboard.restboard.repository.BoardRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long uploadBoard(Request request){
        Board board = request.toEntity(request);
        boardRepository.save(board);
        return board.getId();
    }

    @Transactional
    public void deleteBoard(Long boardId){
        Board board = boardRepository.findById(boardId).get();
        boardRepository.delete(board);
    }

    @Transactional
    public void updateBoard(Request request,Long boardId){
        Board board = boardRepository.findById(boardId).get();
        board.change(request.getTitle(), request.getContent(), request.getWriter());
    }

    public Response findById(Long boardId){
        Board board = boardRepository.findById(boardId).get();
        Response boardResponse = new Response(board);
        return boardResponse;
    }

    public List<Response> findAll(){
        List<Board> boardList = boardRepository.getBoardsDesc();
        List<Response> list = boardList.stream()
                .map(m -> new Response(m))
                .collect(Collectors.toList());
        return list;
    }

    @Transactional
    public void addReadCount(Long boardId) {
        Board board = boardRepository.findById(boardId).get();
        board.addReadCount();
    }


}
