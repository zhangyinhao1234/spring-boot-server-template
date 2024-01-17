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

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.zhangyinhao.base.result.BaseResult;
import org.zhangyinhao.base.result.PageData;
import org.zhangyinhao.om.api.OrderFacade;
import org.zhangyinhao.om.api.req.CreateOrderReq;
import org.zhangyinhao.om.api.req.QueryOrderReq;
import org.zhangyinhao.om.api.rsp.OrderDTO;
import org.zhangyinhao.om.service.execute.IOrderService;
import org.zhangyinhao.om.service.manage.IOrderCreateService;

import javax.validation.Valid;
import java.util.List;


@RestController
@Slf4j
public class OrderController implements OrderFacade {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderCreateService orderCreateService;


    @Override
    public BaseResult<List<OrderDTO>> listAll() {
        List<OrderDTO> orderDTOS = orderService.listAll();
        return BaseResult.successResult(orderDTOS);
    }

    @Override
    public BaseResult<List<OrderDTO>> getByName(@Valid QueryOrderReq queryOrderReq) {
        List<OrderDTO> orderDTOS = orderService.listByName(queryOrderReq.getUserName());
        return BaseResult.successResult(orderDTOS);
    }

    @Override
    public BaseResult<List<OrderDTO>> getByNameAndOrderNo(@Valid QueryOrderReq queryOrderReq) {
        List<OrderDTO> orderDTOS = orderService.listByNameAndOrderNo(queryOrderReq.getUserName(), queryOrderReq.getOrderNo());
        return BaseResult.successResult(orderDTOS);
    }

    @Override
    public BaseResult<PageData<OrderDTO>> page(@Valid QueryOrderReq queryOrderReq) {
        PageData<OrderDTO> pageData = orderService.queryPage(queryOrderReq);
        return BaseResult.successResult(pageData);
    }

    @Override
    public BaseResult<OrderDTO> createOrder(@Valid CreateOrderReq createOrderReq) {
        OrderDTO orderDTO = orderCreateService.orderCreate(createOrderReq);
        return BaseResult.successResult(orderDTO);
    }
}
