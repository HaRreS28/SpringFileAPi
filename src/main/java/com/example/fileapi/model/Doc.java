package com.example.fileapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="docs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doc {
    @Id
    private String id;
    private String docType;
    @Lob
    private  byte [] data;

}
