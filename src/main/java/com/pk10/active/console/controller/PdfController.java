package com.pk10.active.console.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pk10.active.console.config.GlobalConfig;
import com.pk10.active.console.service.UserService;
@RequestMapping("pdf")
@Controller
public class PdfController {

@Autowired
UserService userService;

@RequestMapping(value = "/a", method = RequestMethod.GET)
public ModelAndView a() {
    Map<String,Object> globalModel = new HashMap<>();
    globalModel.put("mobile", GlobalConfig.readProperty("form.mobile"));
    globalModel.put("email", GlobalConfig.readProperty("form.email"));
    globalModel.put("name", GlobalConfig.readProperty("form.name"));
    globalModel.put("mode", "pdf");

    // return a view which will be resolved by an excel view resolver
    return new ModelAndView("", globalModel);
}

@RequestMapping(value = "/b", method = RequestMethod.GET)
public ModelAndView b() {
    Map<String,Object> globalModel = new HashMap<>();
    globalModel.put("mobile", GlobalConfig.readProperty("form.mobile"));
    globalModel.put("email", GlobalConfig.readProperty("form.email"));
    globalModel.put("name", GlobalConfig.readProperty("form.name"));
    globalModel.put("mode", "pdf");

    // return a view which will be resolved by an excel view resolver
    return new ModelAndView("", globalModel);
}

@RequestMapping(value = "/simpleForm", method = RequestMethod.GET)
public ModelAndView simpleForm() {
    Map<String,Object> globalModel = new HashMap<>();
    globalModel.put("mobile", GlobalConfig.readProperty("form.mobile"));
    globalModel.put("email", GlobalConfig.readProperty("form.email"));
    globalModel.put("name", GlobalConfig.readProperty("form.name"));
    globalModel.put("mode", "pdf");

    // return a view which will be resolved by an excel view resolver
    return new ModelAndView("", globalModel);
}
}