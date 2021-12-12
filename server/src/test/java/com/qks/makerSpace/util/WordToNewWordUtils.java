package com.qks.makerSpace.util;


import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class WordToNewWordUtils {

    /**
     * 根据模板生成word文档
     * @param inputUrl 模板路径
     * @param textMap 需要替换的文本内容
     * @return
     */
    public static XWPFDocument changWord(String inputUrl, Map<String, Object> textMap) {

        XWPFDocument document = null;
        try {
            //获取docx解析对象
            document = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));

            //解析替换文本段落对象
            WordToNewWordUtils.changeText(document, textMap);

            //解析替换表格对象
            WordToNewWordUtils.changeTable(document, textMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 替换段落文本
     * @param document docx解析对象
     * @param textMap 需要替换的信息集合
     */
    public static void changeText(XWPFDocument document, Map<String, Object> textMap){
        //获取段落集合
        List<XWPFParagraph> paragraphs = document.getParagraphs();

        for (XWPFParagraph paragraph : paragraphs) {
            //判断此段落时候需要进行替换
            String text = paragraph.getText();
            System.out.println(text);
            if(checkText(text)){
                System.out.println("替换段落");
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    System.out.println(run);
                    //替换模板原来位置
                    Object ob = changeValue(run.toString(), textMap);
                    if (ob instanceof String){
                        run.setText((String)ob,0);
                    }
                }
            }
        }
    }

    /**
     * 替换表格对象方法
     * @param document docx解析对象
     * @param textMap 需要替换的信息集合
     */
    public static void changeTable(XWPFDocument document, Map<String, Object> textMap){
        //获取表格对象集合
        List<XWPFTable> tables = document.getTables();

        //循环所有需要进行替换的文本，进行替换
        for (int i = 0; i < tables.size(); i++) {
            XWPFTable table = tables.get(i);
            if(checkText(table.getText())){
                List<XWPFTableRow> rows = table.getRows();
                //遍历表格,并替换模板
                eachTable(document,rows,textMap);
            }
        }
    }

    /**
     * 遍历表格
     * @param rows 表格行对象
     * @param textMap 需要替换的信息集合
     */
    public static void eachTable(XWPFDocument document,List<XWPFTableRow> rows ,Map<String, Object> textMap){
        for (XWPFTableRow row : rows) {
            List<XWPFTableCell> cells = row.getTableCells();
            for (XWPFTableCell cell : cells) {
                //判断单元格是否需要替换
                if(checkText(cell.getText())){
                    List<XWPFParagraph> paragraphs = cell.getParagraphs();
                    for (XWPFParagraph paragraph : paragraphs) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        System.out.println(runs);
                        for (XWPFRun run : runs) {
                            Object ob = changeValue(run.toString(), textMap);
                            run.setText((String)ob,0);
                        }
                    }
                }
            }
        }
    }

    /**
     * 判断文本中时候包含$
     * @param text 文本
     * @return 包含返回true,不包含返回false
     */
    public static boolean checkText(String text){
        boolean check  =  false;
        if(text.indexOf("$")!= -1){
            check = true;
        }
        return check;
    }

    /**
     * 匹配传入信息集合与模板
     * @param value 模板需要替换的区域
     * @param textMap 传入信息集合
     * @return 模板需要替换区域信息集合对应值
     */
    public static Object changeValue(String value, Map<String, Object> textMap){
        Set<Map.Entry<String, Object>> textSets = textMap.entrySet();
        Object valu = "";
        for (Map.Entry<String, Object> textSet : textSets) {
            //匹配模板与替换值 格式${key}
            String key = textSet.getKey();
            if(value.indexOf(key)!= -1){
                valu = textSet.getValue();
            }
        }
        return valu;
    }

    /**
     * 将输入流中的数据写入字节数组
     * @param in
     * @return
     */
    public static byte[] inputStream2ByteArray(InputStream in, boolean isClose){
        byte[] byteArray = null;
        try {
            int total = in.available();
            byteArray = new byte[total];
            in.read(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(isClose){
                try {
                    in.close();
                } catch (Exception e2) {
                    System.out.println("关闭流失败");
                }
            }
        }
        return byteArray;
    }

    /**
     * 合并行
     * @param table
     * @param col 需要合并的列
     * @param fromRow 开始行
     * @param toRow 结束行
     */
    public static void mergeCellVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for(int rowIndex = fromRow; rowIndex <= toRow; rowIndex++){
            CTVMerge vmerge = CTVMerge.Factory.newInstance();
            if(rowIndex == fromRow){
                vmerge.setVal(STMerge.RESTART);
            } else {
                vmerge.setVal(STMerge.CONTINUE);
            }
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            CTTcPr tcPr = cell.getCTTc().getTcPr();
            if (tcPr != null) {
                tcPr.setVMerge(vmerge);
            } else {
                tcPr = CTTcPr.Factory.newInstance();
                tcPr.setVMerge(vmerge);
                cell.getCTTc().setTcPr(tcPr);
            }
        }
    }


    /**
     * 获取需要合并单元格的下标
     * @return
     */
    public static List<Integer[]> startEnd(List<String[]> daList){
        List<Integer[]> indexList = new ArrayList<Integer[]>();

        List<String> list = new ArrayList<String>();
        for (int i=0;i<daList.size();i++){
            list.add(daList.get(i)[0]);
        }
        Map<Object, Integer> tm = new HashMap<Object, Integer>();
        for (int i=0;i<daList.size();i++){
            if (!tm.containsKey(daList.get(i)[0])) {
                tm.put(daList.get(i)[0], 1);
            } else {
                int count = tm.get(daList.get(i)[0]) + 1;
                tm.put(daList.get(i)[0], count);
            }
        }
        for (Map.Entry<Object, Integer> entry : tm.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            if (list.indexOf(key) != (-1)){
                Integer[] index = new Integer[2];
                index[0] = list.indexOf(key);
                index[1] = list.lastIndexOf(key);
                indexList.add(index);
            }
        }
        return indexList;
    }
}