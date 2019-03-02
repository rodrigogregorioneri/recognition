package com.neri.dev.recognition.rest;

import com.dropbox.core.v2.files.FileMetadata;

import com.neri.dev.recognition.service.DropboxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    DropboxServiceImpl dropbox;


    @PostMapping("/")
    public FileMetadata upload(@RequestParam("file") MultipartFile file, String path) {
        try {
            InputStream inputStream = new ByteArrayInputStream(file.getBytes());
            return dropbox.uploadFile(path,inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @GetMapping("/")
    public ResponseEntity<InputStreamResource>  download(String path){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        FileMetadata metadata = dropbox.getFileDetails(path);
        InputStreamResource resource = new InputStreamResource(dropbox.downloadFile(path));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + metadata.getName())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .contentLength(metadata.getSize())
                .body(resource);
    }
}
