package com.qks.makerSpace.controller;

import com.qks.makerSpace.entity.request.SearchRequest;
import com.qks.makerSpace.entity.response.SearchResponse;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.SearchService;
import com.qks.makerSpace.util.MyResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api("搜索相关操作")
@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "condition", method = RequestMethod.POST)
    @ApiOperation(value = "根据条件筛选", response = SearchResponse.class)
    public Map<String, Object> search(@RequestBody @Validated SearchRequest searchRequest) throws ServiceException {
        return searchService.searchContent(searchRequest);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity validationBodyException(MethodArgumentNotValidException exception){
        BindingResult bindingResult = exception.getBindingResult();
        System.out.println(bindingResult);
        return ResponseEntity.ok(MyResponseUtil.getResultMap("格式不匹配",-1,"failure"));
    }
}