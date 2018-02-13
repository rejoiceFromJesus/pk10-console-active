package com.pk10.active.console.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk10.active.console.entity.TradeRecord;
import com.pk10.active.console.service.TradeRecordService;

@RestController
@RequestMapping("/trade-record")
public class TraceRecordController extends BaseController<TradeRecord, TradeRecordService> {

}
