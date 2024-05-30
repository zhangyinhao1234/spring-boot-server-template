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
package org.zhangyinhao.om.controller.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhangyinhao.base.result.BaseResult;
import org.zhangyinhao.base.result.PageData;
import org.zhangyinhao.om.model.bo.OrderItemBo;
import org.zhangyinhao.om.model.dto.CreateOrderReq;
import org.zhangyinhao.om.model.dto.QueryOrderReq;
import org.zhangyinhao.om.service.manage.IOrderCreateService;
import org.zhangyinhao.om.service.manage.IOrderQueryService;

import javax.validation.Valid;
import java.util.List;

/**
 * API 层 DTO和DO均可直接返回,不必纠结名字的定义
 * DTO和DO均可可作为入参使用
 * 对象字段一样反复转换太累,把握好这个度就行
 */
@Tag(name = "订单服务接口例子",description = "订单服务接口例子")
@RequestMapping("/order/example")
@RestController
@Slf4j
public class OrderController {


    @Autowired
    private IOrderQueryService orderQueryService;

    @Autowired
    private IOrderCreateService orderCreateService;


    @PostMapping(value = "/test/listAll")
    @Operation(summary="列出所有数据",description = "列出所有数据")
    public BaseResult<List<OrderItemBo>> listAll() {
        List<OrderItemBo> orderDTOS = orderQueryService.listAll();
        return BaseResult.successResult(orderDTOS);
    }

    @PostMapping(value = "/test/getByName")
    @Operation(summary="通过名字查询",description = "通过名字查询")
    public BaseResult<List<OrderItemBo>> getByName(@Valid QueryOrderReq queryOrderReq) {
        List<OrderItemBo> orderDTOS = orderQueryService.listByName(queryOrderReq.getUserName());
        return BaseResult.successResult(orderDTOS);
    }

    @PostMapping(value = "/test/getByNameAndOrderNo")
    @Operation(summary="通过名字和订单号查询",description = "通过名字和订单号查询")
    public BaseResult<List<OrderItemBo>> getByNameAndOrderNo(@Valid QueryOrderReq queryOrderReq) {
        List<OrderItemBo> orderDTOS = orderQueryService.listByNameAndOrderNo(queryOrderReq.getUserName(), queryOrderReq.getOrderNo());
        return BaseResult.successResult(orderDTOS);
    }
    @PostMapping(value = "/test/page")
    @Operation(summary="分页查询",description = "分页查询")
    public BaseResult<PageData<OrderItemBo>> page(@Valid QueryOrderReq queryOrderReq) {
        PageData<OrderItemBo> pageData = orderQueryService.queryPage(queryOrderReq);
        return BaseResult.successResult(pageData);
    }

    @PostMapping(value = "/test/save")
    @Operation(summary="创建订单",description = "创建Example")
    public BaseResult<OrderItemBo> createOrder(@Valid CreateOrderReq createOrderReq) {
        OrderItemBo orderDTO = orderCreateService.orderCreate(createOrderReq);
        return BaseResult.successResult(orderDTO);
    }
}
