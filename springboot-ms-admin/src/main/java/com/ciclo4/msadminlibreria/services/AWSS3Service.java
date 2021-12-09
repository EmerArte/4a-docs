package com.ciclo4.msadminlibreria.services;

import org.springframework.web.multipart.MultipartFile;

public interface AWSS3Service {

    String uploadFile(MultipartFile file);
}
