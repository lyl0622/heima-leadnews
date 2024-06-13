package com.itheima.model.wemedia.dtos;

import com.itheima.model.common.dtos.PageRequestDto;
import lombok.Data;

@Data
public class WmMaterialDto extends PageRequestDto {

    /*
    1.查询收藏的 0.未收藏
     */
    Short isCollection;
}

