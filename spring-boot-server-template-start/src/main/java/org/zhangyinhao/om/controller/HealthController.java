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
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.GitProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhangyinhao.base.result.BaseResult;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@Slf4j
@Api(value = "healthCheck", tags = "healthCheck")
public class HealthController {
    @Autowired(required = false)
    private GitProperties git;

    private Map<String, String> gitBuildInfo = new HashMap<>();
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
}
