package com.example.dto;

import lombok.Data;

@Data
public class Board1DTO {
    private long no;
    private String title;
    private String writer;

    public Board1DTO(long no, String title, String writer){
        super();
        this.no = no;
        this.title = title;
        this.writer = writer;
    }
}
