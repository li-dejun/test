package com.ldj.test.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 	导出excel文件（xls,xlsx）
 * @author 李德军
 *
 */
public class ExportExcel {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ExportExcel.class);

    /* LONG */
    protected static final String LONG = "java.lang.Long";
    /* SHORT */
    protected static final String SHORT = "java.lang.Short";
    /* INT */
    protected static final String INT = "java.lang.Integer";
    /* STRING */
    protected static final String STRING = "java.lang.String";
    /* DATE */
    protected static final String DATE = "java.sql.Timestamp";
    /* BIG */
    protected static final String BIG = "java.math.BigDecimal";
    /* CLOB */
    protected static final String CLOB = "oracle.sql.CLOB";

    public static void exportExcel(HttpServletResponse response,HttpServletRequest request,
                                   String title,String[] excelHeadArray, List<List<Object>> excelBodyList,int type) throws Exception {//导出excel表格
        if(type != 0 && type != 1){
            throw new Exception("无效的excel导出类型，type=0表示xls，type=1表示xlsx");
        }
        if(type == 0){
            exportXLS(response, request, title, excelHeadArray,excelBodyList);
        }else if(type == 1) {
            exportXLSX(response, request, title, excelHeadArray,excelBodyList);
        }
    }
    /**
     * 	导出XSL格式
     * @param response
     * @param request
     * @param title
     * @param excelHeadArray
     * @param excelBodyList
     * @throws Exception
     */
    public static void exportXLS(HttpServletResponse response,HttpServletRequest request,
                                 String title,
                                 String[] excelHeadArray, List<List<Object>> excelBodyList) throws Exception {
        //输出流定义
        OutputStream os = response.getOutputStream();
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] fileNameByte = (title + ".xls").getBytes("GBK");
        String filename = new String(fileNameByte, "ISO8859-1");

        response.setContentType("application/x-msdownload");
        response.setCharacterEncoding("UTF-8");response.setHeader("Content-Disposition", "attachment;filename=" + filename);

        //FileOutputStream fileout = new FileOutputStream(new File("C:\\softs\\test.xls"));

        //创建excel
        HSSFWorkbook hssf_w_book=new HSSFWorkbook();
        HSSFSheet hssf_w_sheet=hssf_w_book.createSheet(title);//创建工作簿
        hssf_w_sheet.setDefaultColumnWidth(21); //固定列宽度
        HSSFRow hssf_w_row=null;//创建一行
        HSSFCell hssf_w_cell=null;//创建每个单元格

        //定义表头单元格样式
        HSSFCellStyle head_cellStyle = hssf_w_book.createCellStyle();
        //定义表头字体样式
        HSSFFont head_font = hssf_w_book.createFont();
        head_font.setFontName("宋体");//设置头部字体为宋体
        head_font.setBoldweight(Font.BOLDWEIGHT_BOLD); //粗体
        head_font.setFontHeightInPoints((short) 10); //字体大小
        //表头单元格样式设置
        head_cellStyle.setFont(head_font);//单元格样式使用字体
        head_cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        head_cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
        //定义数据单元格样式
        HSSFCellStyle cellStyle_CN = hssf_w_book.createCellStyle();//创建数据单元格样式(数据库数据样式)

        //在多表头导出时，定义第一个表头出现位置
        int titleFlag = 0;
        //创建表头&表头写入位置
        hssf_w_row = hssf_w_sheet.createRow(titleFlag);
        for(int i = 0; i < excelHeadArray.length; i++){//填充表头的每个cell
            hssf_w_cell = hssf_w_row.createCell(i);
            hssf_w_cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            hssf_w_cell.setCellValue(excelHeadArray[i]);
            hssf_w_cell.setCellStyle(head_cellStyle);
            //hssf_w_sheet.autoSizeColumn(( short ) i );
        }

        //遍历创建行
        for(List<Object> data_rows : excelBodyList) {
            titleFlag++;
            //定义数据行
            hssf_w_row = hssf_w_sheet.createRow(titleFlag);

            //遍历创建cell
            for (int i = 0; i < data_rows.size(); i++) {
                hssf_w_cell = hssf_w_row.createCell(i);
                Object in = data_rows.get(i);
                //写入cell数据
                type4ExcelXLS(in,hssf_w_cell,cellStyle_CN);
            }
        }
        //合并单元格
        mergeRowCellXLS(hssf_w_sheet,0, 1, excelBodyList.size()+1, false, 0);

        //excel文件导出
        hssf_w_book.write(os);
        //hssf_w_book.write(fileout);
        //fileout.close();

        os.close();
        request.getSession().setAttribute("EXCEL_FINISH", "1");

    }
    /**
     * 	导出XSLX格式
     * @param response
     * @param request
     * @param title
     * @param excelHeadArray
     * @param excelBodyList
     * @throws Exception
     */
    public static void exportXLSX(HttpServletResponse response,HttpServletRequest request,
                                  String title,
                                  String[] excelHeadArray, List<List<Object>> excelBodyList) throws Exception {
        //输出流定义
        OutputStream os = response.getOutputStream();
        byte[] fileNameByte = (title + ".xlsx").getBytes("GBK");
        String filename = new String(fileNameByte, "ISO8859-1");
        response.setContentType("application/x-msdownload");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);

        //创建excel文件
        XSSFWorkbook xssf_w_book=new XSSFWorkbook();
        XSSFSheet xssf_w_sheet=xssf_w_book.createSheet(title);
        xssf_w_sheet.setDefaultColumnWidth(21); //固定列宽度
        XSSFRow xssf_w_row=null;//创建一行
        XSSFCell xssf_w_cell=null;//创建每个单元格

        //定义表头单元格样式
        XSSFCellStyle head_cellStyle=xssf_w_book.createCellStyle();
        //定义表头字体样式
        XSSFFont head_font=xssf_w_book.createFont();
        head_font.setFontName("宋体");//设置头部字体为宋体
        head_font.setBoldweight(Font.BOLDWEIGHT_BOLD); //粗体
        head_font.setFontHeightInPoints((short) 10);
        //表头单元格样式设置
        head_cellStyle.setFont(head_font);//单元格使用表头字体样式
        head_cellStyle.setAlignment(HorizontalAlignment.CENTER);
        head_cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        head_cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        head_cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        head_cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        head_cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        //定义数据单元格样式
        XSSFCellStyle cellStyle_CN=xssf_w_book.createCellStyle();//创建数据单元格样式(数据库数据样式)
        cellStyle_CN.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle_CN.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        cellStyle_CN.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle_CN.setBorderTop(XSSFCellStyle.BORDER_THIN);

        //在多表头导出时，定义第一个表头出现位置
        int titleFlag = 0;
        //创建表头&表头写入位置
        xssf_w_row = xssf_w_sheet.createRow(titleFlag);
        for(int i = 0; i < excelHeadArray.length; i++){//填充表头的每个cell
            xssf_w_cell = xssf_w_row.createCell(i);
            xssf_w_cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            xssf_w_cell.setCellValue(excelHeadArray[i]);
            xssf_w_cell.setCellStyle(head_cellStyle);
        }

        //遍历创建行
        for(List<Object> data_rows : excelBodyList) {
            titleFlag++;
            //定义数据行
            xssf_w_row=xssf_w_sheet.createRow(titleFlag);

            //遍历创建cell
            for (int i = 0; i < data_rows.size(); i++) {
                xssf_w_cell = xssf_w_row.createCell(i);
                Object in = data_rows.get(i);
                //写入cell数据
                type4ExcelXLSX(in,xssf_w_cell,cellStyle_CN);
            }
        }
        //合并单元格
        mergeRowCellXLSX(xssf_w_sheet,0, 1, excelBodyList.size()+1, false, 2);

        //excel文件导出
        xssf_w_book.write(os);
        os.close();
        request.getSession().setAttribute("EXCEL_FINISH", "1");
    }

    /**
     * 	根据类型自适应格式
     * @param col
     * @param row
     * @param in
     * @return
     * @throws Exception
     */
    public static void type4ExcelXLS(Object in, HSSFCell cell, HSSFCellStyle style) throws Exception{
        if (null == in){
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            HSSFRichTextString hssfString = new HSSFRichTextString("");
            cell.setCellValue(hssfString);
            cell.setCellStyle(style);
        }else{
//            in = ClobUtils.clobToString(in);
            String type = in.getClass().getName();
            if (INT.equals(type)){
                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(Double.parseDouble(String.valueOf(in)));
                cell.setCellStyle(style);
            }else if (LONG.equals(type) && String.valueOf(in).length() <= 11){
                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(Double.parseDouble(String.valueOf(in)));
                cell.setCellStyle(style);
            }else if (SHORT.equals(type)){
                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(Double.parseDouble(String.valueOf(in)));
                cell.setCellStyle(style);
            }else if (DATE.equals(type)) {
                java.sql.Timestamp sqlDate = (java.sql.Timestamp)in;
                Date d = new java.util.Date(sqlDate.getTime());
                Date ds = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(d));
                SimpleDateFormat df = null;
                if (d.compareTo(ds) == 0){
                    df = new SimpleDateFormat("yyyy-MM-dd");
                }else{
                    df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                HSSFRichTextString hssfString = new HSSFRichTextString(df.format(d));
                cell.setCellValue(hssfString);
                cell.setCellStyle(style);
            }else if (in instanceof java.util.Date){
                Date d = (Date)in;
                Date ds = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(d));
                SimpleDateFormat df = null;
                if (d.compareTo(ds) == 0){
                    df = new SimpleDateFormat("yyyy-MM-dd");
                }else{
                    df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                HSSFRichTextString hssfString = new HSSFRichTextString(df.format(d));
                cell.setCellValue(hssfString);
                cell.setCellStyle(style);
            }else if (STRING.equals(type)){
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                HSSFRichTextString hssfString = new HSSFRichTextString(String.valueOf(in));
                cell.setCellValue(hssfString);
                cell.setCellStyle(style);
            }else if (in instanceof BigDecimal){
                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(Double.parseDouble(String.valueOf(in)));
                cell.setCellStyle(style);
            }else{
                try{
                    double d = Double.parseDouble(String.valueOf(in));
                    //if (String.valueOf(d).equals(String.valueOf(in)) && String.valueOf(in).length() <= 11){
                    if (String.valueOf(in).length() <= 11){
                        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(d);
                        cell.setCellStyle(style);
                    }else{
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        HSSFRichTextString hssfString = new HSSFRichTextString(String.valueOf(in));
                        cell.setCellValue(hssfString);
                        cell.setCellStyle(style);
                    }

                }catch (Exception e) {
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    HSSFRichTextString hssfString = new HSSFRichTextString("");
                    cell.setCellValue(hssfString);
                    cell.setCellStyle(style);
                    LOGGER.error("excel解析:", e);
                }

            }
        }

    }

    /**
     * 	根据类型自适应格式
     * @param col
     * @param row
     * @param in
     * @return
     * @throws Exception
     */
    public static void type4ExcelXLSX(Object in, XSSFCell cell, XSSFCellStyle style) throws Exception{
        if (null == in){
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            XSSFRichTextString xssfString = new XSSFRichTextString("");
            cell.setCellValue(xssfString);
            cell.setCellStyle(style);
        }else{
            //in = ClobUtils.clobToString(in);
            String type = in.getClass().getName();
            if (INT.equals(type)){
                cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(Double.parseDouble(String.valueOf(in)));
                cell.setCellStyle(style);
            }else if (LONG.equals(type) && String.valueOf(in).length() <= 11){
                cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(Double.parseDouble(String.valueOf(in)));
                cell.setCellStyle(style);
            }else if (SHORT.equals(type)){
                cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(Double.parseDouble(String.valueOf(in)));
                cell.setCellStyle(style);
            }else if (DATE.equals(type)) {
                java.sql.Timestamp sqlDate = (java.sql.Timestamp)in;
                Date d = new java.util.Date(sqlDate.getTime());
                Date ds = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(d));
                SimpleDateFormat df = null;
                if (d.compareTo(ds) == 0){
                    df = new SimpleDateFormat("yyyy-MM-dd");
                }else{
                    df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                XSSFRichTextString xssfString = new XSSFRichTextString(df.format(d));
                cell.setCellValue(xssfString);
                cell.setCellStyle(style);
            }else if (in instanceof java.util.Date){
                Date d = (Date)in;
                Date ds = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(d));
                SimpleDateFormat df = null;
                if (d.compareTo(ds) == 0){
                    df = new SimpleDateFormat("yyyy-MM-dd");
                }else{
                    df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                XSSFRichTextString xssfString = new XSSFRichTextString(df.format(d));
                cell.setCellValue(xssfString);
                cell.setCellStyle(style);
            }else if (STRING.equals(type)){
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                XSSFRichTextString xssfString = new XSSFRichTextString(String.valueOf(in));
                cell.setCellValue(xssfString);
                cell.setCellStyle(style);
            }else if (in instanceof BigDecimal){
                cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(Double.parseDouble(String.valueOf(in)));
                cell.setCellStyle(style);
            }else{
                try{
                    double d = Double.parseDouble(String.valueOf(in));
                    //if (String.valueOf(d).equals(String.valueOf(in)) && String.valueOf(in).length() <= 11){
                    if (String.valueOf(in).length() <= 11){
                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(d);
                        cell.setCellStyle(style);
                    }else{
                        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                        XSSFRichTextString xssfString = new XSSFRichTextString(String.valueOf(in));
                        cell.setCellValue(xssfString);
                        cell.setCellStyle(style);
                    }

                }catch (Exception e) {
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    XSSFRichTextString xssfString = new XSSFRichTextString("");
                    cell.setCellValue(xssfString);
                    cell.setCellStyle(style);
                    LOGGER.error("excel解析:", e);
                }

            }
        }

    }

    /**
     * 	XLS合并单元格
     * @param sheet
     * @param colIdx	合并的列
     * @param startRow	起始行
     * @param stopRow	结束行
     * @param isForward	是否递进合并其它列
     * @param forwardToColIdx 递进到的列
     */
    public static void mergeRowCellXLS(HSSFSheet sheet,int colIdx,int startRow,int stopRow ,boolean isForward,int forwardToColIdx){
        HSSFWorkbook hssf_w_book=new HSSFWorkbook();
        String compareValue = null;
        int beginRow = startRow;
        int endRow = startRow;
        for(int i=startRow;i<stopRow; ++i){
            System.out.println(i);
            String value = sheet.getRow(i).getCell(colIdx).getRichStringCellValue()==null ? "":sheet.getRow(i).getCell(colIdx).getRichStringCellValue().toString();
            if(i == startRow){
                compareValue = value;
            }else{
                if(compareValue.equals(value)){//相同，则设置重复的值为空
                    sheet.getRow(i).getCell(colIdx).setCellValue("");
                    endRow = i;
                }else {//不同，则合并之前相同的单元格
                    if(beginRow < endRow){
                        CellRangeAddress cellRangeAddress = new CellRangeAddress(beginRow, endRow, colIdx, colIdx);
                        sheet.addMergedRegion(cellRangeAddress);
                        HSSFCellStyle jz = hssf_w_book.createCellStyle();//新建单元格样式
                        //jz.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
                        jz.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
                        sheet.getRow(beginRow).getCell(colIdx).getCellStyle().cloneStyleFrom(jz);

                        if(isForward){//递进合并下一列
                            int nextColIndex = colIdx;
                            if(colIdx < forwardToColIdx){
                                nextColIndex ++;
                            }else if(colIdx > forwardToColIdx){
                                nextColIndex --;
                            }else{
                                return;
                            }
                            mergeRowCellXLS(sheet, nextColIndex, beginRow, endRow, isForward, forwardToColIdx);
                        }
                    }

                    compareValue = value;
                    beginRow = i;
                    endRow = i;
                }
            }

        }
        if(beginRow < endRow){
            CellRangeAddress cellRangeAddress = new CellRangeAddress(beginRow, endRow, colIdx, colIdx);
            sheet.addMergedRegion(cellRangeAddress);
            HSSFCellStyle jz = hssf_w_book.createCellStyle();//新建单元格样式
//           jz.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
            jz.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            sheet.getRow(beginRow).getCell(colIdx).getCellStyle().cloneStyleFrom(jz);
            if(isForward){//递进合并下一列
                int nextColIndex = colIdx;
                if(colIdx < forwardToColIdx){
                    nextColIndex ++;
                }else if(colIdx > forwardToColIdx){
                    nextColIndex --;
                }else{
                    return;
                }
                mergeRowCellXLS(sheet, nextColIndex, beginRow, endRow, isForward, forwardToColIdx);
            }
        }
    }

    /**
     * 	XLSX合并单元格
     * @param sheet
     * @param colIdx	合并的列
     * @param startRow	起始行
     * @param stopRow	结束行
     * @param isForward	是否递进合并其它列
     * @param forwardToColIdx 递进到的列
     */
    public static void mergeRowCellXLSX(XSSFSheet sheet,int colIdx,int startRow,int stopRow ,boolean isForward,int forwardToColIdx){
        XSSFWorkbook xssf_w_book=new XSSFWorkbook();
        String compareValue = null;
        int beginRow = startRow;
        int endRow = startRow;
        for(int i=startRow;i<stopRow; ++i){
            String value = sheet.getRow(i).getCell(colIdx).getRichStringCellValue()==null ? "":sheet.getRow(i).getCell(colIdx).getRichStringCellValue().toString();
            if(i == startRow){
                compareValue = value;
            }else{
                if(compareValue.equals(value)){//相同，则设置重复的值为空
                    sheet.getRow(i).getCell(colIdx).setCellValue("");
                    endRow = i;
                }else {//不同，则合并之前相同的单元格
                    if(beginRow < endRow){
                        CellRangeAddress cellRangeAddress = new CellRangeAddress(beginRow, endRow, colIdx, colIdx);
                        sheet.addMergedRegion(cellRangeAddress);
                        XSSFCellStyle jz = xssf_w_book.createCellStyle();//新建单元格样式
                        jz.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直居中
                        sheet.getRow(beginRow).getCell(colIdx).getCellStyle().cloneStyleFrom(jz);


                        if(isForward){//递进合并下一列
                            int nextColIndex = colIdx;
                            if(colIdx < forwardToColIdx){
                                nextColIndex ++;
                            }else if(colIdx > forwardToColIdx){
                                nextColIndex --;
                            }else{
                                return;
                            }
                            mergeRowCellXLSX(sheet, nextColIndex, beginRow, endRow, isForward, forwardToColIdx);
                        }
                    }

                    compareValue = value;
                    beginRow = i;
                    endRow = i;
                }
            }

        }
        if(beginRow < endRow){
            CellRangeAddress cellRangeAddress = new CellRangeAddress(beginRow, endRow, colIdx, colIdx);
            sheet.addMergedRegion(cellRangeAddress);
            XSSFCellStyle jz = xssf_w_book.createCellStyle();//新建单元格样式
//           jz.setAlignment(XSSFCellStyle.ALIGN_CENTER);//水平居中
            jz.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直居中
            sheet.getRow(beginRow).getCell(colIdx).getCellStyle().cloneStyleFrom(jz);
            if(isForward){//递进合并下一列
                int nextColIndex = colIdx;
                if(colIdx < forwardToColIdx){
                    nextColIndex ++;
                }else if(colIdx > forwardToColIdx){
                    nextColIndex --;
                }else{
                    return;
                }
                mergeRowCellXLSX(sheet, nextColIndex, beginRow, endRow, isForward, forwardToColIdx);
            }
        }
    }

}

