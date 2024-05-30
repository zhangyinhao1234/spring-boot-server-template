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
package org.zhangyinhao.om.service.manage;


import org.zhangyinhao.base.result.PageData;
import org.zhangyinhao.om.model.bo.OrderItemBo;
import org.zhangyinhao.om.model.dto.QueryOrderReq;

import java.util.List;

public interface IOrderQueryService {
    List<OrderItemBo> listAll();

    List<OrderItemBo> listByName(String userName);

    List<OrderItemBo> listByNameAndOrderNo(String userName, String orderNo);

    PageData<OrderItemBo> queryPage(QueryOrderReq queryOrderReq);


}
