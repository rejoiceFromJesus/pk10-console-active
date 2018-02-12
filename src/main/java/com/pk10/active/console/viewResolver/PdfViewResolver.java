package com.pk10.active.console.viewResolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.pk10.active.console.view.ExcelView;
import com.pk10.active.console.view.FormPdfview;
import com.pk10.active.console.view.Pdfview;

/**
 * Created by aboullaite on 2017-02-24.
 */
public class PdfViewResolver implements ViewResolver {
    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
    	if(s.endsWith(".pdf")){
    		System.out.println(s);
    		FormPdfview view = new FormPdfview();
    		return view;
    		
    	}
    	return null;
    }
}
