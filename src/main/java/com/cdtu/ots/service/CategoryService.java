package com.cdtu.ots.service;

import com.cdtu.ots.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Map<String, Object>> findAll(){
        List<Map<String, Object>> all = categoryMapper.findAll();
        return all;
    }
}
