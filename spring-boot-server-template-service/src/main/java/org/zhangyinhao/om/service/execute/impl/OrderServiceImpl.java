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

package org.zhangyinhao.om.service.execute.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhangyinhao.base.result.PageData;
import org.zhangyinhao.om.api.req.CreateOrderReq;
import org.zhangyinhao.om.api.req.QueryOrderReq;
import org.zhangyinhao.om.api.rsp.OrderDTO;
import org.zhangyinhao.om.dal.order.Order;
import org.zhangyinhao.om.dal.order.OrderMapper;
import org.zhangyinhao.om.service.execute.IOrderService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper exampleMapper;

    @Override
    public OrderDTO getById(Long id) {
        Order order = exampleMapper.getById(id);
        return parse(order);
    }


    @Override
    public OrderDTO create(CreateOrderReq createOrderReq) {
        Order order = Order.builder().createTime(new Date()).orderNo(createOrderReq.getOrderNo())
                .userName(createOrderReq.getUserName()).updateTime(new Date()).build();
        exampleMapper.insert(order);
        return parse(order);
    }

    @Override
    public List<OrderDTO> listByName(String name) {
        List<Order> orders = exampleMapper.listByName(name);
        return parse(orders);
    }

    @Override
    public List<OrderDTO> listByNameAndOrderNo(String name, String orderNO) {
        List<Order> orders = exampleMapper.listByNameAndOrderNo(name, orderNO);
        return parse(orders);
    }

    @Override
    public List<OrderDTO> listAll() {
        List<Order> orders = exampleMapper.selectList(null);
        return parse(orders);
    }


    @Override
    public PageData<OrderDTO> queryPage(QueryOrderReq queryOrderReq) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(queryOrderReq.getUserName())) {
            queryWrapper.eq("user_name", queryOrderReq.getUserName());
        }
        if (StringUtils.isNotBlank(queryOrderReq.getOrderNo())) {
            queryWrapper.eq("order_no", queryOrderReq.getOrderNo());
        }
        if (queryOrderReq.getBeginTime() != null) {
            queryWrapper.ge("create_time", queryOrderReq.getBeginTime());
        }
        if (queryOrderReq.getEndTime() != null) {
            queryWrapper.lt("create_time", queryOrderReq.getEndTime());
        }

        PageHelper.startPage(queryOrderReq.getCurrentPage(), queryOrderReq.getPageSize());
        List<Order> orders = exampleMapper.selectList(queryWrapper);
        PageInfo<Order> pageinfo = new PageInfo<>(orders);
        List<OrderDTO> items = parse(pageinfo.getList());
        return PageData.create(pageinfo.getTotal(), pageinfo.getPageNum(), pageinfo.getPageSize(), items);
    }

    private OrderDTO parse(Order order) {
        return OrderDTO.builder().id(order.getId()).orderNo(order.getOrderNo())
                .userName(order.getUserName()).createTime(order.getCreateTime()).build();
    }

    private List<OrderDTO> parse(List<Order> orders) {
        return orders.stream().map(e -> parse(e)).collect(Collectors.toList());
    }

}
