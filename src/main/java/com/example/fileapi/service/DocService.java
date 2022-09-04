package com.example.fileapi.service;

import com.example.fileapi.model.Doc;
import com.example.fileapi.repository.DocRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DocService {
    private final DocRepository docRepository;

    public DocService(DocRepository docRepository) {
        this.docRepository = docRepository;
    }

@Transactional
    public void saveFile(MultipartFile file) throws IOException {
        Doc doc = new Doc(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        docRepository.save(doc);
    }

    public Optional<Doc> findById(String id){
        return docRepository.findById(id);
    }

    public List<Doc> findAll(){
        return docRepository.findAll();
    }

}
