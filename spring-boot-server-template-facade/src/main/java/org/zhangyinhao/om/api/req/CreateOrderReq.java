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
package org.zhangyinhao.om.api.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderReq implements Serializable {
    private static final long serialVersionUID = 2791135201933801590L;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", example = "张三")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号", example = "1234")
    private String orderNo;


}
