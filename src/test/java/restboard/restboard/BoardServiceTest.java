package restboard.restboard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import restboard.restboard.domain.Board;
import restboard.restboard.dto.Request;
import restboard.restboard.dto.Response;
import restboard.restboard.repository.BoardRepository;
import restboard.restboard.service.BoardService;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void 게시물_생성_조회 (){
    //given
        Request request = new Request("제목","내용","유저");
        Long boardId = boardService.uploadBoard(request);
    //when
        Response response = boardService.findById(boardId);
    //then
        assertEquals("제목",response.getTitle());
    }

    @Test
    public void 게시물_목록_조회 (){
    //given
        for(int i=1; i<=10; i++){
            Request request = new Request("제목"+i,"내용"+i,"유저"+i);
            boardService.uploadBoard(request);
        }
    //when
        List<Response> list = boardService.findAll();
    //then
        assertEquals(10,list.size());
        assertEquals("제목10",list.get(9).getTitle());
        list.forEach(l-> System.out.println(l));
    }

    @Test(expected = NoSuchElementException.class)
    public void 게시물_삭제 (){
    //given
        Request request = new Request("제목","내용","유저");
        Long boardId = boardService.uploadBoard(request);
    //when
        boardService.deleteBoard(boardId);
    //then
        Response response = boardService.findById(boardId);
    }

    @Test
    public void 게시물_수정 (){
    //given
        Request request = new Request("제목","내용","유저");
        Long boardId = boardService.uploadBoard(request);
    //when
        Request updateParam = new Request("수정된제목","수정된내용","수정된유저");
        boardService.updateBoard(updateParam,boardId);
        Response response = boardService.findById(boardId);
    //then
        assertEquals("수정된제목",response.getTitle());

    }

    @Test
    public void 더미데이터_생성 (){
        for(int i=1; i<=20; i++){
            System.out.println(
                    "INSERT INTO board (title, writer, content, read, create_date) values ('title"+i+"', 'User' , 'content"+i+"', 0, now());"
            );
        }
    }
}
