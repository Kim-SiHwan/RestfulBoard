package restboard.restboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import restboard.restboard.dto.Response;
import restboard.restboard.service.BoardService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public List<Response> getAll(){
        List<Response> list = boardService.findAll();
        return list;
    }

    @GetMapping("/")
    public ResponseEntity boardHome(){
        return new ResponseEntity(getAll(), HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity view(@PathVariable("boardId") Long boardId){
        Response responseBoard = boardService.findById(boardId);
        return new ResponseEntity(responseBoard,HttpStatus.OK);
    }

    

}
