package com.example.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.example.dto.Board;

@Service
// Shift + alt + o = [자동으로 import]!
public interface BoardService {
    // 게시글 등록
    public int insertBoardOne(@Param("obj") Board obj);

    // 게시글 전체조회
    public List<Board> selectBoardList();

    // 게시글 하나조회
    public Board selectBoardOne(long no);

    // 게시글 수정하기
    public int updateBoardOne(@Param("obj") Board board);

    // 게시글 삭제하기
    public int deleteBoardOne(long no);
}
