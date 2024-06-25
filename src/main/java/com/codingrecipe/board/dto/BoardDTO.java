package com.codingrecipe.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class BoardDTO {

    private Long id;
    private String boardWriter;
    private String boardPass;
    private  String boardTitle;
    private  String boardContents;
    private  int boardHits;
    private String createdAt;
    //이게시글에 파일첨부가 됬냐 안됬냐를 확인하는 필드
    private int fileAttached;
    //게시글을 작성할때 파일을 담기위해 필요한 필드
    private MultipartFile boardFile;
}
