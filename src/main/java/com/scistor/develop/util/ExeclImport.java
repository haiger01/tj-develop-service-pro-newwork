package com.scistor.develop.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExeclImport {

    /**

     * 查询指定目录中电子表格中所有的数据
     * @param file
     * 文件完整路径
     * @return
     */
    public static List<Map> getAllByExcel(MultipartFile file, String createrCompanyId, String createrUser, String createrUsercode) {
        //文件不能为空
        if (file == null) return null;

        String fileName = file.getName();
        String extString = fileName.substring(fileName.lastIndexOf("."));
        InputStream is = null;
        List<Map> list = new ArrayList<Map>();
        try {
            Workbook rwb = null;
            //Workbook rwb = Workbook.getWorkbook(file.getInputStream());
            is = file.getInputStream();
            if(".xls".equals(extString)){
                rwb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                rwb = new XSSFWorkbook(is);
            }

            //Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
            Sheet rs = rwb.getSheetAt(0);

            int clos = rs.getRow(0).getPhysicalNumberOfCells();// 得到所有的列
            int rows = rs.getPhysicalNumberOfRows();// 得到所有的行

            System.out.println(clos + " rows:" + rows);
            for (int i = 1; i < rows; i++) {

                String companyName = rs.getRow(i).getCell(0).getRichStringCellValue().getString();
                String companyContacts = rs.getRow(i).getCell(1).getRichStringCellValue().getString();
                String companyContactsPhone = rs.getRow(i).getCell(2).getRichStringCellValue().getString();
                String specialistName = rs.getRow(i).getCell(3).getRichStringCellValue().getString();
                String specialistContactsPhone = rs.getRow(i).getCell(4).getRichStringCellValue().getString();

                //防止空数据被读取写入
                if(StringUtils.isEmpty(companyName) || StringUtils.isEmpty(specialistName)) continue;

                Map map = new HashMap();

                map.put("createrCompanyId",createrCompanyId);
                map.put("createrCompanyName",createrCompanyId);
                map.put("createrUser",createrUser);
                map.put("createrUsercode",createrUsercode);

                map.put("createrCompanyType","4");

                map.put("companyName",companyName);
                map.put("companyContacts",companyContacts);
                map.put("companyContactsPhone",companyContactsPhone);
                map.put("specialistName",specialistName);
                map.put("specialistContactsPhone",specialistContactsPhone);
                list.add(map);


                /*for (int j = 0; j < clos; j++) {

                    String companyName = rs.getCell(j++, i).getContents();
                    String companyContacts = rs.getCell(j++, i).getContents();
                    String companyContactsPhone = rs.getCell(j++, i).getContents();
                    String specialistName = rs.getCell(j++, i).getContents();
                    String specialistContactsPhone = rs.getCell(j++, i).getContents();

                    //防止空数据被读取写入
                    if(StringUtils.isEmpty(companyName) || StringUtils.isEmpty(specialistName)) continue;

                    Map map = new HashMap();

                    map.put("createrCompanyId",createrCompanyId);
                    map.put("createrCompanyName",createrCompanyId);
                    map.put("createrUser",createrUser);
                    map.put("createrUsercode",createrUsercode);

                    map.put("createrCompanyType","4");

                    map.put("companyName",companyName);
                    map.put("companyContacts",companyContacts);
                    map.put("companyContactsPhone",companyContactsPhone);
                    map.put("specialistName",specialistName);
                    map.put("specialistContactsPhone",specialistContactsPhone);
                    list.add(map);
                }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }


    public static void main(String[] args) throws Exception{
        //F---转---M
        File file = new File("C:\\Users\\86130\\Desktop\\项目+团队名录.xls");
        FileInputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(inputStream));

        List<Map> list = getAllByExcel(multipartFile,"金融01","jr01","071eee836c87493bb3a89913149e2824");
        System.out.println(list);
    }
}
