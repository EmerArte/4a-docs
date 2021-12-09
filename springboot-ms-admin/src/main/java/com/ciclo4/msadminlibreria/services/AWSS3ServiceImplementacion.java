package com.ciclo4.msadminlibreria.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
@Service
public class AWSS3ServiceImplementacion implements AWSS3Service{

    private static final Logger LOGGER = LoggerFactory.getLogger(AWSS3ServiceImplementacion.class);

    @Autowired
    private AmazonS3 aws3;

    @Value("${aws.s3.bucket}")
    private String bucketName;


    @Override
    public String uploadFile(MultipartFile file) {
        File mainFile = new File(file.getOriginalFilename());
        try(FileOutputStream stream = new FileOutputStream(mainFile)){
            stream.write(file.getBytes());
            String newFileName = System.currentTimeMillis() + "_" + mainFile.getName();
            PutObjectRequest request = new PutObjectRequest(bucketName, newFileName, mainFile);
            aws3.putObject(request);
            return newFileName;
        }catch (IOException e){
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
