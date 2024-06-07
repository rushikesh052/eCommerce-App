package com.rushi.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
public static String saveFile(String fileName, MultipartFile file)throws Exception {
		
		Path uploadPath= Paths.get("uploads/product-images");
		if(!Files.exists(uploadPath)){
			Files.createDirectories(uploadPath);
		}
		Path filepath = uploadPath.resolve(file.getOriginalFilename());
		Files.copy(file.getInputStream(), filepath,StandardCopyOption.REPLACE_EXISTING);
		return filepath.getFileName().toString();

	}
	

}
