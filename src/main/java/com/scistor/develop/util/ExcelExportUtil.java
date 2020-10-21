package com.scistor.develop.util;

import jxl.CellView;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelExportUtil {
    //表头
    private String title;
    //各个列的表头
    private String[] heardList;
    //各个列的元素key值
    private String[] heardKey;
    //需要填充的数据信息
    private List<Map> data;
    //字体大小
    private int fontSize = 10;
    //行高
    private int rowHeight = 25;
    //列宽
    private int columWidth = 200;
    //工作表
    private String sheetName = "sheet1";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getHeardList() {
        return heardList;
    }

    public void setHeardList(String[] heardList) {
        this.heardList = heardList;
    }

    public String[] getHeardKey() {
        return heardKey;
    }

    public void setHeardKey(String[] heardKey) {
        this.heardKey = heardKey;
    }

    public List<Map> getData() {
        return data;
    }

    public void setData(List<Map> data) {
        this.data = data;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }

    public int getColumWidth() {
        return columWidth;
    }

    public void setColumWidth(int columWidth) {
        this.columWidth = columWidth;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    /**
     * 开始导出数据信息
     *
     */
    public byte[] exportExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //检查参数配置信息
        checkConfig();
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建工作表
        HSSFSheet wbSheet = wb.createSheet(this.sheetName);
        //设置默认行宽
        wbSheet.setDefaultColumnWidth(20);

        // 标题样式（加粗，垂直居中）
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        HSSFFont fontStyle = wb.createFont();
        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontStyle.setBold(true);   //加粗
        fontStyle.setFontHeightInPoints((short)16);  //设置标题字体大小
        cellStyle.setFont(fontStyle);

        //在第0行创建rows  (表标题)
        HSSFRow title = wbSheet.createRow((int) 0);
        title.setHeightInPoints(30);//行高
        HSSFCell cellValue = title.createCell(0);
        cellValue.setCellValue(this.title);
        cellValue.setCellStyle(cellStyle);
        wbSheet.addMergedRegion(new CellRangeAddress(0,0,0,(this.heardList.length-1)));
        //设置表头样式，表头居中
        HSSFCellStyle style = wb.createCellStyle();
        //设置单元格样式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //设置字体
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) this.fontSize);
        style.setFont(font);
        //在第1行创建rows
        HSSFRow row = wbSheet.createRow((int) 1);
        //设置列头元素
        HSSFCell cellHead = null;
        for (int i = 0; i < heardList.length; i++) {
            cellHead = row.createCell(i);
            cellHead.setCellValue(heardList[i]);
            cellHead.setCellStyle(style);
        }

        //设置每格数据的样式 （字体红色）
        HSSFCellStyle cellParamStyle = wb.createCellStyle();
        HSSFFont ParamFontStyle = wb.createFont();
        cellParamStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellParamStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        ParamFontStyle.setColor(HSSFColor.DARK_RED.index);   //设置字体颜色 (红色)
        ParamFontStyle.setFontHeightInPoints((short) this.fontSize);
        cellParamStyle.setFont(ParamFontStyle);
        //设置每格数据的样式2（字体蓝色）
        HSSFCellStyle cellParamStyle2 = wb.createCellStyle();
        cellParamStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellParamStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont ParamFontStyle2 = wb.createFont();
        ParamFontStyle2.setColor(HSSFColor.BLUE.index);   //设置字体颜色 (蓝色)
        ParamFontStyle2.setFontHeightInPoints((short) this.fontSize);
        cellParamStyle2.setFont(ParamFontStyle2);
        //开始写入实体数据信息
        int a = 2;
        for (int i = 0; i < data.size(); i++) {
            HSSFRow roww = wbSheet.createRow((int) a);
            Map map = data.get(i);
            HSSFCell cell = null;
            for (int j = 0; j < heardKey.length; j++) {
                cell = roww.createCell(j);
                cell.setCellStyle(style);
                Object valueObject = map.get(heardKey[j]);
                String value = null;
                if (valueObject == null) {
                    valueObject = "";
                }
                if (valueObject instanceof String) {
                    //取出的数据是字符串直接赋值
                    value = (String) map.get(heardKey[j]);
                } else if (valueObject instanceof Integer) {
                    //取出的数据是Integer
                    value = String.valueOf(((Integer) (valueObject)).floatValue());
                } else if (valueObject instanceof BigDecimal) {
                    //取出的数据是BigDecimal
                    value = String.valueOf(((BigDecimal) (valueObject)).floatValue());
                } else {
                    value = valueObject.toString();
                }
                //设置单个单元格的字体颜色
                if(heardKey[j].equals("ddNum") || heardKey[j].equals("sjNum")){
                if((Long)map.get("ddNum")!=null){
                    if((Long)map.get("sjNum")==null){
                        cell.setCellStyle(cellParamStyle);
                    } else if((Long) map.get("ddNum") != (Long) map.get("sjNum")){
                        if ((Long) map.get("ddNum") > (Long) map.get("sjNum")) {
                            cell.setCellStyle(cellParamStyle);
                        }
                        if ((Long) map.get("ddNum") < (Long) map.get("sjNum")) {
                            cell.setCellStyle(cellParamStyle2);
                        }
                    }else {
                        cell.setCellStyle(style);
                    }
                }
                }
                cell.setCellValue(StringUtils.isEmpty(value) ? "" : value);
            }
            a++;
        }

        //导出数据
        try {
            //设置Http响应头告诉浏览器下载这个附件
            response.setHeader("Content-Disposition", "attachment;Filename=" + System.currentTimeMillis() + ".xls");
            OutputStream outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.close();
            return wb.getBytes();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException("导出Excel出现严重异常，异常信息：" + ex.getMessage());
        }

    }
    

    /**
     * 开始导出数据信息
     *
     */
    public void exportExportLocal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //检查参数配置信息
        checkConfig();
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建工作表
        //HSSFSheet wbSheet = wb.createSheet(this.sheetName);
        HSSFSheet wbSheet = wb.createSheet();
        //设置默认行宽
        wbSheet.setDefaultColumnWidth(20);

        // 标题样式（加粗，垂直居中）
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        HSSFFont fontStyle = wb.createFont();
        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontStyle.setBold(true);   //加粗
        fontStyle.setFontHeightInPoints((short)16);  //设置标题字体大小
        cellStyle.setFont(fontStyle);

        //在第0行创建rows
        HSSFRow row = wbSheet.createRow((int) 0);
        row.setHeightInPoints(30);//行高
        HSSFCell cellValue = row.createCell(0);
        cellValue.setCellStyle(cellStyle);
        //设置表头样式，表头居中
        HSSFCellStyle style = wb.createCellStyle();
        //设置单元格样式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //设置字体
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) this.fontSize);
        style.setFont(font);
        //设置列头元素
        HSSFCell cellHead = null;
        for (int i = 0; i < heardList.length; i++) {
            cellHead = row.createCell(i);
            cellHead.setCellValue(heardList[i]);
            cellHead.setCellStyle(style);
        }

        //设置每格数据的样式 （字体红色）
        HSSFCellStyle cellParamStyle = wb.createCellStyle();
        HSSFFont ParamFontStyle = wb.createFont();
        cellParamStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellParamStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        ParamFontStyle.setColor(HSSFColor.DARK_RED.index);   //设置字体颜色 (红色)
        ParamFontStyle.setFontHeightInPoints((short) this.fontSize);
        cellParamStyle.setFont(ParamFontStyle);
        //设置每格数据的样式2（字体蓝色）
        HSSFCellStyle cellParamStyle2 = wb.createCellStyle();
        cellParamStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellParamStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont ParamFontStyle2 = wb.createFont();
        ParamFontStyle2.setColor(HSSFColor.BLUE.index);   //设置字体颜色 (蓝色)
        ParamFontStyle2.setFontHeightInPoints((short) this.fontSize);
        cellParamStyle2.setFont(ParamFontStyle2);
        //开始写入实体数据信息
        int a = 1;
        for (int i = 0; i < data.size(); i++) {
            HSSFRow roww = wbSheet.createRow((int) (i*4+1));
            HSSFRow roww1 = wbSheet.createRow((int) (i*4+2));
            HSSFRow roww2 = wbSheet.createRow((int) (i*4+3));
            HSSFRow roww3 = wbSheet.createRow((int) (i*4+4));
            Map map = data.get(i);
            for (int j = 0; j < heardKey.length; j++) {
            	if(j<=4) {
                    Object valueObject = map.get(heardKey[j]);
                    String value = null;
                    if (valueObject == null) {
                        valueObject = "";
                    }
                    if (valueObject instanceof Date) {
                        //取出的数据是字符串直接赋值
                        value = DateTool.dateTimeSec2Str((Date) map.get(heardKey[j]));
                    } else if (valueObject instanceof String) {
                        //取出的数据是字符串直接赋值
                        value = (String) map.get(heardKey[j]);
                    } else if (valueObject instanceof Integer) {
                        //取出的数据是Integer
                        value = String.valueOf(((Integer) (valueObject)).floatValue());
                    } else if (valueObject instanceof BigDecimal) {
                        //取出的数据是BigDecimal
                        value = String.valueOf(((BigDecimal) (valueObject)).floatValue());
                    } else {
                        value = valueObject.toString();
                    }
                    value = value.indexOf(".") > 0 ? value.substring(0,value.indexOf(".")) : value;

            		wbSheet.addMergedRegion(new CellRangeAddress((a-1)*4+1,a*4,j,j));
            		HSSFCell cell = roww.createCell(j);
            		cell.setCellValue(value); // 跨单元格显示的数据
            		cell.setCellStyle(style);
            	} else if (j> 4 && j <= 8) {
            		HSSFCell cell = roww.createCell(j);
            		if(j==5) {
            			cell.setCellValue("验证款");
            		} else if(j==6) {
            			cell.setCellValue(map.get("accountBank")+""); // 跨单元格显示的数据
            		} else if(j==7) {
                        cell.setCellValue(map.get("colNum")+""); // 跨单元格显示的数据
                    } else {
                        cell.setCellValue(map.get("colAccount")+""); // 跨单元格显示的数据
                    }
            		wbSheet.addMergedRegion(new CellRangeAddress((a-1)*4+1,a*4-2,j,j));
            		cell.setCellStyle(style);
            		
            		HSSFCell cell2 = roww2.createCell(j);
            		if(j==5) {
            			cell2.setCellValue("验证退款");
            		} else if(j==6) {
                        cell2.setCellValue(map.get("accountBank")+""); // 跨单元格显示的数据
                    } else if(j==7) {
                        cell2.setCellValue(map.get("colNum")+""); // 跨单元格显示的数据
                    } else {
                        cell2.setCellValue(map.get("colAccount")+""); // 跨单元格显示的数据
                    }
            		wbSheet.addMergedRegion(new CellRangeAddress(a*4-1,a*4,j,j));
            		cell2.setCellStyle(style);
            	} else {
            		HSSFCell cell = roww.createCell(j);
            		if(j==9) {
            			cell.setCellValue("银行存款"); 
            		} else if(j==10){ 
            			cell.setCellValue(map.get("acceptanceBorrower")+"");
            		} else {
            			cell.setCellValue("-"); // 跨单元格显示的数据 
            		}
            		cell.setCellStyle(style);
            		
            		
            		HSSFCell cell1 = roww1.createCell(j);
            		if(j==9) {
            			cell1.setCellValue("暂存款"); 
            		} else if(j==10){ 
            			cell1.setCellValue("-");
            		} else {
            			cell1.setCellValue(map.get("acceptanceLender")+""); // 跨单元格显示的数据
            		}
            		cell1.setCellStyle(style);
            		
            		HSSFCell cell2 = roww2.createCell(j);
            		if(j==9) {
            			cell2.setCellValue("银行存款"); 
            		} else if (j==10) {
            			cell2.setCellValue("-");
            		} else {
            			cell2.setCellValue(map.get("acceptanceRefundBorrower")+""); // 跨单元格显示的数据
            		}
            		cell2.setCellStyle(style);

            		HSSFCell cell3 = roww3.createCell(j);
            		if(j==9) {
            			cell3.setCellValue("暂存款"); 
            		} else if (j==10) {
            			cell3.setCellValue(map.get("acceptanceRefundLender")+"");
            		} else {
            			cell3.setCellValue("-"); // 跨单元格显示的数据 
            		}
            		cell3.setCellStyle(style);
            	}
            }
            a++;
        }

        //导出数据
        try {
            //设置Http响应头告诉浏览器下载这个附件
            response.setHeader("Content-Disposition", "attachment;Filename="+ URLEncoder.encode("财务核算", "UTF-8")+".xls");
            response.setContentType("application/x-excel");
            OutputStream outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException("导出Excel出现严重异常，异常信息：" + ex.getMessage());
        }
    }


    /**
     * 开始导出数据信息
     *
     */
    public void exportExportLocal2(HttpServletRequest request, HttpServletResponse response,String a,String b) throws IOException {
        //检查参数配置信息
        checkConfig();
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建工作表
        //HSSFSheet wbSheet = wb.createSheet(this.sheetName);
        HSSFSheet wbSheet = wb.createSheet();
        //设置默认行宽
        wbSheet.setDefaultColumnWidth(20);

        // 标题样式（加粗，垂直居中）
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        HSSFFont fontStyle = wb.createFont();
        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontStyle.setBold(true);   //加粗
        fontStyle.setFontHeightInPoints((short)10);  //设置标题字体大小
        cellStyle.setFont(fontStyle);

        //在第0行创建rows
        HSSFRow row = wbSheet.createRow((int) 0);
        row.setHeightInPoints(30);//行高
        HSSFCell cellValue = row.createCell(0);
        cellValue.setCellStyle(cellStyle);
        //设置表头样式，表头居中
        HSSFCellStyle style = wb.createCellStyle();
        //设置单元格样式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //设置字体
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) this.fontSize);
        style.setFont(font);
        //设置列头元素
        HSSFCell cellHead = null;
        for (int i = 0; i < heardList.length; i++) {
            cellHead = row.createCell(i);
            cellHead.setCellValue(heardList[i]);
            cellHead.setCellStyle(style);
        }

        //设置每格数据的样式 （字体红色）
        HSSFCellStyle cellParamStyle = wb.createCellStyle();
        HSSFFont ParamFontStyle = wb.createFont();
        cellParamStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellParamStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        ParamFontStyle.setColor(HSSFColor.DARK_RED.index);   //设置字体颜色 (红色)
        ParamFontStyle.setFontHeightInPoints((short) this.fontSize);
        cellParamStyle.setFont(ParamFontStyle);
        //设置每格数据的样式2（字体蓝色）
        HSSFCellStyle cellParamStyle2 = wb.createCellStyle();
        cellParamStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellParamStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont ParamFontStyle2 = wb.createFont();
        ParamFontStyle2.setColor(HSSFColor.BLUE.index);   //设置字体颜色 (蓝色)
        ParamFontStyle2.setFontHeightInPoints((short) this.fontSize);
        cellParamStyle2.setFont(ParamFontStyle2);


        HSSFCellStyle style1 = wb.createCellStyle();
        //设置单元格样式
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //设置字体
        HSSFFont font1 = wb.createFont();
        font1.setFontHeightInPoints((short) 10);
        font1.setBoldweight((short) 100);
        style1.setFont(font1);

        HSSFCellStyle style2 = wb.createCellStyle();
        //设置单元格样式
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //设置字体
        HSSFFont font2 = wb.createFont();
        font2.setFontHeightInPoints((short) 10);
        font2.setBoldweight((short) 300);
        style2.setFont(font2);

        //开始写入实体数据信息
        int c = 1;
        for (int i = 0; i < data.size(); i++) {
            HSSFRow roww = wbSheet.createRow((int) (i*4+1));
            HSSFRow roww1 = wbSheet.createRow((int) (i*4+2));
            HSSFRow roww2 = wbSheet.createRow((int) (i*4+3));
            HSSFRow roww3 = wbSheet.createRow((int) (i*4+4));
            Map map = data.get(i);
            for (int j = 0; j < heardKey.length; j++) {
                //a=0表示需要验证款和验证退款--1表示需要验证款--2表示需要验证退款
                //b=0表示需要银行存款和暂存款--1表示需要银行存款--2表示需要暂存款
                int rowIndex = ("0".equals(a)) ? (c*4-2) : (c*4);

                if(j<=4) {
                    Object valueObject = map.get(heardKey[j]);
                    String value = null;
                    if (valueObject == null) {
                        valueObject = "";
                    }
                    if (valueObject instanceof Date) {
                        //取出的数据是字符串直接赋值
                        value = DateTool.dateTimeSec2Str((Date) map.get(heardKey[j]));
                    } else if (valueObject instanceof String) {
                        //取出的数据是字符串直接赋值
                        value = (String) map.get(heardKey[j]);
                    } else if (valueObject instanceof Integer) {
                        //取出的数据是Integer
                        value = String.valueOf(((Integer) (valueObject)).floatValue());
                    } else if (valueObject instanceof BigDecimal) {
                        //取出的数据是BigDecimal
                        value = String.valueOf(((BigDecimal) (valueObject)).floatValue());
                    } else {
                        value = valueObject.toString();
                    }
                    if(StringUtils.isNotEmpty(value)){
                        value = value.indexOf(".") > 0 ? value.substring(0,value.indexOf(".")) : value;
                    }

                    wbSheet.addMergedRegion(new CellRangeAddress((c-1)*4+1,c*4,j,j));
                    HSSFCell cell = roww.createCell(j);
                    if(StringUtils.isNotEmpty(value)){
                        cell.setCellValue("0,1".contains(value) ? ("1".equals(value) ? "是" : "否") : value); // 跨单元格显示的数据
                    } else {
                        cell.setCellValue("");
                    }

                    if("playStatus".equals(heardKey[j]) || "refundStatus".equals(heardKey[j])){
                        cell.setCellStyle(style1);
                    } else if (heardKey[j].contains("Time")) {
                        cell.setCellStyle(style2);
                    } else {
                        cell.setCellStyle(style);
                    }
                } else if (j> 4 && j <= 8) {

                    HSSFCell cell = roww.createCell(j);
                    if(j==5) {
                        cell.setCellValue("2".equals(a) ? "验证退款" : "验证款");
                        wbSheet.addMergedRegion(new CellRangeAddress((c-1)*4+1,rowIndex,j,j));
                        cell.setCellStyle(style1);
                    } else if(j==6) {
                        cell.setCellValue(map.get("accountBank")+"");
                        wbSheet.addMergedRegion(new CellRangeAddress((c-1)*4+1,rowIndex,j,j));
                        cell.setCellStyle(style2);
                    } else if(j==7) {
                        cell.setCellValue(map.get("colNum")+"");
                        wbSheet.addMergedRegion(new CellRangeAddress((c-1)*4+1,rowIndex,j,j));
                        cell.setCellStyle(style1);
                    } else {
                        cell.setCellValue(map.get("colAccount")+"");
                        wbSheet.addMergedRegion(new CellRangeAddress((c-1)*4+1,rowIndex,j,j));
                        cell.setCellStyle(style2);
                    }


                    if("0".equals(a)){
                        HSSFCell cell2 = roww2.createCell(j);
                        if(j==5) {
                            cell2.setCellValue("验证退款");
                            wbSheet.addMergedRegion(new CellRangeAddress(c*4-1,c*4,j,j));
                            cell2.setCellStyle(style1);
                        } else if(j==6) {
                            cell2.setCellValue(map.get("accountBank")+"");
                            wbSheet.addMergedRegion(new CellRangeAddress(c*4-1,c*4,j,j));
                            cell2.setCellStyle(style2);
                        } else if(j==7) {
                            cell2.setCellValue(map.get("colNum")+"");
                            wbSheet.addMergedRegion(new CellRangeAddress(c*4-1,c*4,j,j));
                            cell2.setCellStyle(style1);
                        } else {
                            cell2.setCellValue(map.get("colAccount")+"");
                            wbSheet.addMergedRegion(new CellRangeAddress(c*4-1,c*4,j,j));
                            cell2.setCellStyle(style2);
                        }

                    }
                } else {
                    if("0".equals(a) && "0".equals(b)){
                        HSSFCell cell = roww.createCell(j);
                        if(j==9) {
                            cell.setCellValue("银行存款");
                        } else if(j==10){
                            cell.setCellValue(map.get("acceptanceBorrower")+"");
                        } else {
                            cell.setCellValue("-"); // 跨单元格显示的数据
                        }
                        cell.setCellStyle(style1);


                        HSSFCell cell1 = roww1.createCell(j);
                        if(j==9) {
                            cell1.setCellValue("暂存款");
                        } else if(j==10){
                            cell1.setCellValue("-");
                        } else {
                            cell1.setCellValue(map.get("acceptanceLender")+"");
                        }
                        cell1.setCellStyle(style1);

                        HSSFCell cell2 = roww2.createCell(j);
                        if(j==9) {
                            cell2.setCellValue("银行存款");
                        } else if (j==10) {
                            cell2.setCellValue("-");
                        } else {
                            cell2.setCellValue(map.get("acceptanceRefundBorrower")+"");
                        }
                        cell2.setCellStyle(style1);

                        HSSFCell cell3 = roww3.createCell(j);
                        if(j==9) {
                            cell3.setCellValue("暂存款");
                        } else if (j==10) {
                            cell3.setCellValue(map.get("acceptanceRefundLender")+"");
                        } else {
                            cell3.setCellValue("-"); // 跨单元格显示的数据
                        }
                        cell3.setCellStyle(style1);
                    } else if(!"0".equals(a) || !"0".equals(b)){
                        if("0".equals(a) && !"0".equals(b)){//合并两个格子,1,3,行合并
                            //a=0/1,b=0/1
                            //验证款----银行存款
                            HSSFCell cell = roww.createCell(j);
                            if(j==9) {
                                cell.setCellValue("1".equals(b) ? "银行存款" : "暂存款");
                            } else if(j==10){
                                cell.setCellValue("1".equals(b) ? (map.get("acceptanceBorrower")+"") : "-");
                            } else {
                                cell.setCellValue("1".equals(b) ? "-" : (map.get("acceptanceLender")+""));
                            }
                            cell.setCellStyle(style1);
                            wbSheet.addMergedRegion(new CellRangeAddress((c-1)*4+1,(c*4-2),j,j));
                            //a=0/2,b=0/2
                            //验证退款----银行存款
                            HSSFCell cell2 = roww2.createCell(j);
                            if(j==9) {
                                cell2.setCellValue("1".equals(b) ? "银行存款" : "暂存款");
                            } else if (j==10) {
                                cell2.setCellValue("1".equals(b) ? "-" : (map.get("acceptanceRefundLender")+""));
                            } else {
                                cell2.setCellValue("1".equals(b) ? (map.get("acceptanceRefundBorrower")+"") : "-"); // 跨单元格显示的数据
                            }
                            cell2.setCellStyle(style1);
                            wbSheet.addMergedRegion(new CellRangeAddress(c*4-1,c*4,j,j));
                        } else if(!"0".equals(a) && "0".equals(b)){//1,3行合并
                            //验证款----银行存款
                            HSSFCell cell = roww.createCell(j);
                            if(j==9) {
                                cell.setCellValue("银行存款");
                            } else if(j==10){
                                cell.setCellValue("1".equals(a) ? (map.get("acceptanceBorrower")+"") : "-");
                            } else {
                                cell.setCellValue("1".equals(a) ? "-" : (map.get("acceptanceRefundBorrower")+""));
                            }
                            cell.setCellStyle(style1);
                            wbSheet.addMergedRegion(new CellRangeAddress((c-1)*4+1,(c*4-2),j,j));

                            //验证款----暂存款
                            HSSFCell cell2 = roww2.createCell(j);
                            if(j==9) {
                                cell2.setCellValue("暂存款");
                            } else if(j==10){
                                cell2.setCellValue("1".equals(a) ? "-" : (map.get("acceptanceRefundLender")+""));
                            } else {
                                cell2.setCellValue("1".equals(a) ? (map.get("acceptanceLender")+"") : "-");
                            }
                            cell2.setCellStyle(style1);
                            wbSheet.addMergedRegion(new CellRangeAddress(c*4-1,c*4,j,j));
                        } else if(!"0".equals(a) && !"0".equals(b)) {
                            if ((Integer.parseInt(a)+Integer.parseInt(b)) == 3){
                                HSSFCell cell = roww.createCell(j);
                                if(j==9) {
                                    cell.setCellValue("1".equals(b) ? "银行存款" : "暂存款");
                                } else if(j==10){
                                    cell.setCellValue("-");
                                } else {
                                    cell.setCellValue("1".equals(b) ? (map.get("acceptanceRefundBorrower")+"") : (map.get("acceptanceLender")+""));
                                }
                                cell.setCellStyle(style1);
                            } else {
                                HSSFCell cell = roww.createCell(j);
                                if(j==9) {
                                    cell.setCellValue("1".equals(b) ? "银行存款" : "暂存款");
                                } else if(j==10){
                                    cell.setCellValue("1".equals(b) ? (map.get("acceptanceBorrower")+""):(map.get("acceptanceRefundLender")+""));
                                } else {
                                    cell.setCellValue("-");
                                }
                                cell.setCellStyle(style1);
                            }
                            wbSheet.addMergedRegion(new CellRangeAddress((c-1)*4+1,c*4,j,j));

                        }
                    }
                }
            }
            c++;
        }

        //导出数据
        try {
            //设置Http响应头告诉浏览器下载这个附件
            response.setHeader("Content-Disposition", "attachment;Filename="+ URLEncoder.encode("财务核算", "UTF-8")+".xls");
            response.setContentType("application/x-excel");
            OutputStream outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException("导出Excel出现严重异常，异常信息：" + ex.getMessage());
        }

    }
    
    /**
     * 检查数据配置问题
     *
     * @throws IOException 抛出数据异常类
     */
    protected void checkConfig() throws IOException {
        if (heardKey == null || heardList.length == 0) {
            throw new IOException("列名数组不能为空或者为NULL");
        }

        if (fontSize < 0 || rowHeight < 0 || columWidth < 0) {
            throw new IOException("字体、宽度或者高度不能为负值");
        }

        if (StringUtils.isEmpty(sheetName)) {
            throw new IOException("工作表表名不能为NULL");
        }
    }

    public static <T> void exportexcle(HttpServletResponse response, String fileName, List<T> listData,
                                       String sheetName, String[] columns, String[] heads) {
        response.setContentType("application/x-excel");
        try {
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(fileName.getBytes("UTF8"), "ISO8859-1") + ".xls");
            exportToExcel(response, listData, sheetName, columns, heads);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> void exportToExcel(HttpServletResponse response, List<T> objData, String sheetName,
                                         String[] columns, String[] heads) {
        try {
            OutputStream os = response.getOutputStream();

            WritableWorkbook wwb = Workbook.createWorkbook(os);

            WritableSheet ws = wwb.createSheet(sheetName, 0);

            SheetSettings ss = ws.getSettings();
            ss.setVerticalFreeze(1);

            WritableFont font1 = new WritableFont(WritableFont.createFont("微软雅黑"), 10, WritableFont.BOLD);
            WritableFont font2 = new WritableFont(WritableFont.createFont("微软雅黑"), 9, WritableFont.NO_BOLD);
            WritableCellFormat wcf = new WritableCellFormat(font1);
            WritableCellFormat wcf2 = new WritableCellFormat(font2, NumberFormats.TEXT);

            wcf.setBackground(jxl.format.Colour.SKY_BLUE);
            wcf.setAlignment(jxl.format.Alignment.CENTRE);
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            wcf2.setAlignment(jxl.format.Alignment.CENTRE);
            wcf2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

            wcf.setAlignment(Alignment.CENTRE);

            if ((columns != null) && (columns.length > 0)) {
                for (int i = 0; i < columns.length; i++) {
                    CellView view = new CellView();
                    view.setAutosize(true);
                    ws.setColumnView(i, 20);
                    ws.addCell(new Label(i, 0, columns[i], wcf));
                }

                if ((objData != null) && (objData.size() > 0)) {
                    for (int i = 0; i < objData.size(); i++) {
                        Map map = (Map) objData.get(i);

                        for (int j = 0; j < heads.length; j++) {
                            ws.addCell(new Label(j, i + 1,
                                    String.valueOf(map.get(heads[j]) == null ? "" : map.get(heads[j]))));
                        }
                    }
                }

                wwb.write();

                wwb.close();

                os.flush();
                os.close();
                os = null;
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
