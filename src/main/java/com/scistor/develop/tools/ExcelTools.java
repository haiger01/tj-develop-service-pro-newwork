package com.scistor.develop.tools;

import jxl.*;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class ExcelTools {

    private static DecimalFormat decimalFormat = new DecimalFormat("###################.###########");

    public static String FONT_FAMILY = "宋体";
    public static int HEAD_FONT = 10;
    public static int TITLE_FONT = 16;
    public static int KEY_FONT = 10;
    public static int TEXT_FONT = 10;
    public final static Map<String, Integer> EXPORT_CLAZZ_TYPE = new HashMap() {{
        this.put("java.lang.Double", 1);
        this.put("java.lang.Long", 1);
        this.put("java.lang.Integer", 1);
        this.put("java.lang.BigDecimal", 1);
        this.put("java.math.BigDecimal", 1);
        this.put("long", 1);
        this.put("int", 1);
        this.put("float", 1);
    }};

    public static <T> void exportexcle(HttpServletResponse response, String fileName, List<T> listData,
                                       String sheetName, String[] columns, String[] heads) {
        try {
            response.setHeader("Content-disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");

            response.setContentType("application/x-excel");
            exportToExcel(response, (List<Object>) listData, sheetName, columns, heads);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static <T> void exportToExcel(HttpServletResponse response, List<Object> objData, String sheetName,
                                         String[] columns, String[] heads) {
        try {

            createExcle(objData, sheetName, columns, heads, response.getOutputStream());

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void createExcle(List<Object> objData, String sheetName,
                                   String[] columns, String[] heads, File file) throws Exception {

        WritableWorkbook wwb = jxl.Workbook.createWorkbook(file);

        WritableSheet ws = wwb.createSheet(sheetName, 0);

        SheetSettings ss = ws.getSettings();
        ss.setVerticalFreeze(1);

        WritableFont font1 = new WritableFont(WritableFont.createFont("微软雅黑"), 10, WritableFont.BOLD);
        WritableFont font2 = new WritableFont(WritableFont.createFont("微软雅黑"), 9, WritableFont.NO_BOLD);
        WritableCellFormat wcf = new WritableCellFormat(font1);
        WritableCellFormat wcf2 = new WritableCellFormat(font2, NumberFormats.TEXT);

        wcf.setBackground(Colour.SKY_BLUE);
        wcf.setAlignment(Alignment.CENTRE);
        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
        wcf2.setAlignment(Alignment.CENTRE);
        wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);

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
        }
    }

    public static void fileDownload(OutputStream os, String path) throws Exception {
        InputStream in = new FileInputStream(path);
        int len = 0;
        byte buffert[] = new byte[1024];
        while ((len = in.read(buffert)) != -1) {
            os.write(buffert, 0, len);
        }
        os.flush();
        os.close();
    }

    @SuppressWarnings("resource")
    public static <T> void createExcle(List<T> objData, String sheetName,
                                       String[] columns, String[] heads, OutputStream os) {
        int sheetNameCount = 1; //sheet名称记录
        int sheetIndex = 0;   //记录工作薄sheet次数
        int maxRow = 35536; //最大列条数65536
        try {
            WritableWorkbook wwb = jxl.Workbook.createWorkbook(os);
            WritableSheet ws = wwb.createSheet(sheetName + "_" + sheetNameCount, sheetIndex);
            SheetSettings ss = ws.getSettings();
            ss.setVerticalFreeze(1);

            WritableFont font1 = new WritableFont(WritableFont.createFont("微软雅黑"), 10, WritableFont.BOLD);
            WritableFont font2 = new WritableFont(WritableFont.createFont("微软雅黑"), 9, WritableFont.NO_BOLD);
            WritableCellFormat wcf = new WritableCellFormat(font1);
            WritableCellFormat wcf2 = new WritableCellFormat(font2, NumberFormats.TEXT);

            wcf.setBackground(Colour.SKY_BLUE);
            wcf.setAlignment(Alignment.CENTRE);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf2.setAlignment(Alignment.CENTRE);
            wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);

            wcf.setAlignment(Alignment.CENTRE);

            if ((columns != null) && (columns.length > 0)) {
                for (int i = 0; i < columns.length; i++) {
                    CellView view = new CellView();
                    view.setAutosize(true);
                    ws.setColumnView(i, 20);
                    ws.addCell(new Label(i, 0, columns[i], wcf));
                }

                if ((objData != null) && (objData.size() > 0)) {
                    int row = 0;
                    for (int i = 0; i < objData.size(); i++) {
                        row++;
                        if (row > maxRow) { //当数据条数大于6w时，将数据分发到下一个工作薄，以此类推。
                            sheetNameCount++;
                            sheetIndex++;
                            ws = wwb.createSheet(sheetName + "_" + sheetNameCount, sheetIndex);
                            ss = ws.getSettings();
                            ss.setVerticalFreeze(1);

                            font1 = new WritableFont(WritableFont.createFont("微软雅黑"), 10, WritableFont.BOLD);
                            font2 = new WritableFont(WritableFont.createFont("微软雅黑"), 9, WritableFont.NO_BOLD);
                            wcf = new WritableCellFormat(font1);
                            wcf2 = new WritableCellFormat(font2, NumberFormats.TEXT);

                            wcf.setBackground(Colour.SKY_BLUE);
                            wcf.setAlignment(Alignment.CENTRE);
                            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
                            wcf2.setAlignment(Alignment.CENTRE);
                            wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);

                            wcf.setAlignment(Alignment.CENTRE);

                            for (int ii = 0; ii < columns.length; ii++) {
                                CellView view = new CellView();
                                view.setAutosize(true);
                                ws.setColumnView(ii, 20);
                                ws.addCell(new Label(ii, 0, columns[ii], wcf));
                            }
                            row = 1;
                        }
                        Map map = (Map) objData.get(i);
                        for (int j = 0; j < heads.length; j++) {
                            try {
                                if (map.get(heads[j]) != null && EXPORT_CLAZZ_TYPE.get(map.get(heads[j]).getClass().getName()) != null) {
                                    ws.addCell(new jxl.write.Number(j, row, (Double.valueOf(map.get(heads[j]) + ""))));
                                } else {
                                    ws.addCell(new Label(j, row,
                                            String.valueOf(map.get(heads[j]) == null ? "" : map.get(heads[j]))));
                                }
                            } catch (Exception e) {
                                ws.addCell(new Label(j, row,
                                        String.valueOf(map.get(heads[j]) == null ? "" : map.get(heads[j]))));
                            }
                        }
                    }
                }
                wwb.write();
                wwb.close();
                os.flush();
                os.close();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            //尝试关闭一个远程提示报错
            //ex.printStackTrace();
        }
    }


    public static List<List<List<String>>> readExcel(File file) throws BiffException, IOException {
        jxl.Workbook workBook = jxl.Workbook.getWorkbook(file);

        if (workBook == null) {
            return null;
        }
        return getDataInWorkbook(workBook);
    }

    private static List<List<List<String>>> getDataInWorkbook(jxl.Workbook workBook) {
        Sheet[] sheet = workBook.getSheets();

        List dataList = new ArrayList();

        if ((sheet != null) && (sheet.length > 0)) {
            for (int i = 0; i < sheet.length; i++) {
                List rowList = new ArrayList();

                int rowNum = sheet[i].getRows();
                int colNum = sheet[i].getColumns();
                for (int j = 0; j < rowNum; j++) {
                    Cell[] cells = sheet[i].getRow(j);
                    if ((cells != null) && (cells.length > 0)) {
                        List cellList = new ArrayList();

                        for (int k = 0; k < colNum; k++) {
                            Cell cell = sheet[i].getCell(k, j);
                            String cellValue = "";

                            if (cell.getType() == CellType.NUMBER) {
                                NumberCell numberCell = (NumberCell) cell;
                                double value = numberCell.getValue();
                                cellValue = decimalFormat.format(value);
                            } else if ((cell.getType() == CellType.NUMBER_FORMULA)
                                    || (cell.getType() == CellType.STRING_FORMULA)
                                    || (cell.getType() == CellType.BOOLEAN_FORMULA)
                                    || (cell.getType() == CellType.DATE_FORMULA)
                                    || (cell.getType() == CellType.FORMULA_ERROR)) {
                                FormulaCell nfc = (FormulaCell) cell;
                                cellValue = nfc.getContents();
                            } else {
                                cellValue = cell.getContents();

                                cellValue = excelCharaterDeal(cellValue);
                            }

                            cellList.add(cellValue.trim());
                        }
                        rowList.add(cellList);
                    }
                }
                dataList.add(rowList);
            }
        }

        workBook.close();

        return dataList;
    }

    private static String excelCharaterDeal(String str) {
        String[] val = {"-", "_", "/" };
        for (String i : val) {
            str = toToken(str, i);
        }
        return str;
    }

    private static String toToken(String s, String val) {
        if ((s == null) || (s.trim().equals(""))) {
            return s;
        }
        if ((val == null) || (val.equals(""))) {
            return s;
        }
        StringBuffer stringBuffer = new StringBuffer();
        String[] result = s.split(val);
        for (int x = 0; x < result.length; x++) {
            stringBuffer.append(" ").append(result[x]);
        }
        return stringBuffer.toString();
    }

    public String[] readExcelTitle(File file) throws Exception {
        InputStream is = new FileInputStream(file);
        HSSFWorkbook wb = new HSSFWorkbook(is);
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(0);

        int colNum = row.getPhysicalNumberOfCells();
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            title[i] = getCellFormatValue(row.getCell(i));
        }
        return title;
    }

    private static String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case 0:
                    cellvalue = decimalFormat.format(cell.getNumericCellValue());
                    break;
                case 2:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);
                        break;
                    }
                    FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                    CellValue cellValue = evaluator.evaluate(cell);
                    switch (cellValue.getCellType()) {
                        case 4:
                            cellvalue = String.valueOf(cellValue.getBooleanValue());
                            break;
                        case 0:
                            cellvalue = decimalFormat.format(cellValue.getNumberValue());
                            break;
                        case 1:
                            cellvalue = String.valueOf(cellValue.getStringValue());
                            break;
                        case 3:
                            break;
                        case 2:
                        case 5:
                    }

                    break;
                case 1:
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:
                    cellvalue = "";

                    break;
            }
        } else
            cellvalue = "";

        return cellvalue;
    }


}