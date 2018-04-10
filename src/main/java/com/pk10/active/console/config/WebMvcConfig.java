package com.pk10.active.console.config;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.pk10.active.console.viewResolver.ExcelViewResolver;
import com.pk10.active.console.viewResolver.PdfViewResolver;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
  @Autowired 
  HandlerInterceptor sessionIntercepTor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
	   registry.addInterceptor(sessionIntercepTor);
    }
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/files/**")
              .addResourceLocations("file:upload-files/");
  }

@Override
public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer
            .defaultContentType(MediaType.APPLICATION_JSON)
            .favorPathExtension(true);
}

@Bean
public MultipartResolver multipartResolver() {
   return new StandardServletMultipartResolver() {
     @Override
     public boolean isMultipart(HttpServletRequest request) {
        String method = request.getMethod().toLowerCase();
        //By default, only POST is allowed. Since this is an 'update' we should accept PUT.
        if (!Arrays.asList("put", "post").contains(method)) {
           return false;
        }
        String contentType = request.getContentType();
        return (contentType != null &&contentType.toLowerCase().startsWith("multipart/"));
     }
   };
}
@Bean
public FilterRegistrationBean httpPutFormContentFilter() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    HttpPutFormContentFilter httpPutFormContentFilter = new HttpPutFormContentFilter();
    registration.setFilter(httpPutFormContentFilter);
    registration.addUrlPatterns("/*");
    return registration;
}

/*
 * Configure View resolver to provide XLS output using Apache POI library to
 * generate XLS output for an object content
 */
@Bean
public ViewResolver excelViewResolver() {
    return new ExcelViewResolver();
}

@Bean
public ViewResolver pdfViewResolver() {
    return new PdfViewResolver();
}


}