package restboard.restboard.dto;

import lombok.Getter;
import restboard.restboard.domain.Board;

import java.time.LocalDateTime;

@Getter
public class Response {
    private String title;
    private String content;
    private String writer;
    private Integer read;
    private LocalDateTime createDate;

    public Response (Board board){
        this.title= board.getTitle();
        this.content= board.getContent();
        this.writer= board.getWriter();
        this.read=0;
        this.createDate=LocalDateTime.now();
    }
}
