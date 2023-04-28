package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.dto.Board;

@Mapper
public interface BoardMapper {
    // 글쓰기
    @Insert(value = {
            "	INSERT INTO board( title, content, writer)	",
            "	VALUES( #{obj.title}, #{obj.content}, #{obj.writer}  )	"
    })
    public int insertBoard(@Param("obj") Board board);

    /* ------------------------------------------------------------------- */

    // 전체목록 조회
    @Select(value = {
            "	SELECT * FROM board	ORDER BY no DESC	"
    })
    public List<Board> selectBoard();

    /* ------------------------------------------------------------------- */

    // 한개조회
    @Select(value = {
            "	SELECT * FROM board		",
            "	WHERE no = #{no}	"
    })
    public Board selectBoardOne(@Param("no") long no);

    /* ------------------------------------------------------------------- */

    // 게시글 수정하기
    // sql문이 없음 => resources/mappers/파일명Mapper.xml
    public int updateBoardOne(Board board);

    /* ------------------------------------------------------------------- */

    // 게시글 삭제하기
    // SQL문이 없음 => resources/mappers/파일명Mapper.xml
    public int deleteBoardOne(long no);
    
}
