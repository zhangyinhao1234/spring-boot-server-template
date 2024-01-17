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
package org.zhangyinhao.om.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zhangyinhao.base.result.BaseResult;
import org.zhangyinhao.base.result.PageData;
import org.zhangyinhao.om.api.req.CreateOrderReq;
import org.zhangyinhao.om.api.req.QueryOrderReq;
import org.zhangyinhao.om.api.rsp.OrderDTO;

import javax.validation.Valid;
import java.util.List;


@Api(value = " OrderFacade", tags = "订单服务接口例子")
@RequestMapping("/order/example")
public interface OrderFacade {

    @PostMapping(value = "/test/listAll")
    @ApiOperation(value = "列出所有数据")
    BaseResult<List<OrderDTO>> listAll();

    @PostMapping(value = "/test/getByName")
    @ApiOperation(value = "通过名字查询")
    BaseResult<List<OrderDTO>> getByName(@RequestBody @Valid QueryOrderReq queryOrderReq);

    @PostMapping(value = "/test/getByNameAndOrderNo")
    @ApiOperation(value = "通过名字和订单号查询")
    BaseResult<List<OrderDTO>> getByNameAndOrderNo(@RequestBody @Valid QueryOrderReq queryOrderReq);

    @PostMapping(value = "/test/page")
    @ApiOperation(value = "分页查询")
    BaseResult<PageData<OrderDTO>> page(@RequestBody @Valid QueryOrderReq queryOrderReq);

    @PostMapping(value = "/test/save")
    @ApiOperation(value = "创建Example")
    BaseResult<OrderDTO> createOrder(@RequestBody @Valid CreateOrderReq createOrderReq);


}
