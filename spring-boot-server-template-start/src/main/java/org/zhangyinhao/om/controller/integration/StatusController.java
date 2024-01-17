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
package org.zhangyinhao.om.controller.integration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shenyu.client.springcloud.annotation.ShenyuSpringCloudClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhangyinhao.base.result.BaseResult;
import springfox.documentation.annotations.ApiIgnore;


@Slf4j
@RestController
@ApiIgnore
@ShenyuSpringCloudClient()
@RequestMapping("/integration/status")
public class StatusController {

    @PostMapping(value = "/ok")
    @ApiOperation(value = "API网关服务检测")
    BaseResult<String> ok(@RequestBody JSONObject req) {
        log.info("status ok JSONObject = {}", JSON.toJSONString(req));
        return BaseResult.successResult("server is ok !");
    }

}
