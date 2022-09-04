package com.example.fileapi.controller;

import com.example.fileapi.model.Doc;
import com.example.fileapi.service.DocService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DocController {
private final DocService docService;

    public DocController(DocService docService) {
        this.docService = docService;
    }

    @PostMapping("/upload_file")
    public RedirectView uploadFiles(@RequestBody MultipartFile[] files){
        for (MultipartFile f:files) {
            try {
                docService.saveFile(f);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String path = UriComponentsBuilder.fromPath("/").build().toString();
        String path2 = UriComponentsBuilder.fromPath("/").query("s").build().toString();
        String path24 = UriComponentsBuilder.fromPath("/").queryParam("s","s").build().toString();
        System.out.println("path 1 "+path);
        System.out.println("path 2 "+path2);
        System.out.println("path 24 "+path24);
        String path3 = UriComponentsBuilder.fromPath("/{s}/{s}")
                .buildAndExpand("s1","s2").toString();
        System.out.println("path 3 "+path3);
        return new RedirectView(path,true,false);
    }

    @GetMapping("/download_file/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId){
        Optional<Doc> byId = docService.findById(fileId);
        return byId.<ResponseEntity<Resource>>map(doc -> ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=" +
                        doc.getId()).body(new ByteArrayResource(doc.getData())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
