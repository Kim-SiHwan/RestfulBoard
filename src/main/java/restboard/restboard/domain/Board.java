package restboard.restboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardId")
    private Long id;

    private String title;

    private String content;

    private String writer;

    private Integer read;

    private LocalDateTime createDate;

    public void addReadCount(){this.read+=1;}

    public void change(String title, String content, String writer){
        this.title=title;
        this.content=content;
        this.writer=writer;
    }

    @Builder(builderClassName = "makeBoard", builderMethodName = "makeBoard")
    public Board(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.read = 0;
        this.createDate = LocalDateTime.now();
    }
}
