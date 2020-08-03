package com.fh.product.mapper;

import com.fh.commons.ServerResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TypeMpper {

    List<Map<String, Object>> queryTypeTree();
}
