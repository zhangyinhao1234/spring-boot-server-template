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
package org.zhangyinhao.om.model.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderBo implements Serializable {
    private static final long serialVersionUID = 2791135201933801590L;

    /**
     * 姓名
     */
    @Schema(description = "姓名", example = "张三")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 订单编号
     */
    @Schema(description = "订单编号", example = "1234")
    private String orderNo;

    @Schema(description = "优惠券")
    private CouponsItemBo coupons;

    @Schema(description = "商品")
    private ProductItemBo product;

}
