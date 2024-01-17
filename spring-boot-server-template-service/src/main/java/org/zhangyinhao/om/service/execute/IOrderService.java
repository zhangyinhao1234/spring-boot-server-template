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
package org.zhangyinhao.om.service.execute;



import org.zhangyinhao.base.result.PageData;
import org.zhangyinhao.om.api.req.CreateOrderReq;
import org.zhangyinhao.om.api.req.QueryOrderReq;
import org.zhangyinhao.om.api.rsp.OrderDTO;

import java.util.List;

public interface IOrderService {
    OrderDTO getById(Long id);

    List<OrderDTO> listByName(String name);

    List<OrderDTO> listByNameAndOrderNo(String name, String orderNO);

    List<OrderDTO> listAll();

    PageData<OrderDTO> queryPage(QueryOrderReq queryOrderReq);

    OrderDTO create(CreateOrderReq createOrderReq);
}
