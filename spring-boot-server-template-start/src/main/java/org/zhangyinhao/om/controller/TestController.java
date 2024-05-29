package org.zhangyinhao.om.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhangyinhao.base.result.BaseResult;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Slf4j
@Api(value = "testController", tags = "testController")
public class TestController {

    @RequestMapping(value = {"/testError"}, method = {POST, GET})
    public BaseResult testError() {
        String ss = null;
        ss.equals("");
        return BaseResult.successResult();
    }


}
