package org.zhangyinhao.om.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhangyinhao.base.result.BaseResult;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Slf4j
@Tag(name = "TestController",description = "TestController")
public class TestController {

    @RequestMapping(value = {"/testError"}, method = {POST, GET})
    public BaseResult testError() {
        String ss = null;
        ss.equals("");
        return BaseResult.successResult();
    }


}
