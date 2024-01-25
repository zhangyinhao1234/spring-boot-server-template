/**
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.zhangyinhao.om.controller;

import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.GitProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhangyinhao.base.result.BaseResult;
import org.zhangyinhao.om.dal.order.Order;
import org.zhangyinhao.om.service.feign.HelloFeignClient;
import org.zhangyinhao.om.service.mq.producer.KafkaDefaultProducerClient;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@Slf4j
@Tag(name = "健康检查",description = "健康检查")
public class HealthController {
    @Autowired(required = false)
    private GitProperties git;

    private Map<String, String> gitBuildInfo = new HashMap<>();
    @Operation(summary = "服务健康检查",description = "服务健康检查")
    @RequestMapping(value = {"/status", "/health", "/healthy"}, method = {POST, GET})
    public BaseResult health() {
        if (git == null) return BaseResult.successResult();

        if (!gitBuildInfo.isEmpty()) {
            return BaseResult.successResult(gitBuildInfo);
        }

        git.forEach(entity -> {
            gitBuildInfo.put(entity.getKey(), entity.getValue());
        });
        return BaseResult.successResult(gitBuildInfo);
    }

    @Autowired
    private HelloFeignClient helloFeignClient;
    @RequestMapping(value = {"/hello"}, method = {POST})
    public BaseResult hello() {
        log.info("hello java .......call start.......");
        helloFeignClient.hello();
        log.info("hello java .......call end.......");
        return BaseResult.successResult(gitBuildInfo);
    }


    @Autowired
    private KafkaDefaultProducerClient producerClient;
    @RequestMapping(value = {"/sendKafkaMsg"}, method = {POST})
    public BaseResult sendKafkaMsg() {
        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());
        producerClient.sendMessage("phoenix_tbox_location",order);
        return BaseResult.successResult(gitBuildInfo);
    }





}
