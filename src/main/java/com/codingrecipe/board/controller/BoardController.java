package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.BoardFileDTO;
import com.codingrecipe.board.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String save(){

        return "save";
    }
    //포스트방식 글작성
    @PostMapping("/save")
    public String save(BoardDTO boardDTO) throws IOException {
        System.out.println("boardDTO" + boardDTO);
        boardService.save(boardDTO);
        return "redirect:/list";
    }
    //목록
    @GetMapping("/list")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        System.out.println("boardDTOList" + boardDTOList);
        model.addAttribute("boardList" , boardDTOList);
        return "list";


    }
    //상세조회
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        //조회수 처리
        boardService.updateHits(id);
        //상세내용을 가져온다
        BoardDTO boardDTO = boardService.findById(id);
        System.out.println("boardDTO" + boardDTO);
        model.addAttribute("board" , boardDTO);
        if(boardDTO.getFileAttached() ==1){
           BoardFileDTO boardFileDTO = boardService.findFile(id);
           model.addAttribute("boardFile" ,boardFileDTO);

        }
        return "detail";

    }

    //수정하러 들어가는메소드
    @GetMapping("/update/{id}")
    public String update (@PathVariable("id") Long id , Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "update";
    }
    //수정입력메소드
    @PostMapping("/update/{id}")
    public String update(BoardDTO boardDTO ,Model model){
        boardService.update(boardDTO);
        //업데이트가 반영된 목록을 가져오기
        BoardDTO dto = boardService.findById(boardDTO.getId());
        //반영된 목록을 모델에 담는다.
        model.addAttribute("board" ,dto);
        //모델에 담은 목록을 상세페이지 뿌려준다.
        return "detail";




    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        boardService.delete(id);
        return "redirect:/list";
    }


}



