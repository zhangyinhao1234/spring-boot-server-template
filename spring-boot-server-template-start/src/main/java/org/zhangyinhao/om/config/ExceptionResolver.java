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
package org.zhangyinhao.om.config;
import org.zhangyinhao.base.result.BaseCode;
import org.zhangyinhao.base.result.BaseResult;
import org.zhangyinhao.base.exception.BaseBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zhangyinhao.base.result.ResultType;

import javax.validation.ValidationException;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionResolver {

    @ExceptionHandler(value = ValidationException.class)
    public BaseResult validationExceptionHandler(ValidationException e) {
        return BaseResult.result(ResultType.ILLEGAL_ARGUMENTS.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = BaseBusinessException.class)
    public BaseResult bizExceptionHandler(BaseBusinessException e) {
        return BaseResult.result(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining(","));
        return BaseResult.result(ResultType.ILLEGAL_ARGUMENTS.getCode(),message);
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResult exceptionHandler(Exception e) {
        log.error("系统发生异常", e);
        return BaseResult.result(BaseCode.ERROR.getCode(),e.getMessage());
    }
}
