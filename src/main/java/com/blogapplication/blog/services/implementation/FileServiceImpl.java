package com.blogapplication.blog.services.implementation;

import com.blogapplication.blog.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

       @Override
       public String uploadImage(String path, MultipartFile file) throws IOException {

           //File name
           String fileName=file.getOriginalFilename();


           String randomId=UUID.randomUUID().toString();
           fileName=randomId.concat(fileName.substring(fileName.lastIndexOf(".")));
           //FullPath
           String filePath = path+ File.separator+fileName;

           // Folder exists
           File f =new File(path);
           if(!f.exists()){
               f.mkdir();
           }

           //File copy
           Files.copy(file.getInputStream(), Paths.get(filePath));
           System.out.println("Returnoing the Filename as "+fileName);
           return fileName;
       }

       @Override
       public InputStream getResource(String path,String fileName) throws FileNotFoundException {
              String fullPathFile = path+File.separator+fileName;
              InputStream ipSt = new FileInputStream(fullPathFile);
              return ipSt;
       }
}
