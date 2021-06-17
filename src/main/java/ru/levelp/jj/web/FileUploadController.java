package ru.levelp.jj.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileUploadController {
    @GetMapping("/file/upload")
    public String showFileUploadForm(Model model) {
        model.addAttribute("fileSize", -1L);
        return "fileupload";
    }

    @PostMapping("/file/upload")
    public String handleFile(
            @RequestParam String description,
            @RequestParam MultipartFile file,
            Model model
    ) throws IOException {
//        Blob blob = BlobProxy.generateProxy(file.getInputStream(), file.getSize());
        model.addAttribute("fileSize", file.getSize());
        return "fileupload";
    }
}
