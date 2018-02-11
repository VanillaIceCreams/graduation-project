package com.zxj.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



import common.ImageDate;

@Controller
public class FileLoadController {

	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public ImageDate uploadFile(MultipartFile uploadFile,HttpSession session){
			
		FileOutputStream os=null;
		ImageDate result = new ImageDate();
		//判断图片是否为空
		if (uploadFile.isEmpty()) {
			result.setError(1);
			result.setMessage("图片为空");
			return result;
		}
		//上传到图片服务器
		try {
			//取图片名
			String originalFilename = uploadFile.getOriginalFilename();
			//取扩展名
			String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
			//获取图片的字节
			byte[] bytes = uploadFile.getBytes();
			
			String newName = UUID.randomUUID()+extName;
						
			String url = session.getServletContext().getRealPath("/")+"WEB-INF\\image\\"+newName;
			File file = new File(url);
			 os = new FileOutputStream(file);
			os.write(bytes);
			//把url响应给客户端
			result.setError(0);
			result.setUrl("http://localhost:8080/image/"+newName);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(1);
			result.setMessage("图片上传失败");
		}finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
