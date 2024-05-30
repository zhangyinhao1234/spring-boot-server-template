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
public class ProductItemBo {

    /**
     * 商品ID
     */
    @Schema(description = "商品ID", example = "1234")
    private String productId;

    /**
     * 商品描述
     */
    @Schema(description = "商品描述", example = "这是一个苹果")
    private String productDescribe;
}
