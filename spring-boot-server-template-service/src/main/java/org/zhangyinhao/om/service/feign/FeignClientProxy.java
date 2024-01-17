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

package org.zhangyinhao.om.service.feign;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 对FeignClient进行增强可使用代理模式
 */
@Component
@Slf4j
public class FeignClientProxy {

    @Resource(name = "helloFeignClient")
    private HelloFeignClient helloFeignClient;

    @Primary
    @Bean
    public HelloFeignClient suppliersFeignProxyClient() {
        FeignClientInvocationHandler invocationHandler = new FeignClientInvocationHandler();
        HelloFeignClient proxyClient = (HelloFeignClient) invocationHandler.bind(helloFeignClient);
        return proxyClient;
    }


}
