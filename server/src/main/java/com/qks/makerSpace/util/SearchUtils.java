package com.qks.makerSpace.util;

import com.qks.makerSpace.entity.request.SearchRequest;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SearchUtils {

    private static String selectContent(String content) {
        return "%"+content+"%";
    }

    private static String selectTime(String time) {
        String[] realTime = time.split("\\s|:|-");
        return realTime[0]+"-"+realTime[1]+"-"+realTime[2]+":"+realTime[3]+":"+realTime[4]+":"+realTime[5];
    }

    public static SearchRequest dealSearch(SearchRequest searchRequest) {
        searchRequest.setContent(selectContent(searchRequest.getContent()));
        if (searchRequest.getBeginTime().isEmpty())
            searchRequest.setBeginTime("2020-01-01:00:00:00");
        else searchRequest.setBeginTime(searchRequest.getBeginTime()+":00:00:00");

        if (searchRequest.getEndTime().isEmpty())
            searchRequest.setEndTime(new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date()));
        else searchRequest.setEndTime(searchRequest.getEndTime()+":23:59:59");

        return searchRequest;
    }
}