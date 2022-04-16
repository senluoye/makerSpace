package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.SearchDao;
import com.qks.makerSpace.entity.request.SearchRequest;
import com.qks.makerSpace.entity.response.SearchResponse;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.SearchService;
import com.qks.makerSpace.util.MyResponseUtil;
import com.qks.makerSpace.util.SearchUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class SearchServiceImpl implements SearchService {

    private final SearchDao searchDao;

    public SearchServiceImpl(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    @Override
    public Map<String, Object> searchContent(SearchRequest searchRequest) throws ServiceException {
        List<SearchResponse> searchResponseList = new ArrayList<>();
        SearchRequest dealRequest = SearchUtils.dealSearch(searchRequest);
        System.out.println(dealRequest);

        List<SearchResponse> searchResponseListA =  searchDao.selectOld(dealRequest);
        for (SearchResponse searchResponse : searchResponseListA) {
            searchResponse.setDescribe("3");
            searchResponse.setAdministratorAudit(searchDao.selectAdministratorAudit(searchResponse.getCreditCode(),searchResponse.getSubmitTime()));
            searchResponse.setLeadershipAudit(searchDao.selectLeadershipAudit(searchResponse.getCreditCode(),searchResponse.getSubmitTime()));
        }
        List<SearchResponse> searchResponseListB =  searchDao.selectNew(searchRequest);
        for (SearchResponse searchResponse : searchResponseListB) {
            searchResponse.setDescribe("2");
            searchResponse.setAdministratorAudit(searchDao.selectAdministratorAudit(searchResponse.getCreditCode(),searchResponse.getSubmitTime()));
            searchResponse.setLeadershipAudit(searchDao.selectLeadershipAudit(searchResponse.getCreditCode(),searchResponse.getSubmitTime()));
        }

        searchResponseList.addAll(searchResponseListA);
        searchResponseList.addAll(searchResponseListB);

        if (searchResponseList.size() == 0)
            throw new ServiceException("未查到相关结果");

        return MyResponseUtil.getResultMap(searchResponseList,0,"success");
    }
}