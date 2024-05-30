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
package org.zhangyinhao.om.service.manage.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhangyinhao.om.model.bo.CreateOrderBo;
import org.zhangyinhao.om.model.bo.OrderItemBo;
import org.zhangyinhao.om.model.dto.CreateOrderReq;
import org.zhangyinhao.om.order.service.IOrderService;
import org.zhangyinhao.om.service.manage.IOrderCreateService;

@Service
public class OrderCreateServiceImpl implements IOrderCreateService {

    @Autowired
    private IOrderService exampleService;


    @Override
    @Transactional
    public OrderItemBo orderCreate(CreateOrderReq createOrderReq) {
        //服务编排 可能分别调用A,B,C领域的服务

        //调用了其他的方法做了一些业务上的检查

        //扣减优惠券、商品,返回优惠券商品信息

        //最终执行了创建
        CreateOrderBo createOrderBo = new CreateOrderBo();
        //基于返回的信息组合 CreateOrderBo
        return exampleService.create(createOrderBo);
    }
}
