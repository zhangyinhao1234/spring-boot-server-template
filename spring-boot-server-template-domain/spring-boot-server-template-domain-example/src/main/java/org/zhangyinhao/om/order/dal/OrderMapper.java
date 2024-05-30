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
package org.zhangyinhao.om.order.dal;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * mysql demo
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    Order getById(@Param("id") Long id);

    List<Order> listByNameAndOrderNo(@Param("userName") String userName, @Param("orderNo") String orderNo);

    @Select("select * from t_voms_order_example where user_name=#{userName}")
    List<Order> listByName(@Param("userName") String userName);
}
