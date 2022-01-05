package com.qks.makerSpace.util;

import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.*;

public class WordChangeUtils {

    /**
     * 根据模板生成新word文档 （一个表格模板,多行数据循环，混合表格外文本数据）
     */
    /**
     *	表格+段落
     * @param outStream 模板路径
     * @param map	表格外数据
     * @param list	表格数据
     */
    public static void expor(OutputStream outStream,Map<String,Object> map,List<Map<String,Object>> list){
        try {
            // 获取docx解析对象
            InputStream inputStream = new ClassPathResource("template/output.docx").getInputStream();
            XWPFDocument document = new XWPFDocument(inputStream);
            // 表格外替换
            if (map != null) {
                replaceInAllParagraphs(document.getParagraphs(), packMap(map));
            }
            // 表格内替换
            List<XWPFTable> tables = document.getTables();
            XWPFTable table = tables.get(0);
            System.out.println(checkText(table.getText()));
            if (checkText(table.getText())) {
                List<XWPFTableRow> templateTableRows = table.getRows();
                int startRowsIndex = 0;// 标签行indexs
                int endRowIndex = 0;// 循环结束行标签索引
                for (int i = 0, size = templateTableRows.size(); i < size; i++) {
                    String rowText = templateTableRows.get(i).getCell(0).getText();// 获取到表格行的第一个单元格
                    if (rowText.indexOf("##{foreachRows}##") > -1) {
                        startRowsIndex = i;
                        continue;
                    }
                    if (rowText.indexOf("##{endForeachRows}##") > -1) {
                        endRowIndex = i;
                        break;
                    }
                }
                // 获取到模板行
                startRowsIndex = startRowsIndex + 1;
                // 复制模板行和替换数据
                replaceAndCopyRows(startRowsIndex, endRowIndex, table, list,true);
                if (map != null) {
                    // 替换表格中段落数据
                    replaceInTable(table, packMap(map));
                }
            }
            document.write(outStream);
            outStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 替换一个表格中的所有行
     * @param xwpfTable 一个表格
     * @param params 所有替换数据集合
     */
    private static void replaceInTable(XWPFTable xwpfTable,Map<String, Object> params) {
        List<XWPFTableRow> rows = xwpfTable.getRows();
        replaceInRows(rows, params);
    }
    /**
     * 替换表格中的一行
     * @param rows 所有行
     * @param params 所有替换数据集合
     */
    private static void replaceInRows(List<XWPFTableRow> rows,Map<String, Object> params) {
        for (int i = 0; i < rows.size(); i++) {
            XWPFTableRow row = rows.get(i);
            replaceInCells(row.getTableCells(), params);
        }
    }
    /**
     * 在指定行位置开始替换数据
     * @param startIndex 开始索引
     * @param endIndex 结束索引
     * @param table 模板表格
     * @param tableList 需要替换的数据集合
     */
    private static Integer replaceAndCopyRows(int startIndex, int endIndex,XWPFTable table, List<Map<String, Object>> tableList,boolean endFlag) {
        tableList = packListMap(tableList);
        List<XWPFTableRow> templateTableRows = table.getRows();
        XWPFTableRow tempRow = templateTableRows.get(startIndex);
        int rsIndex = 0;
        int exitTempRow = endIndex - startIndex;
        int removeEndIndex = endIndex;
        System.out.println(exitTempRow);
        // 模板行数 < 需替换的集合数量， 复制相差的模板行数
        if (exitTempRow < tableList.size()) {
            int moreRow = tableList.size() - exitTempRow;
            rsIndex = endIndex + moreRow;
            for (int m = 0; m < moreRow; m++) {
                table.insertNewTableRow(endIndex + m);
                copyTableRow(tempRow, templateTableRows.get(endIndex + m));// 复制模板行
            }
            if (endFlag) {
                table.removeRow(endIndex + moreRow);
            }
        }
        // 模板行数 > 需要替换的集合数据，删除多余的模板行数
        if (exitTempRow > tableList.size()) {
            int deleteRow = exitTempRow - tableList.size();
            rsIndex = endIndex - deleteRow;
            if (endFlag) {
                for (int m = 0; m <= deleteRow; m++) {
                    table.removeRow(endIndex - m);
                }
            } else {
                for (int m = 1; m <= deleteRow; m++) {
                    table.removeRow(endIndex - m);
                }
            }
        }
        // 在模板的基础上循环替换为数据
        for (int j = 0; j < tableList.size(); j++) {
            XWPFTableRow newCreateRow = templateTableRows.get(j + startIndex);
            replaceTableRow(newCreateRow, tableList.get(j));// 处理标签替换
        }
        table.removeRow(startIndex - 1);// 移除多出来的标签行
//        table.removeRow(endIndex - 1);
        return rsIndex;
    }
    /**
     * 替换一行
     */
    private static void replaceTableRow(XWPFTableRow row,Map<String, Object> textMap) {
        List<XWPFTableCell> cells = row.getTableCells();
        replaceInCells(cells, packMap(textMap));
    }
    /**
     * 替换一行中所有的单元格
     * @param xwpfTableCellList 所有段落
     * @param params 所有替换数据集合
     */
    private static void replaceInCells(List<XWPFTableCell> xwpfTableCellList, Map<String, Object> params) {
        for (XWPFTableCell cell : xwpfTableCellList) {
            replaceInCell(cell, params);
        }
    }
    /**
     * 替换表格中每一行中的每一个单元格中的所有段落
     * @param cell 一个单元格
     * @param params 所有替换数据集合
     */
    private static void replaceInCell(XWPFTableCell cell,Map<String, Object> params) {
        List<XWPFParagraph> cellParagraphs = cell.getParagraphs();
        replaceInAllParagraphs(cellParagraphs, params);
    }
    /**
     * 复制一行
     */
    private static void copyTableRow(XWPFTableRow source, XWPFTableRow target) {
        // 复制样式
        if (source.getCtRow() != null) {
            target.getCtRow().setTrPr(source.getCtRow().getTrPr());
        }
        // 复制单元格
        for (int i = 0; i < source.getTableCells().size(); i++) {
            XWPFTableCell cell1 = target.getCell(i);
            XWPFTableCell cell2 = source.getCell(i);
            if (cell1 == null) {
                cell1 = target.addNewTableCell();
            }
            copyTableCell(cell2, cell1);
        }
    }
    /**
     * 复制单元格，从source到target
     */
    private static void copyTableCell(XWPFTableCell source, XWPFTableCell target) {
        // 列属性
        if (source.getCTTc() != null) {
            target.getCTTc().setTcPr(source.getCTTc().getTcPr());
        }
        // 删除段落
        for (int pos = 0; pos < target.getParagraphs().size(); pos++) {
            target.removeParagraph(pos);
        }
        // 添加段落
        for (XWPFParagraph sp : source.getParagraphs()) {
            XWPFParagraph targetP = target.addParagraph();
            copyParagraph(sp, targetP);
        }
    }
    /**
     * 复制段落，从source到target
     */
    private static void copyParagraph(XWPFParagraph source, XWPFParagraph target) {
        // 设置段落样式
        target.getCTP().setPPr(source.getCTP().getPPr());
        // 移除所有的run
        for (int pos = target.getRuns().size() - 1; pos >= 0; pos--) {
            target.removeRun(pos);
        }
        // copy 新的run
        for (XWPFRun s : source.getRuns()) {
            XWPFRun targetrun = target.createRun();
            copyRun(s, targetrun);
        }
    }
    /**
     * 复制RUN，从source到target
     */
    private static void copyRun(XWPFRun source, XWPFRun target) {
        // 设置run属性
        target.getCTR().setRPr(source.getCTR().getRPr());
        // 设置文本
        target.setText(source.getText(0));
    }
    /**
     * 重新组装List<Map<String,String>>数据(自动生成序号)
     */
    private static List<Map<String, Object>> packListMap(List<Map<String, Object>> list) {
        int count = 0;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                count++;
                Map<String, Object> map = list.get(i);
                map.put("index", count + "");
            }
        }
        return list;
    }
    /**
     * 判断文本中时候包含$
     * @param text 文本
     * @return 包含返回true,不包含返回false
     */
    private static boolean checkText(String text) {
        boolean check = false;
        if (text.indexOf("$") != -1) {
            check = true;
        }
        return check;

    }
    /**
     * 重新组装map数据
     */
    private static Map<String, Object> packMap(Map<String, Object> map) {
        Map<String, Object> newMap = new HashMap<>();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            String key = "${" + e.getKey() + "}";
            newMap.put(key, e.getValue());
        }
        return newMap;
    }
    /**
     * 替换所有段落中的标记（表格外替换）
     * @param xwpfParagraphList 所有段落集合
     * @param params 所有替换数据集合
     */
    private static void replaceInAllParagraphs(List<XWPFParagraph> xwpfParagraphList, Map<String, Object> params) {
        for (XWPFParagraph paragraph : xwpfParagraphList) {
            if (paragraph.getText() == null || paragraph.getText().equals("")){
                continue;
            }
            for (String key : params.keySet()) {
                if (paragraph.getText().contains(key)) {
                    replaceInParagraph(paragraph, key, (String) params.get(key));
                }
            }
        }
    }
    /**
     * 替换段落中的字符串
     * @param xwpfParagraph 一个段落
     * @param oldString 模板字符串
     * @param newString 替换的数据字符串
     */
    private static void replaceInParagraph(XWPFParagraph xwpfParagraph,String oldString, String newString) {
        Map<String, Integer> pos_map = findSubRunPosInParagraph(xwpfParagraph,oldString);
        if (pos_map != null) {
            List<XWPFRun> runs = xwpfParagraph.getRuns();
            XWPFRun modelRun = runs.get(pos_map.get("end_pos"));
            XWPFRun xwpfRun = xwpfParagraph.insertNewRun(pos_map.get("end_pos") + 1);
            xwpfRun.setText(newString);
            if (modelRun.getFontSize() != -1){
                xwpfRun.setFontSize(modelRun.getFontSize());// 默认值是五号字体，但五号字体getFontSize()时，返回-1
            }
            xwpfRun.setFontFamily(modelRun.getFontFamily());
            for (int i = pos_map.get("end_pos"); i >= pos_map.get("start_pos"); i--) {
                xwpfParagraph.removeRun(i);
            }
        }
    }
    /*
     * 找到段落中子串的起始XWPFRun下标和终止XWPFRun的下标
     * @param xwpfParagraph 一个段落
     * @param substring 需要替换的包含${}的字符串
     * @return
     */
    private static Map<String, Integer> findSubRunPosInParagraph(XWPFParagraph xwpfParagraph, String substring) {
        List<XWPFRun> runs = xwpfParagraph.getRuns();
        int start_pos = 0;
        int end_pos = 0;
        String subtemp = "";
        for (int i = 0; i < runs.size(); i++) {
            subtemp = "";
            start_pos = i;
            for (int j = i; j < runs.size(); j++) {
                if (runs.get(j).getText(runs.get(j).getTextPosition()) == null){
                    continue;
                }
                subtemp += runs.get(j).getText(runs.get(j).getTextPosition());
                if (subtemp.equals(substring)) {
                    end_pos = j;
                    Map<String, Integer> map = new HashMap<>();
                    map.put("start_pos", start_pos);
                    map.put("end_pos", end_pos);
                    return map;
                }
            }
        }
        return null;
    }




    public static void searchAndReplace(OutputStream outStream , Map<String, Object> map) {
        try {
            InputStream inputStream = new ClassPathResource("template/output_document.docx").getInputStream();
            XWPFDocument document = new XWPFDocument(inputStream);

            /**
             * 替换段落中的指定文字
             */
            Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
            String text;
            Set<String> set;
            XWPFParagraph paragraph;
            List<XWPFRun> run;
            String key;
            while (itPara.hasNext()) {
                paragraph = itPara.next();
                set = map.keySet();
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    key = iterator.next();
                    run = paragraph.getRuns();
                    for (int i = 0, runSie = run.size(); i < runSie; i++) {
                        text = run.get(i).getText(run.get(i).getTextPosition());
                        if (text != null && text.equals(key)) {
                            run.get(i).setText(String.valueOf(map.get(key)), 0);
                        }
                    }
                }
            }
                /**
                 * 替换表格中的指定文字
                 */
            Iterator<XWPFTable> itTable = document.getTablesIterator();
            while (itTable.hasNext()) {
                XWPFTable table = itTable.next();
                int count = table.getNumberOfRows();
                for (int i = 0; i < count; i++) {
                    XWPFTableRow row = table.getRow(i);
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        for (Map.Entry<String, Object> e : map.entrySet()) {
                            if (cell.getText().equals(e.getKey())) {
                                cell.removeParagraph(0);
                                cell.setText(String.valueOf(e.getValue()));
                            }
                        }
                    }
                }
            }
            document.write(outStream);
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}