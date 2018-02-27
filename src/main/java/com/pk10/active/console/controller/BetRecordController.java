package com.pk10.active.console.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk10.active.console.entity.BetRecord;
import com.pk10.active.console.service.BetRecordService;

@RequestMapping("/bet-record")
@RestController
public class BetRecordController extends BaseController<BetRecord, BetRecordService> {

}
