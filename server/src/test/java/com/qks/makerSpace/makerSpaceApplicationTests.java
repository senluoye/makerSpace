package com.qks.makerSpace;

import com.qks.makerSpace.Enity.CustomXWPFDocument;
import com.qks.makerSpace.util.WordToNewWordUtils;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.util.*;

import static com.qks.makerSpace.util.WordChangeUtils.searchAndReplace;

//import static com.qks.makerSpace.util.WordChangeUtils.searchAndReplace;


@SpringBootTest
class makerSpaceApplicationTests {


//    public static void searchAndReplace(String srcPath, String destPath,Map<String, Object> map) {
//        try {
//            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath));
//            /**
//             * 替换段落中的指定文字
//             */
//            Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
//            String text;
//            Set<String> set;
//            XWPFParagraph paragraph;
//            List<XWPFRun> run;
//            String key;
//            while (itPara.hasNext()) {
//                paragraph = itPara.next();
//                set = map.keySet();
//                Iterator<String> iterator = set.iterator();
//                while (iterator.hasNext()) {
//                    key = iterator.next();
//                    run = paragraph.getRuns();
//                    for (int i = 0, runSie = run.size(); i < runSie; i++) {
//                        text = run.get(i).getText(run.get(i).getTextPosition());
//                        if (text != null && text.equals(key)) {
//                            run.get(i).setText(String.valueOf(map.get(key)), 0);
//                        }
//                    }
//                }
//            }
//
//            /**
//             * 替换表格中的指定文字
//             */
//            Iterator<XWPFTable> itTable = document.getTablesIterator();
//            while (itTable.hasNext()) {
//                XWPFTable table = (XWPFTable) itTable.next();
//                int count = table.getNumberOfRows();
//                for (int i = 0; i < count; i++) {
//                    XWPFTableRow row = table.getRow(i);
//                    List<XWPFTableCell> cells = row.getTableCells();
//                    for (XWPFTableCell cell : cells) {
//                        for (Map.Entry<String, Object> e : map.entrySet()) {
//                            if (cell.getText().equals(e.getKey())) {
//                                cell.removeParagraph(0);
//                                cell.setText((String) e.getValue());
//                            }
//                        }
//                    }
//                }
//            }
//            FileOutputStream outStream = null;
//            outStream = new FileOutputStream(destPath);
//            document.write(outStream);
//            outStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test
//    void contextLoads() throws Exception {
//
//        Map<String,Object> map = new HashMap<>();
//
//        map.put("${year}",String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+"年度");
//        map.put("${teamName}","不懂队");
//        map.put("${code}","123456-123");
////        map.put("${organizationCode}","1234567-2");
//        map.put("${registerTime}","2020-12-6");
//        map.put("${joinTime}","2021-12-6");
//        map.put("${registerCapital}","2000 千元");
//        map.put("${registerKind}","国有");
//        map.put("${industryKind}","不知道");
//        map.put("${field}","地球、空间与海洋");
//        map.put("${graduatedEnterprise}","是");
//        map.put("${graduatedTime}","2020-1-1");
//        map.put("${highEnterprise}","asdfsadfa");
//        String srcPath = "D:\\output_document.docx";
//        String destPath = "D:\\吼吼.doc";
//
//        searchAndReplace(srcPath,destPath,map);
//
//    }
}
