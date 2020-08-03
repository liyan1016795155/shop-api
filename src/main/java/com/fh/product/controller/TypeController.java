package com.fh.product.controller;

import com.fh.commons.ServerResponse;
import com.fh.product.service.type.TypeService;
import com.sun.org.apache.bcel.internal.generic.ReturnInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("type")
public class TypeController {
    @Autowired
    private TypeService typeService;


    @RequestMapping("queryTypeTree")
    public ServerResponse queryTypeTree(){
        List<Map<String, Object>> list =typeService.queryTypeTree();
        return ServerResponse.success(list) ;
    }

}
