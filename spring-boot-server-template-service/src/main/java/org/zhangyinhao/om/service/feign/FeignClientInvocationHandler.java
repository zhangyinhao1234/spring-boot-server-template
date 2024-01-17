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
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 对FeignClient进行增强可使用代理模式
 */
@Slf4j
class FeignClientInvocationHandler implements InvocationHandler {
    private Object obj;


    public FeignClientInvocationHandler() {
    }

    public Object bind(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String apiName = getApiName(method);
        try {
            addArgs(args);
            Object result = method.invoke(this.obj, args);
            printLog(result, apiName, args);
            return result;
        } catch (Exception e) {//可能超时，便于日志查询
            log.error("【{}】发生异常，发送的数据={}", apiName, args, e);
            throw e;
        }
    }

    private void addArgs(Object[] args) {

    }

    private void printLog(Object result, String apiName, Object[] args) {
        try {
            log.info("接口【{}】;发送的数据={};返回结果={}", apiName, args, result);
        } catch (Exception e) {
            log.warn("FeignClient代理打印日志异常;apiName={}", apiName, e);
        }
    }

    private String getApiName(Method method) {
        String apiName = "";
        try {
            Annotation annotation = method.getAnnotation(PostMapping.class);
            if (annotation != null) {
                apiName = ((PostMapping) annotation).name();
                if (StringUtils.isBlank(apiName)) {
                    apiName = Arrays.toString(((PostMapping) annotation).value());
                }
            }
        } catch (Exception e) {
            log.warn("FeignClient代理获取ApiName异常", e);
        }
        return apiName;
    }


}