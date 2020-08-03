package com.fh.product.service.type;

import com.fh.commons.ServerResponse;

import java.util.List;
import java.util.Map;

public interface TypeService {

    List<Map<String, Object>> queryTypeTree();
}
