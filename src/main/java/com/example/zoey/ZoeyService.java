package com.example.zoey;

import com.example.zoey.mapper.ZoeyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ZoeyService {

    final ZoeyMapper zoeyMapper;

    @Autowired
    public ZoeyService(ZoeyMapper zoeyMapper) {
        this.zoeyMapper = zoeyMapper;
    }

    public List<Map<String, Object>> getField(Map<String, String> params) {
        return zoeyMapper.findStoryById(params);
    }

}
