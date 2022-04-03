package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.AdminDao;
import com.qks.makerSpace.entity.Temp.EmploymentData;
import com.qks.makerSpace.entity.Temp.FormAwardsData;
import com.qks.makerSpace.entity.Temp.HighEnterpriseData;
import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.request.AdminSpaceApplyingReq;
import com.qks.makerSpace.entity.request.AdminTechnologyApplyingReq;
import com.qks.makerSpace.entity.request.BriefFormReq;
import com.qks.makerSpace.entity.response.*;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.AdminService;
import com.qks.makerSpace.service.FileService;
import com.qks.makerSpace.util.ChangeUtils;
import com.qks.makerSpace.util.FormParserUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import com.qks.makerSpace.util.WordChangeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {

}