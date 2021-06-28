package ru.levelp.jj.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.levelp.jj.dao.FileUploadItemRepository;
import ru.levelp.jj.model.FileUploadItem;

import java.io.InputStream;
import java.sql.SQLException;

@RestController
public class FileShowController {
    @Autowired
    private FileUploadItemRepository items;

//    @GetMapping("/file/show/{id}")
//    @ResponseBody
//    public ResponseEntity<InputStream> showFile(@PathVariable int id) throws SQLException {
//        FileUploadItem file = items.findById(id).orElseThrow();
//        InputStream fileStream = file.getFileContent().getBinaryStream();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "image/png");
//
//        ResponseEntity<InputStream> response = new ResponseEntity<InputStream>(fileStream,
//                headers,
//                200
//        );
//
//        return response;
//    }

    @GetMapping("/file/show/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> showFile(@PathVariable int id) throws SQLException {
        FileUploadItem file = items.findById(id).orElseThrow();
        byte[] content = file.getFileContent().getBytes(0, (int)file.getSize());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "image/png");

        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(content,
                headers,
                200
        );

        return response;
    }
}
