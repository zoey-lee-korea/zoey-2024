package com.example.zoey.mapper;

import java.util.List;
import java.util.Map;

@ZoeyDBConnMapper
public interface ZoeyMapper {
	List<Map<String, Object>> findStoryById(Map<String, String> params);
}
