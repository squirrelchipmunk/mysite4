package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	public String restore(MultipartFile file) {
		System.out.println("FileService.restore()");
		
		String saveDir = "C:\\javaStudy\\upload";
		
		//원본파일 이름
		String orgName = file.getOriginalFilename();
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		
		//저장파일 이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		
		//파일 경로
		String filePath = saveDir+"\\"+saveName;
		
		//파일 사이즈
		long fileSize = file.getSize();
		
		
		//파일을 하드디스크에 저장(운영)
		try {
			byte[] fileData = file.getBytes();
			BufferedOutputStream bout = new BufferedOutputStream( new FileOutputStream(filePath));
			bout.write(fileData);
			bout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//db에 저장
		return saveName;
	}

}
