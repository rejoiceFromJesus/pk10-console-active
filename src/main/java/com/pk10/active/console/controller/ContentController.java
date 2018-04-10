/**
 * 系统项目名称
 * com.pk10.active.console.controller
 * ContentController.java
 * 
 * 2018年4月9日-下午3:27:07
 *  2018金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.joda.time.DateTime;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pk10.active.console.common.bean.Result;
import com.pk10.active.console.entity.Content;
import com.pk10.active.console.service.ContentService;

/**
 *
 * ContentController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2018年4月9日 下午3:27:07
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("/content")
public class ContentController extends BaseController<Content, ContentService>{
	
	  private final Path rootLocation;

	    public ContentController() {
	        this.rootLocation = Paths.get("upload-files");
	    }
	
	@PostMapping("/save")
	public Result<Void> save(Content content,@RequestParam(name="picFile") MultipartFile uploadfile) throws IOException{
		String fileName = "";
		if(!uploadfile.isEmpty()){
			DateTime now = DateTime.now();
			fileName = now.toString("yyyy-MM-dd-HH-mm-ss-SSS-")+uploadfile.getOriginalFilename();
			fileName = StringUtils.cleanPath(fileName);
			Files.copy(uploadfile.getInputStream(), this.rootLocation.resolve(fileName),
	                StandardCopyOption.REPLACE_EXISTING);
			content.setPic("/files/"+fileName);
		}
		this.getService().saveSelective(content);
		return Result.success(null);
	}
	@PutMapping("/save")
	public Result<Void> upload(Content content,@RequestParam(name="picFile") MultipartFile uploadfile) throws IOException{
		String fileName = "";
		if(!uploadfile.isEmpty()){
			DateTime now = DateTime.now();
			fileName = now.toString("yyyy-MM-dd-HH-mm-ss-SSS-")+uploadfile.getOriginalFilename();
			fileName = StringUtils.cleanPath(fileName);
			Files.copy(uploadfile.getInputStream(), this.rootLocation.resolve(fileName),
	                StandardCopyOption.REPLACE_EXISTING);
			content.setPic("/files/"+fileName);
		}
		this.getService().updateByIdSelective(content);
		return Result.success(null);
	}
	

}
