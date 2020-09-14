package restboard.restboard.dto;

import lombok.Getter;
import restboard.restboard.domain.Board;

@Getter
public class Request {
    private String title;
    private String content;
    private String writer;


    public Request(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Board toEntity(Request request){
        return Board.makeBoard()
                .title(request.getTitle())
                .content(request.getContent())
                .writer(request.getWriter())
                .build();
    }
}
