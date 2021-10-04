package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.PostDao;
import com.qks.makerSpace.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final PostDao postDao;

    public PostServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }


}
