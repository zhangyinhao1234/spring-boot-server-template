/**
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
import org.zhangyinhao.base.params.QueryPageParam;

import javax.validation.constraints.NotBlank;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel
public class QueryOrderReq extends QueryPageParam {

    private static final long serialVersionUID = 2791135201933801590L;

    @NotBlank(message = "userName 不能为空")
    @ApiModelProperty(name = "用户名",example = "张三")
    private String userName;

    @ApiModelProperty(name = "开始时间",example = "13位时间戳")
    private Date beginTime;

    @ApiModelProperty(name = "结束时间",example = "13位时间戳")
    private Date endTime;

    @ApiModelProperty(name = "订单编号",example = "1234567")
    private String orderNo;

}
