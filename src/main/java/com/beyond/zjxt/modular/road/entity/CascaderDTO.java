package com.beyond.zjxt.modular.road.entity;

import lombok.Data;

import java.util.List;
@Data
public class CascaderDTO {
  public  long value;
  public  String label;
  public  List<CascaderDTO> children;



}
