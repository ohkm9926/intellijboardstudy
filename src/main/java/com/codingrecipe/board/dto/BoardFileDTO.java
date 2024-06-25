package com.codingrecipe.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileDTO {

     private Long id;
     //이파일이 어떤게시글에 담겨지는 알아야되는 id
     private Long boardId;
     //원본파일
     private String originalFileName;
     //원본파일이 언제 바뀔수 있는지 데이터를 추가하는 파일필드
     private String storedFileName;

}
