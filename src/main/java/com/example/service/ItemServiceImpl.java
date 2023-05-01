package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.Item;
import com.example.dto.ItemImage;
import com.example.mapper.ItemMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class ItemServiceImpl implements ItemService{

    final ItemMapper iMapper; // 매퍼 객체 생성 @Autowired Itemmapper iMapper;

    @Override
    public List<Item> selectItemList() {
        try {
            return iMapper.selectItemList();
        }
        catch (Exception e) {
            e.printStackTrace(); // 오류발생시 터미널에 표시
            return null; // 오류발생시 null 반환
        }
    }

    @Override
    public int insertItemImage(ItemImage obj) {
        try {
            return iMapper.insertItemImage(obj);
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
       
    }
    
}
