package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.Board;
import com.example.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 클래스에서만 가능함, 인터페이스에서는 안됨
public class BoardServiceImpl implements BoardService {
    // @Autowired를 통해서 직접 Mapper를 지정해줌!

    /* 반드시 붙어있어야 함! */
    final BoardMapper bMapper; // @Autowired BoardMapper bMapper;
    /* /반드시 붙어있어야 함! */


    // 게시글 한개 등록
    @Override
    public int insertBoardOne(Board obj) {
        try {
            return bMapper.insertBoard(obj);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'insertBoardOne'");
        }
    }

    /* ---------------------------------------------------------------------------------------------------- */

    // 게시글 전체 조회
    @Override
    public List<Board> selectBoardList() {
        try {
            return bMapper.selectBoard();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    /* ---------------------------------------------------------------------------------------------------- */

    // 게시글 한개 조회
    @Override
    public Board selectBoardOne(long no) {
       try {
           return bMapper.selectBoardOne(no);
       } catch (Exception e) {
           e.printStackTrace();
        return null;
       }
    }
    /* ---------------------------------------------------------------------------------------------------- */

    // 게시글 한개 수정
    @Override
    public int updateBoardOne(Board board) {
        try {
            System.out.println(board.toString());
            return bMapper.updateBoardOne(board);

        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'updateBoardOne'");
        }
        
    }
    /* ---------------------------------------------------------------------------------------------------- */
    
    // 게시글 한개 삭제
    @Override
    public int deleteBoardOne(long no) {
        try {
            System.out.println(no);
            return bMapper.deleteBoardOne(no);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'deleteBoardOne'");
            
        }
        
    }

   

}
