package com.sciatta.dev.java.database.elasticsearch.resthighlevelclient.service;

/**
 * Created by yangxiaoyu on 2021/9/9<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserService
 */
public interface UserService {
    /**
     * 创建索引库
     */
    void createIndexRequest(String index);
    
    /**
     * 删除索引库
     */
    void deleteIndexRequest(String index);
    
    /**
     * 更新索引文档
     */
    void updateRequest(String index, String id, Object object);
    
    /**
     * 新增索引文档
     */
    void insertRequest(String index, String id, Object object);
    
    /**
     * 删除索引文档
     */
    void deleteRequest(String index, String id);
}
