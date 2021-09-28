package com.cdtu.ots.service;

import com.cdtu.ots.entity.Indent;
import com.cdtu.ots.mapper.IndentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IndentService {
    @Autowired
    private IndentMapper indentMapper;



    public boolean insertIndent(Indent indent){
        boolean b = indentMapper.insertIndent(indent);
        return b;
    }

}
