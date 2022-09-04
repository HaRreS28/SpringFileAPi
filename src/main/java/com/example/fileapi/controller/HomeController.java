package com.example.fileapi.controller;

import com.example.fileapi.model.Doc;
import com.example.fileapi.service.DocService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final DocService docService;

    public HomeController(DocService docService) {
        this.docService = docService;
    }

    @GetMapping("/")
    public String home(Model model){
        List<Doc> all = docService.findAll();
        model.addAttribute("files",all);
        return "index";
    }
}
