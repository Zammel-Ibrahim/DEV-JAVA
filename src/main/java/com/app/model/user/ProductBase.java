package com.app.model.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductBase  {

  @ApiModelProperty(example = "demo", required = true)
  private Integer id = 0;

  @ApiModelProperty(example = "demo", required = true)
  private Integer etat = 0;

}

