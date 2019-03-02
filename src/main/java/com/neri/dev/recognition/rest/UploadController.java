package com.neri.dev.recognition.rest;

import com.dropbox.core.v2.files.FileMetadata;

import com.neri.dev.recognition.service.DropboxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    DropboxServiceImpl dropbox;


    @PostMapping("/")
    public FileMetadata handleFileUpload(@RequestParam("file") MultipartFile file, String path) {
        try {
            InputStream inputStream = new ByteArrayInputStream(file.getBytes());
            return dropbox.uploadFile(path,inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
