/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zhangyinhao.om.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.zhangyinhao.base.result.BaseCode;
import org.zhangyinhao.base.result.BaseResult;
import org.zhangyinhao.base.exception.BaseBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zhangyinhao.base.result.ResultType;
import jakarta.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionResolver {

    @ExceptionHandler(value = ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResult validationExceptionHandler(ValidationException e) {
        return BaseResult.result(ResultType.ILLEGAL_ARGUMENTS.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = BaseBusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResult bizExceptionHandler(BaseBusinessException e) {
        return BaseResult.result(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.joining(","));
        return BaseResult.result(ResultType.ILLEGAL_ARGUMENTS.getCode(), message);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResult exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("系统发生异常,method = {}, path = {} ", req.getMethod(), req.getServletPath(), e);
        return BaseResult.result(ResultType.SYSTEM_ERROR.getCode(), ResultType.SYSTEM_ERROR.getDesc());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResult noHandlerFoundException(HttpServletRequest req, Exception e) {
        log.error("404异常 NoHandlerFoundException, method = {}, path = {} ", req.getMethod(), req.getServletPath(), e);
        return BaseResult.result(404, "未能找到资源");
    }
}
