package com.example.fileapi.repository;

import com.example.fileapi.model.Doc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocRepository extends JpaRepository<Doc,String> {
}
