package com.sciatta.dev.java.database.elasticsearch.resthighlevelclient.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.sciatta.dev.java.database.elasticsearch.resthighlevelclient.module.User;
import com.sciatta.dev.java.database.elasticsearch.resthighlevelclient.service.AbstractUserService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiaoyu on 2021/9/9<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserServiceImpl
 */
@Service
@Slf4j
public class UserServiceImpl extends AbstractUserService {
    public void insertBach(String index, List<User> list) {
        if (list.isEmpty()) {
            log.warn("bach insert index but list is empty ...");
            return;
        }
        list.forEach((user) -> {
            super.insertRequest(index, user.getId().toString(), user);
        });
    }
    
    public UserServiceImpl() {
    }
    
    public List<User> searchList(String index) {
        SearchResponse searchResponse = search(index);
        SearchHit[] hits = searchResponse.getHits().getHits();
        List<User> userList = new ArrayList<>();
        Arrays.stream(hits).forEach(hit -> {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            User user = BeanUtil.mapToBean(sourceAsMap, User.class, true);
            userList.add(user);
        });
        return userList;
    }
    
    protected SearchResponse search(String index) {
        
        SearchRequest searchRequest = new SearchRequest(index);
        
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        
        // bool符合查询
        // BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder()
        //        .filter(QueryBuilders.matchQuery("name", "盖伦"))
        //        .must(QueryBuilders.matchQuery("desc", "部落"))
        //        .should(QueryBuilders.matchQuery("realName", "光辉"));
        
        // 分页
        // searchSourceBuilder.from(1).size(2);
        
        // 排序
        // searchSourceBuilder.sort("", SortOrder.DESC);
        
        // 误拼写时的fuzzy模糊搜索方法 2表示允许的误差字符数
        // QueryBuilders.fuzzyQuery("title", "ceshi").fuzziness(Fuzziness.build("2"));
        
        searchRequest.source(searchSourceBuilder);
        System.out.println(searchSourceBuilder.toString());
        System.out.println(JSONUtil.parseObj(searchSourceBuilder.toString()).toStringPretty());
        
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }
}
