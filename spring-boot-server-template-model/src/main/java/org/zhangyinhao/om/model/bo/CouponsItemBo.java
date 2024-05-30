package org.zhangyinhao.om.model.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponsItemBo {
    /**
     * 优惠券ID
     */
    @Schema(description = "优惠券ID", example = "1234")
    private String couponsId;

    @Schema(description = "优惠券描述", example = "这是一张10元优惠券")
    private String couponsDescribe;
}
