package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.Item;
import com.example.dto.ItemImage;

@Mapper
public interface ItemMapper {

    // 물품 일괄수정
    public int updateItemBatch ( List<Item> list );

    // 물품번호에 해당하는 항목 반환
    public List<Item> selectItemNoList ( long[] no );

    // 물품 일괄삭제
    public int deleteItemBatch ( long[] no );

    // 판매자 ID별 해당물품 조회
    public List<Item> selectItemSellerList( String seller );

    // 물품 일괄등록
    public int insertItemBatch( List<Item> list );

    // 물품전체조회
    public List<Item> selectItemList( );
    
    // 물품 이미지 등록
    public int insertItemImage( ItemImage obj );

    // 이미지 번호가 전송되면 1개의 이미지 정보 반환 (개별이미지 1개)
    public ItemImage selectItemImageOne( long no );

    // 물품번호를 전송하면 해당하는 이미지번호를 반환
    public List<Long> selectItemImageNo( long itemno );

}