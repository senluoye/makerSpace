package com.qks.makerSpace.service;

import com.qks.makerSpace.entity.request.SearchRequest;
import com.qks.makerSpace.exception.ServiceException;

import java.util.Map;

public interface SearchService {

    Map<String, Object> searchContent(SearchRequest searchRequest) throws ServiceException;
}
