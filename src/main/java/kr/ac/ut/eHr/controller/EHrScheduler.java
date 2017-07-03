package kr.ac.ut.eHr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.ac.ut.eHr.service.EHrSchedulerService;

@Component
public class EHrScheduler {

    @Autowired
    private EHrSchedulerService service;

    public void changeStatus(){

        //1. update psnnl_batch statcodeid (희망관서입력/희망관서입력종료)
        service.updatePsnnlBatchStat();

        //2. update psnnl from scheluler
        service.updatePsnnl();
    }

}
