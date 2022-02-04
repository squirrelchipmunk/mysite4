package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	@Autowired
	private GalleryDao galleryDao;
	
	public void write(GalleryVo galleryVo, MultipartFile file) {
		//저장 폴더
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
		
		// VO > 이전까지 userNo, content 저장. no는 db에서.
		galleryVo.setFilePath(filePath);
		galleryVo.setOrgName(orgName);
		galleryVo.setSaveName(saveName);
		galleryVo.setFileSize(fileSize);
		
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
		galleryDao.insert(galleryVo);
		
	}

	public List<GalleryVo> getList() {
		return galleryDao.selectList();
	}

	public GalleryVo read(int no) {
		return galleryDao.selectByNo(no);
	}

	public String remove(int no) {
		int count = galleryDao.delete(no);
		if(count > 0)
			return "success";
		else
			return "fail";
	}

}
