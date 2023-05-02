package com.example.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.Item;
import com.example.dto.ItemImage;
import com.example.mapper.ItemMapper;
import com.example.service.ItemService;

@Controller
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired ItemService iService; // 서비스 객체 생성
    @Autowired ItemMapper iMapper; // 서비스 사용하지 않고 바로 매퍼 호출.(나중에는 서비스를 통해서 할것!)
    @Autowired ResourceLoader resourceLoader; // resources 폴더의 파일 불러오기

    @Value("${default.image}") String defaultImage; // 기본이미지

    /* ------------------------------------------------------------------------------------------------- */
                                                 // image //

    // <img src="@{/item/image(no=1)}">
    // String = html 파일을 표시
    // ResponseEntity<byte[]>  =>  이미지나 동영상등을 표시
    // 127.0.0.1:9090/ROOT/item/image?no=1
    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> image( @RequestParam(name = "no", defaultValue = "0" ) long no ) throws IOException {
        ItemImage obj = iMapper.selectItemImageOne(no);


        HttpHeaders headers = new HttpHeaders();
        if( obj != null ){ // 이미지가 존재하는 경우
            if( obj.getFilesize() > 0 ){
                headers.setContentType( MediaType.parseMediaType( obj.getFiletype() ) );
                ResponseEntity<byte[]> response = new ResponseEntity<>( obj.getFiledata(), headers, HttpStatus.OK );
                return response;
            }
        }
        
        InputStream is = resourceLoader.getResource("classpath:/static/images/default.png").getInputStream(); // exception 발생됨.
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>( is.readAllBytes(), headers, HttpStatus.OK );
    }
    /* ------------------------------------------------------------------------------------------------- */
                                                // insertImage //

    // /item/insertimage.do?no=7 => name값은 no이고, value값은 숫자 7이 전달됨.
    // <input type="text" name="no" value="7" />
    @GetMapping(value = "/insertimage.do")
    public String insertimageGet(@RequestParam(name = "no", defaultValue = "0", required = false) long no,
            Model model) {
        if (no == 0) {
            return "redirect:selectlist.do"; // 상대경로로 이동, 가장 마지막 주소만 변경해서 이동
        }
        model.addAttribute("itemno", no);

        // 현재 물품에 해당하는 이미지 번호
        List<Long> imgNo = iMapper.selectItemImageNo(no);
        System.out.println("insertimage.do =>" + imgNo.toString());

        model.addAttribute("imgno", imgNo);
        model.addAttribute("itemno", no);
        return "/item/insertimage"; // resources/ templates/item폴더/insertimage.html
    }

    @PostMapping(value = "/insertimage.do")
    public String insertImagePOST( @ModelAttribute ItemImage obj, 
            @RequestParam(name = "file1") MultipartFile file1) throws IOException {
        obj.setFilename( file1.getOriginalFilename() );
        obj.setFilesize( file1.getSize() );
        obj.setFiletype( file1.getContentType() );
        obj.setFiledata( file1.getBytes() ); //exception발생됨.
        System.out.println(obj.toString()); //확인용

        int ret = iService.insertItemImage(obj);
        if( ret == 1) {
            return "redirect:insertimage.do?no=" + obj.getItemno(); 
        }
        return "redirect:insertimage.do?no=" + obj.getItemno(); 
    }
    /* ------------------------------------------------------------------------------------------------- */
                                                // selectlist //
    // 127.0.0.1:9090/ROOT/item/selectList.do
    @GetMapping(value = "/selectlist.do")
    public String selectListGet(Model model) {
        // 1. 서비스를 호출하여 물품목록 받기
        List<Item> list = iService.selectItemList();

        // 2. model을 활용하여 view로 받은목록 전달하기
        model.addAttribute("list", list);

        // 3. view를 화면에 표시하기
        return "/item/selectlist"; // resources/templates (/item폴더를 생성, selectlist.html을 생성)
    }
    /* ------------------------------------------------------------------------------------------------- */
}
