/**
 * 系统项目名称
 * com.pk10.active.console
 * Regulatorsconsole.java
 * 
 * 2017年5月10日-下午2:23:55
 *  2017金融街在线公司-版权所有
 *
 */
package com.pk10.active.console;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pk10.active.console.common.constant.ContentTypeEnum;
import com.pk10.active.console.common.util.ImageService;
import com.pk10.active.console.entity.Content;
import com.pk10.active.console.service.ContentService;

/**
 *
 * Regulatorsconsole
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年5月10日 下午2:23:55
 * 
 * @version 1.0.0
 *
 */
@Controller
@EnableScheduling
@EnableCaching
@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages="com.pk10.active.console")
@MapperScan(basePackages = "com.pk10.active.console.mapper")
public class ActiveConsole {

	public static final String QRCODE_ENDPOINT = "/public/qrcode";
	public static final long THIRTY_MINUTES = 1800000; 
	
	@Autowired
	ContentService contentService;
	
	@Autowired
	ImageService imageService;

	
	public static void main(String[] args) {
		new SpringApplicationBuilder(ActiveConsole.class)
				.properties(
						"spring.config.name:application,constant,activemq",
						"spring.config.location:classpath:/config/").build()
				.run(args);
	}
	
	@GetMapping(value = QRCODE_ENDPOINT, produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getQRCode(HttpServletRequest request) {
		try { 
			Content cons = new Content();
			cons.setType(ContentTypeEnum.PK10_ADDRESS.value());
			String contentStr = contentService.queryOne(cons).getContent();
			System.err.println(contentStr);
			return ResponseEntity.ok()
					.body(imageService.generateQRCodeAsync(contentStr, 256, 256).get());
		} catch (Exception ex) {
			throw new InternalServerError("Error while generating QR code image.", ex);
		}
	}
	
	




	  @Scheduled(fixedRate = THIRTY_MINUTES)
		@ResponseStatus(HttpStatus.NO_CONTENT)
		@DeleteMapping(value = QRCODE_ENDPOINT)
		public void deleteAllCachedImages() {
			imageService.purgeCache();
		}

		@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
		public class InternalServerError extends RuntimeException {

			private static final long serialVersionUID = 1L;

			public InternalServerError(final String message, final Throwable cause) {
				super(message);
			}

		}

}

