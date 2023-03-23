package com.doongie.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {

	// final붙이면 변하지않는 상수가 됨
	// 이미지 저장경로는 변할일없으니 
	public static final String FILE_UPLOAD_PATH = "D:\\박주영\\springProject\\upload\\memo\\image";
	
	// 파일 저장 --> 경로 생성 기능 수행
	
	public static String saveFile(int userId, MultipartFile file) {
		
		
		
		if(file == null) {
			return null;
		}
		
		
		
		// 사용자 별로 폴더를 구분
		// 중복되지않게 하기위해
		// 시간을 포함해서 구분 
		// UNIX_TIME : 1970년 1월 1일부터 흐른 시간을 표현하는 것 (millisecond 1/1000)
		// 폴더이름: userId_time (3_3949828284)

		String directoryName = "/" + userId + "_" + System.currentTimeMillis() + "/";
		
		// 디렉토리 생성
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		File directory = new File(directoryPath);
		
		if(!directory.mkdir()) {
			return null;
		} 
		
		
		// 파일 저장
		try {
			byte[] bytes = file.getBytes();
			
			String filePath = directoryPath + file.getOriginalFilename();
			Path path = Paths.get(filePath);
			
			Files.write(path, bytes);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			return null;
		}
		
		
		// 클라이언트에서 저장된 파일을 접근할 수 있는 경로를 리턴
		// 경로 규칙 : /images/2_283928392893/test.png
		// http://localhost:8080/images/2_283928392893/test.png
		
		return "/images" + directoryName + file.getOriginalFilename();
		
		
	}
}
