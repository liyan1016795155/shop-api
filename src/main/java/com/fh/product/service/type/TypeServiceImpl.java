package com.fh.product.service.type;

import com.fh.commons.ServerResponse;
import com.fh.product.mapper.TypeMpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMpper typeMpper;


    @Override
    public List<Map<String, Object>> queryTypeTree() {
        List<Map<String, Object>> allList=typeMpper.queryTypeTree();
        List<Map<String, Object>> parenList=new ArrayList<Map<String,Object>>();
        for (Map<String, Object> map : allList) {
            if (map.get("pid").equals(0)){
                parenList.add(map);
            }
        }
        selectChildren(parenList,allList);
        return parenList;
    }

    public void selectChildren(List<Map<String, Object>> parenList,List<Map<String, Object>> allList){

        for (Map<String, Object> pmap : parenList) {
            List<Map<String, Object>> childrenList=new ArrayList<Map<String,Object>>();
            for (Map<String, Object> amap : allList) {
                if(pmap.get("id").equals(amap.get("pid"))){
                    childrenList.add(amap);
                }

            }

            if (childrenList !=null && childrenList.size()>0){
                pmap.put("children",childrenList);
                selectChildren(childrenList,allList);
            }

        }


    }
}
