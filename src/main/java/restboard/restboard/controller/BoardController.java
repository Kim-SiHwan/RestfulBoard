package restboard.restboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restboard.restboard.dto.Request;
import restboard.restboard.dto.Response;
import restboard.restboard.service.BoardService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public List<Response> getAll(){
        List<Response> list = boardService.findAll();
        return list;
    }

    @GetMapping
    public ResponseEntity boardHome(){
        return new ResponseEntity(getAll(), HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity view(@PathVariable("boardId") Long boardId){
        boardService.addReadCount(boardId);
        Response responseBoard = boardService.findById(boardId);
        return new ResponseEntity(responseBoard,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity uploadBoard(@RequestBody Request boardRequest){
        boardService.uploadBoard(boardRequest);
        return new ResponseEntity(getAll(),HttpStatus.CREATED);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity updateBoard(@PathVariable("boardId") Long boardId,
                                      @RequestBody Request boardRequest){
        boardService.updateBoard(boardRequest,boardId);
        return new ResponseEntity(getAll(),HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity deleteBoard(@PathVariable("boardId") Long boardId){
        boardService.deleteBoard(boardId);
        return new ResponseEntity(getAll(),HttpStatus.OK);
    }



}
