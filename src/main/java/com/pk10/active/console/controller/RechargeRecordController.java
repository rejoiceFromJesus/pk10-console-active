package com.pk10.active.console.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk10.active.console.entity.RechargeRecord;
import com.pk10.active.console.service.RechargeRecordService;

@RestController
@RequestMapping("/recharge-record")
public class RechargeRecordController extends BaseController<RechargeRecord, RechargeRecordService> {

}
