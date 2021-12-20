package com.qks.makerSpace;

import com.qks.makerSpace.Enity.CustomXWPFDocument;
import com.qks.makerSpace.util.WordToNewWordUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.util.*;

import static com.qks.makerSpace.util.WordChangeUtils.searchAndReplace;

//import static com.qks.makerSpace.util.WordChangeUtils.searchAndReplace;


@SpringBootTest
class makerSpaceApplicationTests {

    @Test
    void contextLoads() throws Exception {

        Map<String,Object> map = new HashMap<>();

        map.put("${year}",String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+"年度");
        map.put("${teamName}","不懂队");
        map.put("${code}","123456-123");
//        map.put("${organizationCode}","1234567-2");
        map.put("${registerTime}","2020-12-6");
        map.put("${joinTime}","2021-12-6");
        map.put("${registerCapital}","2000 千元");
        map.put("${registerKind}","国有");
        map.put("${industryKind}","不知道");
        map.put("${field}","地球、空间与海洋");
        map.put("${graduatedEnterprise}","是");
        map.put("${graduatedTime}","2020-1-1");
        map.put("${highEnterprise}","asdfsadfa");

//        searchAndReplace(,map,1);

    }
}
