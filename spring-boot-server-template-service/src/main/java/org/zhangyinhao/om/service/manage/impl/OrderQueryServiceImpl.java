package org.zhangyinhao.om.service.manage.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhangyinhao.base.result.PageData;
import org.zhangyinhao.om.model.bo.OrderItemBo;
import org.zhangyinhao.om.model.dto.QueryOrderReq;
import org.zhangyinhao.om.order.service.IOrderService;
import org.zhangyinhao.om.service.manage.IOrderQueryService;

import java.util.List;

@Service
@Slf4j
public class OrderQueryServiceImpl implements IOrderQueryService {
    @Autowired
    private IOrderService exampleService;

    @Override
    public List<OrderItemBo> listAll() {
        return exampleService.listAll();
    }

    @Override
    public List<OrderItemBo> listByName(String userName) {
        return exampleService.listByName(userName);
    }

    @Override
    public List<OrderItemBo> listByNameAndOrderNo(String userName, String orderNo) {
        return exampleService.listByNameAndOrderNo(userName, orderNo);
    }

    @Override
    public PageData<OrderItemBo> queryPage(QueryOrderReq queryOrderReq) {
        return exampleService.queryPage(queryOrderReq);
    }
}
