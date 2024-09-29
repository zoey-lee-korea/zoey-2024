package com.example.zoey.mapper;

import com.example.zoey.ZoeyDBConnMapper;

import java.util.List;
import java.util.Map;

@ZoeyDBConnMapper
public interface ZoeyMapper {
	List<Map<String, Object>> findStoryById(Map<String, String> params);
}
