package com.pk10.active.console.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk10.active.console.controller.BaseController;
import com.pk10.active.console.entity.BetInfo;

@RestController
@RequestMapping("/bet-info")
public class BetInfoController extends BaseController<BetInfo, BetInfoService> {

}
