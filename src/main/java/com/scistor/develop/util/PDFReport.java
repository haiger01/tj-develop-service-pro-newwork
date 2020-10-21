package com.scistor.develop.util;

import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;
import com.scistor.develop.data.entity.*;
import com.scistor.develop.util.authentication.Constants;

/**
 * 生成PDF方法
 * @author jxl
 *
 */
public class PDFReport{ 
    Document document = new Document();// 建立一个Document对象     
	PdfWriter pdfWriter = null;
    static DecimalFormat decimalFormat = new DecimalFormat("#.####");//格式化设置

	static Map<String,String> numberMap = new HashMap<>();
	static {
		numberMap.put("1","一");
		numberMap.put("2","二");
		numberMap.put("3","三");
		numberMap.put("4","四");
		numberMap.put("5","五");
		numberMap.put("6","六");
		numberMap.put("7","七");
		numberMap.put("8","八");
		numberMap.put("9","九");
	}

    
    
    private static Font headFont;		// 设置总标题文字大小
    private static Font titleFont;		// 设置每一栏的小标题
    private static Font keyFont;		// 设置标题文字大小
    private static Font textFont;		// 设置内容文字大小
	private static Font minFont;		// 设置内容文字大小
   
       
	static{ 
		BaseFont bfChinese; 
		try {    
			bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			headFont = new Font(bfChinese, 22, Font.BOLD,new BaseColor(1,1,1));		// 设置字体大小-黑色
        	titleFont = new Font(bfChinese, 18, Font.BOLD,new BaseColor(1,1,1));		// 设置字体大小-黑色
        	keyFont = new Font(bfChinese, 13, Font.NORMAL,new BaseColor(1,1,1));		// 设置字体大小-普通-黑色 
        	textFont = new Font(bfChinese, 13,Font.NORMAL,new BaseColor(1,1,1));		// 设置字体大小-普通-黑色
			minFont = new Font(bfChinese, 8,Font.NORMAL,new BaseColor(1,1,1));		// 设置字体大小-普通-黑色
		} catch (Exception e) {
            e.printStackTrace(); 
        }  
    } 
	
	public PDFReport() { 
		
	}
       
    /**
     * 设置页面大小和返回路径   
     * @param file
     */
	public PDFReport(File file) {
		//实现A4纸页面 并且横向显示（不设置则为纵向）
		document = new Document(PageSize.A4.rotate());
		//ocument.setPageSize(PageSize.A4);// 设置页面大小
         try {
			 pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));
			 // 打开文档
            document.open();
        } catch (Exception e) { 
            e.printStackTrace(); 
        }  
    } 
    int maxWidth = 520; 
       
    /**
     * 添加一行   
     * @param value		内容
     * @param font		文字样式
     * @param align		文字所在位置
     * @return
     */
	public PdfPCell createCell(String value,Font font,int align){ 
		PdfPCell cell = new PdfPCell(); 
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);         
		cell.setHorizontalAlignment(align);     
		cell.setPhrase(new Phrase(value,font)); 
		return cell; 
    } 
	
	/**
	 * 添加一行  
	 * @param value	内容
	 * @param font	文字样式
	 * @return
	 */
	public PdfPCell createCell(String value,Font font){ 
		PdfPCell cell = new PdfPCell(); 
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
		cell.setPhrase(new Phrase(value,font)); 
		return cell; 
    } 
   
	/**
	 * 添加一行  
	 * @param value		内容
	 * @param font		文字大小
	 * @param align		文字所在位置
	 * @param colspan	占多少位
	 * @return
	 */
	public PdfPCell createCell(String value,Font font,int align,int colspan){ 
		PdfPCell cell = new PdfPCell(); 
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
		cell.setHorizontalAlignment(align);     
		cell.setColspan(colspan); 
		cell.setPhrase(new Phrase(value,font)); 
        return cell; 
    } 
	
	/**
	 * 没有设置行高的（由字体大小来设置）
	 * @param value			内容
	 * @param font			文字样式
	 * @param align			文字所在位置
	 * @param colspan		占多少位
	 * @param boderFlag		是否有边框
	 * @return
	 */
	public PdfPCell createCellNotHeight(String value,Font font,int align,int colspan,boolean boderFlag){ 
		PdfPCell cell = new PdfPCell(); 
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
		cell.setHorizontalAlignment(align);     
		cell.setColspan(colspan); 
		cell.setPhrase(new Phrase(value,font)); 		
		cell.setPadding(3.0f); 
		cell.setBorderWidth(0.1f);			
		cell.setBorderColor(new BaseColor(159, 159, 159));//设置边框颜色
		if(!boderFlag){ 
			cell.setBorder(0); 
			cell.setPaddingTop(15.0f); 
			cell.setPaddingBottom(8.0f); 
		}
		return cell; 
    } 
	

	/**
	 * 添加一行  
	 * @param value			内容
	 * @param font			文字样式
	 * @param align			文字所在位置
	 * @param colspan		占多少位
	 * @param boderFlag		是否有边框
	 * @return
	 */
	public PdfPCell createCell(String value,Font font,int align,int colspan,boolean boderFlag){ 
		PdfPCell cell = new PdfPCell(); 
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
		cell.setHorizontalAlignment(align);     
		cell.setColspan(colspan); 
		cell.setPhrase(new Phrase(value,font)); 		
		cell.setPadding(3.0f); 
		cell.setBorderWidth(0.1f);			
		cell.setBorderColor(new BaseColor(159, 159, 159));//设置边框颜色
		cell.setMinimumHeight(35.0f);		//行高
		//cell.setFixedHeight(35.0f);			//行高
		if(!boderFlag){ 
			cell.setBorder(0); 
			cell.setPaddingTop(15.0f); 
			cell.setPaddingBottom(8.0f); 
		}
		return cell; 
    } 
	
	/**
	 * 添加一行  
	 * @param value			内容
	 * @param font			文字样式
	 * @param align			文字所在位置
	 * @param colspan		占多少位
	 * @param boderFlag		是否有边框
	 * @param BackgroundFlag 是否有背景颜色
	 * @return
	 */
	public PdfPCell createCell(String value,Font font,int align,int colspan,boolean boderFlag,boolean BackgroundFlag){ 
		PdfPCell cell = new PdfPCell(); 
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
		cell.setHorizontalAlignment(align);     
		cell.setColspan(colspan); 
		cell.setPhrase(new Phrase(value,font)); 
		cell.setPadding(3.0f);
		cell.setMinimumHeight(35.0f);		//行高
		//cell.setFixedHeight(35.0f);			//行高
		cell.setBorderColor(new BaseColor(159, 159, 159));//设置边框颜色
		if(!boderFlag){ 
			cell.setBorder(0); 
			cell.setPaddingTop(15.0f); 
			cell.setPaddingBottom(8.0f); 
		}
		if (BackgroundFlag){
			cell.setBackgroundColor(new BaseColor(241, 241, 241));//设置背景颜色
		}
		return cell; 
    }
	
	/**
	 * 创建一个表
	 * @param colNumber
	 * @return
	 */
	public PdfPTable createTable(int colNumber){ 
		PdfPTable table = new PdfPTable(colNumber); 
		try{ 
			table.setTotalWidth(maxWidth); 
            table.setLockedWidth(true); 
            table.setHorizontalAlignment(Element.ALIGN_CENTER);      
            table.getDefaultCell().setBorder(1); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 
        return table; 
    } 
	
	/**
	 * 创建一个表
	 * @param widths
	 * @return
	 */
	public PdfPTable createTable(float[] widths){ 
		PdfPTable table = new PdfPTable(widths); 
		try{ 
			table.setTotalWidth(maxWidth); 
			table.setLockedWidth(true); 
			table.setHorizontalAlignment(Element.ALIGN_CENTER);      
			table.getDefaultCell().setBorder(1); 
		}catch(Exception e){ 
			e.printStackTrace(); 
		} 
		return table; 
	} 
       
     public PdfPTable createBlankTable(){ 
         PdfPTable table = new PdfPTable(1); 
         table.getDefaultCell().setBorder(0); 
         table.addCell(createCell("", keyFont));          
         table.setSpacingAfter(20.0f); 
         table.setSpacingBefore(20.0f); 
         return table; 
     }
     
     
     
     /**
      * 导出机构档案的运营数据
      * @param response
      * @param map
      */
     public void exportPDF(HttpServletResponse response,Map<String,Object> map){
    	 try {
			 String fileName = "天津市地方企业评价报告";
    		 /*SysCompany company = (SysCompany) map.get("company");
    		 String fileName = "档案";
    		 if (StringUtil.isNotEmpty(company.getPlatformName())){
    			 fileName = company.getPlatformName() + "档案";
    		 }else if (StringUtil.isNotEmpty(company.getCompanyName())){
    			 fileName = company.getCompanyName() + "档案"; 
    		 }*/
    		 response.reset();
        	 response.setContentType("application/pdf;charset=utf-8");
        	 response.setHeader("Content-Disposition","attachment;filename=" + new String((fileName + "_" + new SimpleDateFormat("yyyyMMdd").format(new Date())).getBytes("gb2312"), "ISO8859-1") + ".pdf");
        	 OutputStream out = response.getOutputStream();

			 document = new Document(PageSize.A4.rotate());
			 PdfWriter writer = PdfWriter.getInstance(document,out);
			 //添加打印信息
			 writer.setPageEvent(new SdkPdfPageEvent());
			 // 打开文档
			 document.open();
			 //读取一个图片
			 //Image image = Image.getInstance("D:\\gz.jpg");

			 //插入一个图片
			 //document.add(image);
             generateCompanyPDF(map,fileName);
             out.close();


    	 }catch (Exception e){
    		 e.printStackTrace();
    	 }
     }


    public String saveExportPDF(Map<String,Object> map,String filePath,String imgPath){
        try {
            String fileName = "";
            filePath = filePath + fileName;
            File file = new File(filePath);


    		 /*SysCompany company = (SysCompany) map.get("company");
    		 String fileName = "档案";
    		 if (StringUtil.isNotEmpty(company.getPlatformName())){
    			 fileName = company.getPlatformName() + "档案";
    		 }else if (StringUtil.isNotEmpty(company.getCompanyName())){
    			 fileName = company.getCompanyName() + "档案";
    		 }*/
            /*response.reset();
            response.setContentType("application/pdf;charset=utf-8");
            response.setHeader("Content-Disposition","attachment;filename=" + new String((fileName + "_" + new SimpleDateFormat("yyyyMMdd").format(new Date())).getBytes("gb2312"), "ISO8859-1") + ".pdf");
            OutputStream out = response.getOutputStream();*/
            //FileOutputStream fileSteam =new FileOutPutStream(file);

            FileOutputStream fileSteam = new FileOutputStream(file);

            document = new Document(PageSize.A4.rotate());
            PdfWriter writer = PdfWriter.getInstance(document,fileSteam);
            //添加打印信息
            writer.setPageEvent(new SdkPdfPageEvent());
            // 打开文档
            document.open();
            //读取一个图片
            Image image = Image.getInstance(imgPath);

            //插入一个图片
            document.add(image);
            generateCompanyPDF(map,fileName);
            fileSteam.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return filePath;
    }
     
     /**
      * 
      * @throws Exception
      */
	public void generateCompanyPDF(Map<String,Object> map,String name) throws Exception{
		if (null == map || map.size() < 1 ) return ;

		PdfPTable table = createTable(12);
		//总标题
		table.addCell(createCellNotHeight("天津市地方企业评价报告", headFont,Element.ALIGN_CENTER,12,false));
		table.addCell(createCellNotHeight("（本报告中数据均为模拟样例数据）", minFont,Element.ALIGN_CENTER,12,false));



		ZwCompany company = (ZwCompany) map.get("company");
		table = getTemplateReport(table, company);
		//一、基本信息
		table.addCell(createCellNotHeight("一、企业基本信息（企业填报）",titleFont,Element.ALIGN_LEFT,12,false));
		table = getTemplateCompanyInfo(table, company);

		//二、工商信息
		table.addCell(createCellNotHeight("二、工商信息",titleFont,Element.ALIGN_LEFT,12,false));
		table = getTemplateBusiness(table,map);


         //三、市场监管委信息
         table.addCell(createCellNotHeight("三、市场监管委信息",titleFont,Element.ALIGN_LEFT,12,false));
		 table.addCell(createCellNotHeight("(一)企业人员信息",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateComPersonnel(table,map);
		 table.addCell(createCellNotHeight("(二)股东信息",titleFont,Element.ALIGN_LEFT,12,false));
		 table.addCell(createCellNotHeight("1)股东出资记录",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateComShareholderContribute(table,map);
		 table.addCell(createCellNotHeight("2)股东变更信息",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateComShareholderAlter(table,map);
		 table.addCell(createCellNotHeight("(三)企业年报",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateComYearSubmits(table,map);


		 table.addCell(createCellNotHeight("四、社保(五险)、社会保险信息",titleFont,Element.ALIGN_LEFT,12,false));
		 table.addCell(createCellNotHeight("(一)社保信息(五险)",titleFont,Element.ALIGN_LEFT,12,false));
		 table.addCell(createCellNotHeight("1)养老保险(近三年)",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateStaffCultivate(table,map);
		 table.addCell(createCellNotHeight("2)失业保险（近三年）",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateStaffUnemployment(table,map);
		 table.addCell(createCellNotHeight("3)医疗保险（近三年）",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateStaffMedical(table,map);
		 table.addCell(createCellNotHeight("4)工伤保险（近三年）",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateStaffHarm(table,map);
		 table.addCell(createCellNotHeight("5)生育保险（近三年）",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateStaffBirth(table,map);
		 table.addCell(createCellNotHeight("(二)社会保险信息",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateSocietyInsuranceInfo(table,map);

		 table.addCell(createCellNotHeight("五、“两定机构”医保结算信息（接入中）",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateSettlementInfo(table,map);
		 table.addCell(createCellNotHeight("",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateSettlementList(table,map);

		 table.addCell(createCellNotHeight("六、用水信息",titleFont,Element.ALIGN_LEFT,12,false));
		 table.addCell(createCellNotHeight("(一)企业用水信息（近三年）",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateWaterInfo(table,map);
		 table.addCell(createCellNotHeight("(二)企业用水欠费信息（近三年）",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateWaterArrears(table,map);

		 table.addCell(createCellNotHeight("七、政采信息",titleFont,Element.ALIGN_LEFT,12,false));
		 table.addCell(createCellNotHeight("(一)单一来源",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateGovernSource(table,map);
		 table.addCell(createCellNotHeight("(二)合同公告",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateGovernCommon(table,map);
		 table.addCell(createCellNotHeight("(三)采购结果公告",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateGovernBuy(table,map);
		 table.addCell(createCellNotHeight("(四)验收信息",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateGovernCheck(table,map);

		 table.addCell(createCellNotHeight("八、消防处罚信息(接入中)",titleFont,Element.ALIGN_LEFT,12,false));

		 table.addCell(createCellNotHeight("九、未结清小贷信息",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateLoans(table,map);

		 table.addCell(createCellNotHeight("十、高院信息",titleFont,Element.ALIGN_LEFT,12,false));
		 table.addCell(createCellNotHeight("(一)高院失信企业信息",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateCourtLoseCompany(table,map);
		 table.addCell(createCellNotHeight("(二)高院失信个人信息",titleFont,Element.ALIGN_LEFT,12,false));
		 table.addCell(createCellNotHeight("1) 企业法定代表人违法信息（立案）",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateCourtLosePersonBegin(table,map);
		 table.addCell(createCellNotHeight("2) 企业法定代表人违法信息（结案）",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateCourtLosePersonEnd(table,map);
		 table.addCell(createCellNotHeight("(三)高院结案信息",titleFont,Element.ALIGN_LEFT,12,false));
		 table = getTemplateCourtSettle(table,map);

		 table.addCell(createCellNotHeight("十一、重要项目列入情况",titleFont,Element.ALIGN_LEFT,12,false));

		 //
		table.addCell(createCell("序号",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("项目",keyFont,Element.ALIGN_CENTER,8,true));
		table.addCell(createCell("是否列入",keyFont,Element.ALIGN_CENTER,2,true));
		for (int i = 0 ; i < 9 ; i++) {
			int j = i + 1;
			table.addCell(createCell( j+"" ,textFont,Element.ALIGN_CENTER,2,true));
			table.addCell(createCell( Constants.enrolName.getDescription(j+"") ,textFont,Element.ALIGN_CENTER,8,true));
			Object o = map.get( "enrolList"+j);
			if (null != o) {
				table.addCell(createCell( "是" ,textFont,Element.ALIGN_CENTER,2,true));
			}else{
				table.addCell(createCell( "否" ,textFont,Element.ALIGN_CENTER,2,true));
			}
		}

		int number = 1;
		for (int i = 0 ; i < 9 ; i++) {
			int j = i + 1;
			Object o = map.get( "enrolList"+j);
			if (null != o) {
				table.addCell(createCellNotHeight("("+numberMap.get(number+"")+")"+Constants.enrolName.getDescription(j+""),titleFont,Element.ALIGN_LEFT,12,false));
				if ("1".equals(j+"")) getTemplateEnrolList1(table,map);
				if ("2".equals(j+"")) getTemplateEnrolList2(table,map);
				if ("3".equals(j+"")) getTemplateEnrolList3(table,map);
				if ("4".equals(j+"")) getTemplateEnrolList4(table,map);
				if ("5".equals(j+"")) getTemplateEnrolList5(table,map);
				if ("6".equals(j+"")) getTemplateEnrolList6(table,map);
				if ("7".equals(j+"")) getTemplateEnrolList7(table,map);
				if ("8".equals(j+"")) getTemplateEnrolList8(table,map);
				if ("9".equals(j+"")) getTemplateEnrolList9(table,map);
				number ++;
			}
		}


		/*table.addCell(createCellNotHeight("(一)天津市“专精特新”中小企业培育",titleFont,Element.ALIGN_LEFT,12,false));
		table = getTemplateEnrolList1(table,map);
		table.addCell(createCellNotHeight("(二)天津市自然科学基金项目",titleFont,Element.ALIGN_LEFT,12,false));
		table = getTemplateEnrolList2(table,map);
		table.addCell(createCellNotHeight("(三)国家科技型中小企业名录",titleFont,Element.ALIGN_LEFT,12,false));
		table = getTemplateEnrolList3(table,map);
		table.addCell(createCellNotHeight("(四)天津市中小企业“专精特新”产品（接入中）",titleFont,Element.ALIGN_LEFT,12,false));
		table = getTemplateEnrolList4(table,map);
		table.addCell(createCellNotHeight("(五)天津市高新技术企业名单",titleFont,Element.ALIGN_LEFT,12,false));
		table = getTemplateEnrolList5(table,map);
		table.addCell(createCellNotHeight("(六)天津市自然科学基金资助项目",titleFont,Element.ALIGN_LEFT,12,false));
		table = getTemplateEnrolList6(table,map);
		table.addCell(createCellNotHeight("(七)天津市技术先进型服务企业名单",titleFont,Element.ALIGN_LEFT,12,false));
		table = getTemplateEnrolList7(table,map);
		table.addCell(createCellNotHeight("(八)国家重点研发计划项目",titleFont,Element.ALIGN_LEFT,12,false));
		table = getTemplateEnrolList8(table,map);
		table.addCell(createCellNotHeight("(九)天津市龙头企业基本信息",titleFont,Element.ALIGN_LEFT,12,false));
		table = getTemplateEnrolList9(table,map);*/

		table.addCell(createCellNotHeight("十二、专利信息",titleFont,Element.ALIGN_LEFT,12,false));
		table = getTemplatePatents(table,map);

         document.add(table);

         //ddddd
         //ddddd



         document.close(); 
	}

	/**
	 * 一、一、企业基本信息(企业填报)
	 * @param table
	 * @param sub
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateReport(PdfPTable table, ZwCompany sub) {
		//table.addCell(createCell("报告编号",keyFont,Element.ALIGN_LEFT,3,true,true));
		table.addCell(createCell("报告编号",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-",textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getReportNumber() ,textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("报告日期",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getReportDate() ,textFont,Element.ALIGN_LEFT,3,true));

		//table.addCell(createCell("查询机构",keyFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("查询机构",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-",textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getCheckName() ,textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("经办人",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getHandleName() ,textFont,Element.ALIGN_LEFT,3,true));


		return table;
	}

	/**
	 * 一、一、企业基本信息(企业填报)
	 * @param table
	 * @param sub
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateCompanyInfo(PdfPTable table, ZwCompany sub) {

		//table.addCell(createCell("企业名称",keyFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("企业名称",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-",textFont,Element.ALIGN_LEFT,9,true));
		else table.addCell(createCell( sub.getCompanyName() ,textFont,Element.ALIGN_LEFT,9,true));

		table.addCell(createCell("注册地址",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,9,true));
		else table.addCell(createCell( sub.getRegisterAddress() ,textFont,Element.ALIGN_LEFT,9,true));

		table.addCell(createCell("实际经营地址",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,9,true));
		else table.addCell(createCell( sub.getRealityAddress() ,textFont,Element.ALIGN_LEFT,9,true));

		table.addCell(createCell("统一社会信用代码",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getOrgNo() ,textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("组织机构代码",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getOrgCode() ,textFont,Element.ALIGN_LEFT,3,true));

		table.addCell(createCell("ICP备案号",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getIcpCode() ,textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("主要结算银行",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getSettleBank() ,textFont,Element.ALIGN_LEFT,3,true));

		table.addCell(createCell("第一联系人",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getLinkmanNo1() ,textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("第一联系人联系方式",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getLinkmanNo1Phone() ,textFont,Element.ALIGN_LEFT,3,true));

		table.addCell(createCell("第二联系人",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getLinkmanNo2() ,textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("第二联系人联系方式",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getLinkmanNo2Phone() ,textFont,Element.ALIGN_LEFT,3,true));

		table.addCell(createCell("主营业务",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,9,true));
		else table.addCell(createCell( sub.getBusiness() ,textFont,Element.ALIGN_LEFT,9,true));

		return table;
	}



	/**
	 * 二、工商信息
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateBusiness(PdfPTable table, Map<String,Object> map ) {
		if (null == map || map.size() < 1 ) return table;
		ZwScjgwEntinfo business = (ZwScjgwEntinfo) map.get("business");

		table.addCell(createCell("企业名称",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == business) table.addCell(createCell( "-",textFont,Element.ALIGN_LEFT,9,true));
		else table.addCell(createCell( business.getEntName(),textFont,Element.ALIGN_LEFT,9,true));

		table.addCell(createCell("统一社会信用代码",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == business)  table.addCell(createCell( "-",textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( business.getOrgNo(),textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("公司类型",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == business)  table.addCell(createCell( "-",textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( business.getEntType(),textFont,Element.ALIGN_LEFT,3,true));

		table.addCell(createCell("所属行业",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == business)  table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( business.getIndustryCo() ,textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("注册资本（万元）",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == business) table.addCell(createCell("",textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell(business.getRegCap() ,textFont,Element.ALIGN_LEFT,3,true));

		table.addCell(createCell("成立日期",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == business)  table.addCell(createCell(  "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell(  business.getEstDate() ,textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("法定代表人",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == business)  table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( business.getLerep() ,textFont,Element.ALIGN_LEFT,3,true));

		table.addCell(createCell("法人代表联系电话",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == business) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( business.getLerep_tel() ,textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("电话",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == business) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( business.getTel() ,textFont,Element.ALIGN_LEFT,3,true));

		table.addCell(createCell("登记机关",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == business) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( business.getRegOrg() ,textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("登记状态",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == business) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( business.getRegState() ,textFont,Element.ALIGN_LEFT,3,true));

		table.addCell(createCell("注册号",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == business) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( business.getRegNo() ,textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("住所所在行政区划",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == business) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( business.getDomDistrict() ,textFont,Element.ALIGN_LEFT,3,true));

		table.addCell(createCell("住所",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == business) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,9,true));
		else table.addCell(createCell( business.getDom() ,textFont,Element.ALIGN_LEFT,9,true));

		table.addCell(createCell("经营范围",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == business) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,9,true));
		else table.addCell(createCell( business.getOpScope() ,textFont,Element.ALIGN_LEFT,9,true));
		return table;
	}

	/**
	 * 三、市场监管委信息-(一)企业人员信息
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateComPersonnel(PdfPTable table, Map<String, Object> map)  {

		List<ZwScjgwPersoninfo> comPersonList = (List<ZwScjgwPersoninfo>) map.get("comPerson");

		table.addCell(createCell("序号",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("姓名",keyFont,Element.ALIGN_CENTER,5,true));
		table.addCell(createCell("职务",keyFont,Element.ALIGN_CENTER,5,true));

		if (null != comPersonList && comPersonList.size() > 0){
			for (int i = 0 ; i < comPersonList.size() ; i++) {
				ZwScjgwPersoninfo n = comPersonList.get(i);
				if (null == n) continue;
				int j = i+1;
				table.addCell(createCell( j+"" ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getName() ,textFont,Element.ALIGN_CENTER,5,true));
				table.addCell(createCell( n.getPosition() ,textFont,Element.ALIGN_CENTER,5,true));

			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 三、市场监管委信息-(二)股东信息-1)股东出资记录
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateComShareholderContribute(PdfPTable table, Map<String, Object> map) {

		List<ZwScjgwGdczinfo> comShareholderList = (List<ZwScjgwGdczinfo>) map.get("comShareholderContribute");
		table.addCell(createCell("序号",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("股东名称",keyFont,Element.ALIGN_CENTER,4,true));
		table.addCell(createCell("出资时间",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("出资方式",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("出资金额",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("股东类型",keyFont,Element.ALIGN_CENTER,1,true));

		if (null != comShareholderList && comShareholderList.size() > 0){
			for (int i = 0 ; i < comShareholderList.size() ; i++) {
				ZwScjgwGdczinfo n = comShareholderList.get(i);
				if (null == n) continue;
				int j = i+1;
				table.addCell(createCell( j+"" ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getInv() ,textFont,Element.ALIGN_CENTER,4,true));
				table.addCell(createCell( n.getConDate()  ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getConFrom()  ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getConNam()  ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getInvType()  ,textFont,Element.ALIGN_CENTER,1,true));

			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 三、市场监管委信息-(二)股东信息-2)股东变更信息
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateComShareholderAlter(PdfPTable table, Map<String, Object> map) {

		List<ZwScjgwGdbginfo> comShareholderList = (List<ZwScjgwGdbginfo>) map.get("comShareholderAlter");

		table.addCell(createCell("序号",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("股东名称",keyFont,Element.ALIGN_CENTER,4,true));
		table.addCell(createCell("变更前股权比例",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("变更后股权比例",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("股权变更日期",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("年报年度",keyFont,Element.ALIGN_CENTER,1,true));

		if (null != comShareholderList && comShareholderList.size() > 0){
			for (int i = 0 ; i < comShareholderList.size() ; i++) {
				ZwScjgwGdbginfo n = comShareholderList.get(i);
				if (null == n) continue;
				int j = i+1;
				table.addCell(createCell( j+"" ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getInv() ,textFont,Element.ALIGN_CENTER,4,true));
				table.addCell(createCell( n.getTransAmprBefore()  ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getTransAmprAfter()  ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getAltDate() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getAncheYear()  ,textFont,Element.ALIGN_CENTER,1,true));

			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 三、市场监管委信息-(三)企业年报
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateComYearSubmits(PdfPTable table, Map<String, Object> map) {
		if (null == map || map.size() < 1 ) return table;
		ZwScjgwNbinfo sub = (ZwScjgwNbinfo) map.get("companyYearSubmits");

		table.addCell(createCell("企业名称",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub ) table.addCell(createCell( "-",textFont,Element.ALIGN_LEFT,4,true));
		else table.addCell(createCell( sub.getEntName() ,textFont,Element.ALIGN_LEFT,4,true));
		table.addCell(createCell("电子邮箱",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,4,true));
		else table.addCell(createCell( sub.getEmail() ,textFont,Element.ALIGN_LEFT,4,true));

		table.addCell(createCell("企业通信地址",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,10,true));
		else table.addCell(createCell( sub.getAddr() ,textFont,Element.ALIGN_LEFT,10,true));

		table.addCell(createCell("所有者权益",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,2,true));
		else table.addCell(createCell( sub.getTotEqu() ,textFont,Element.ALIGN_LEFT,2,true));
		table.addCell(createCell("营业总收入（万元）",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,2,true));
		else table.addCell(createCell( sub.getVendInc() ,textFont,Element.ALIGN_LEFT,2,true));
		table.addCell(createCell("主营业务收入（万元）",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub ) table.addCell(createCell(  "-"  ,textFont,Element.ALIGN_LEFT,2,true));
		else table.addCell(createCell(  sub.getMaiBusInc()  ,textFont,Element.ALIGN_LEFT,2,true));

		table.addCell(createCell("资产总额（万元）",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,2,true));
		else table.addCell(createCell( sub.getAssGro() ,textFont,Element.ALIGN_LEFT,2,true));
		table.addCell(createCell("负债总额（万元）",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,2,true));
		else table.addCell(createCell( sub.getLiaGro() ,textFont,Element.ALIGN_LEFT,2,true));
		table.addCell(createCell("纳税总额（万元）",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub ) table.addCell(createCell( "-",textFont,Element.ALIGN_LEFT,2,true));
		else table.addCell(createCell( sub.getRatGro() ,textFont,Element.ALIGN_LEFT,2,true));

		table.addCell(createCell("净利润",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,2,true));
		else table.addCell(createCell( sub.getNetInc() ,textFont,Element.ALIGN_LEFT,2,true));
		table.addCell(createCell("利润总额",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,2,true));
		else table.addCell(createCell( sub.getProGro() ,textFont,Element.ALIGN_LEFT,2,true));
		table.addCell(createCell("年报年度",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub || null == sub.getAncheYear() || "".equals(sub.getAncheYear())  ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,2,true));
		else table.addCell(createCell( sub.getAncheYear()+"年" ,textFont,Element.ALIGN_LEFT,2,true));
		return table;
	}

	/**
	 * 四、社保（五险）、社会保险信息
	 * （一）社保信息（五险）
	 * 1） 养老保险（近三年）
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateStaffCultivate(PdfPTable table, Map<String, Object> map) {

		List<ZwShebaoInfo> comStaffList = (List<ZwShebaoInfo>) map.get("comStaffCultivate");

		table.addCell(createCell("养老缴费人数",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("养老缴费基数",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("养老缴费比例(%)",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("养老缴费金额(万元)",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("养老应缴未缴金额",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("结算期",keyFont,Element.ALIGN_CENTER,2,true));

		if (null != comStaffList && comStaffList.size() > 0){
			for (int i = 0 ; i < comStaffList.size() ; i++) {
				ZwShebaoInfo n = comStaffList.get(i);
				if (null == n) continue;
				if (null == n.getPenPayres()
						&& null == n.getPenPayBase()
						&& null == n.getPenPayRatio()
						&& null == n.getEntPayPenAmount()
						&& null == n.getEntMonUnpPenAmount()
					) continue;
				table.addCell(createCell(  n.getPenPayres()   ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell(  n.getPenPayBase()   ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell(  n.getPenPayRatio()   ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell(  n.getEntPayPenAmount()   ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell(  n.getEntMonUnpPenAmount()   ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getPeriod() ,textFont,Element.ALIGN_CENTER,2,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 四、社保（五险）、社会保险信息
	 * （一）社保信息（五险）
	 * 2)失业保险（近三年）
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateStaffUnemployment(PdfPTable table, Map<String, Object> map) {

		List<ZwShebaoInfo> comStaffList = (List<ZwShebaoInfo>) map.get("comStaffUnemployment");

		table.addCell(createCell("失业缴费人数",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("失业缴费基数",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("失业缴费比例(%)",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("失业缴费金额(万元)",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("失业应缴未缴金额",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("结算期",keyFont,Element.ALIGN_CENTER,2,true));

		if (null != comStaffList && comStaffList.size() > 0){
			for (int i = 0 ; i < comStaffList.size() ; i++) {
				ZwShebaoInfo n = comStaffList.get(i);
				if (null == n) continue;
				if (null == n.getUnePayers()
						&& null == n.getUnePayBase()
						&& null == n.getUnePayRatio()
						&& null == n.getEntPayUneAmount()
						&& null == n.getEntMonUnpUneAmount()
				) continue;
				table.addCell(createCell( n.getUnePayers()   ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getUnePayBase()   ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getUnePayRatio()   ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getEntPayUneAmount()   ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getEntMonUnpUneAmount()   ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getPeriod() ,textFont,Element.ALIGN_CENTER,2,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 四、社保（五险）、社会保险信息
	 * （一）社保信息（五险）
	 * 3)医疗保险（近三年）
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateStaffMedical(PdfPTable table, Map<String, Object> map) {

		List<ZwShebaoInfo> comStaffList = (List<ZwShebaoInfo>) map.get("comStaffMedical");

		table.addCell(createCell("医疗缴费人数",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("医疗缴费基数",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("医疗缴费比例(%)",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("医疗缴费金额(万元)",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("医疗应缴未缴金额",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("结算期",keyFont,Element.ALIGN_CENTER,2,true));

		if (null != comStaffList && comStaffList.size() > 0){
			for (int i = 0 ; i < comStaffList.size() ; i++) {
				ZwShebaoInfo n = comStaffList.get(i);
				if (null == n) continue;
				if (null == n.getMedPayers()
						&& null == n.getMedPayBase()
						&& null == n.getMedPayRatio()
						&& null == n.getMedInsPay()
						&& null == n.getMedInsArr()
				) continue;
				table.addCell(createCell( n.getMedPayers()  ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getMedPayBase()  ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getMedPayRatio()  ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell(n.getMedInsPay(),textFont,Element.ALIGN_LEFT,2,true));
				table.addCell(createCell(n.getMedInsArr(),textFont,Element.ALIGN_LEFT,2,true));
				table.addCell(createCell( n.getPeriod() ,textFont,Element.ALIGN_CENTER,2,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 四、社保（五险）、社会保险信息
	 * （一）社保信息（五险）
	 * 4)工伤保险（近三年）
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateStaffHarm(PdfPTable table, Map<String, Object> map) {

		List<ZwShebaoInfo> comStaffList = (List<ZwShebaoInfo>) map.get("comStaffHarm");

		table.addCell(createCell("工伤缴费人数",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("工伤缴费基数",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("工伤缴费比例(%)",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("工伤缴费金额(万元)",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("工伤应缴未缴金额",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("结算期",keyFont,Element.ALIGN_CENTER,2,true));

		if (null != comStaffList && comStaffList.size() > 0){
			for (int i = 0 ; i < comStaffList.size() ; i++) {
				ZwShebaoInfo n = comStaffList.get(i);
				if (null == n) continue;
				if (null == n.getWorkInjPayers()
						&& null == n.getWorkInjPayBase()
						&& null == n.getWorkInjPayRatio()
						&& null == n.getEntPayInjAmount()
						&& null == n.getEntMonUnpInjAmount()
				) continue;
				table.addCell(createCell( n.getWorkInjPayers()  ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getWorkInjPayBase()  ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getWorkInjPayRatio()  ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getEntPayInjAmount()  ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getEntMonUnpInjAmount()  ,textFont,Element.ALIGN_CENTER,2,true));

				table.addCell(createCell( n.getPeriod() ,textFont,Element.ALIGN_CENTER,2,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 四、社保（五险）、社会保险信息
	 * （一）社保信息（五险）
	 * 5)生育保险（近三年）
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateStaffBirth(PdfPTable table, Map<String, Object> map) {

		List<ZwShebaoInfo> comStaffList = (List<ZwShebaoInfo>) map.get("comStaffBirth");

		table.addCell(createCell("生育缴费人数",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("生育缴费基数",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("生育缴费比例(%)",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("生育缴费金额(万元)",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("生育应缴未缴金额",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("结算期",keyFont,Element.ALIGN_CENTER,2,true));

		if (null != comStaffList && comStaffList.size() > 0){
			for (int i = 0 ; i < comStaffList.size() ; i++) {
				ZwShebaoInfo n = comStaffList.get(i);
				if (null == n) continue;
				if (null == n.getMatPayers()
						&& null == n.getMatPayBase()
						&& null == n.getMatPayRatio()
						&& null == n.getBirInsPay()
						&& null == n.getBirInsArr()
				) continue;
				table.addCell(createCell( n.getMatPayers() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getMatPayBase() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getMatPayRatio() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getBirInsPay() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getBirInsArr() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getPeriod() ,textFont,Element.ALIGN_CENTER,2,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 四、社保（五险）、社会保险信息
	 * （二）社会保险信息
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateSocietyInsuranceInfo(PdfPTable table, Map<String, Object> map) {
		List<T3070020000013000030> comStaffList = (List<T3070020000013000030>) map.get("societyInsuranceInfo");

		table.addCell(createCell("参保缴费状态",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("行业风险类别",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("参加养老保险人数",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("参加失业保险人数",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("参加生育保险人数",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("参加医疗保险人数",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("参加工伤保险人数",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("参保起始日期",keyFont,Element.ALIGN_CENTER,1,true));



		if (null != comStaffList && comStaffList.size() > 0){
			for (int i = 0 ; i < comStaffList.size() ; i++) {
				T3070020000013000030 n = comStaffList.get(i);
				if (null == n) continue;
				table.addCell(createCell( n.getF_3070020000013_000030002() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getF_3070020000013_000030004() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getF_3070020000013_000030010() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getF_3070020000013_000030017() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000013_000030021() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000013_000030025() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000013_000030028() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000013_000030031() ,textFont,Element.ALIGN_CENTER,1,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}


	/**
	 * 五、“两定机构”医保结算信息
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateSettlementInfo(PdfPTable table, Map<String, Object> map) {
		if (null == map || map.size() < 1 ) return table;
		//ZwScjgwNbinfo sub = (ZwScjgwNbinfo) map.get("companyYearSubmits");

		table.addCell(createCell("企业名称",keyFont,Element.ALIGN_LEFT,4,true));
		table.addCell(createCell( "" ,textFont,Element.ALIGN_LEFT,8,true));

		table.addCell(createCell("证照类型",keyFont,Element.ALIGN_LEFT,4,true));
		table.addCell(createCell( "" ,textFont,Element.ALIGN_LEFT,8,true));

		table.addCell(createCell("证照编号",keyFont,Element.ALIGN_LEFT,4,true));
		table.addCell(createCell( "" ,textFont,Element.ALIGN_LEFT,8,true));

		table.addCell(createCell("药品经营许可证编号",keyFont,Element.ALIGN_LEFT,4,true));
		table.addCell(createCell( "" ,textFont,Element.ALIGN_LEFT,8,true));

		table.addCell(createCell("药品经营许可证有效期",keyFont,Element.ALIGN_LEFT,4,true));
		table.addCell(createCell( "" ,textFont,Element.ALIGN_LEFT,8,true));

		table.addCell(createCell("注册经营场所",keyFont,Element.ALIGN_LEFT,4,true));
		table.addCell(createCell( "" ,textFont,Element.ALIGN_LEFT,8,true));

		table.addCell(createCell("医保对企业付款起始时间",keyFont,Element.ALIGN_LEFT,4,true));
		table.addCell(createCell( "" ,textFont,Element.ALIGN_LEFT,8,true));

		table.addCell(createCell("是否纳入医保黑名单",keyFont,Element.ALIGN_LEFT,4,true));
		table.addCell(createCell( "" ,textFont,Element.ALIGN_LEFT,8,true));

		table.addCell(createCell("医保协议到期日",keyFont,Element.ALIGN_LEFT,4,true));
		table.addCell(createCell( "" ,textFont,Element.ALIGN_LEFT,8,true));

		return table;
	}

	/**
	 * 五、“两定机构”医保结算信息
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateSettlementList(PdfPTable table, Map<String, Object> map) {

		//List<ZwShebaoInfo> comStaffList = (List<ZwShebaoInfo>) map.get("comStaffBirth");

		table.addCell(createCell("序号",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("交易笔数(前18个月)",keyFont,Element.ALIGN_CENTER,5,true));
		table.addCell(createCell("交易金额(元)(前18个月)",keyFont,Element.ALIGN_CENTER,5,true));


		table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));

		return table;
	}


	/**
	 * 六、用水信息
	 * (一)企业用水信息（近三年）
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateWaterInfo(PdfPTable table, Map<String, Object> map) {

		List<T3070020000Q03000002> waterList = (List<T3070020000Q03000002>) map.get("waterInfo");

		table.addCell(createCell("序号",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("用水所属期",keyFont,Element.ALIGN_CENTER,3,true));
		table.addCell(createCell("用水量(吨)",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("缴费金额(元)",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("缴费日期",keyFont,Element.ALIGN_CENTER,3,true));

		if (null != waterList && waterList.size() > 0){
			for (int i = 0 ; i < waterList.size() ; i++) {
				T3070020000Q03000002 n = waterList.get(i);
				if (null == n) continue;
				int j = i+1;
				table.addCell(createCell( j+"" ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000Q03_000002008() ,textFont,Element.ALIGN_CENTER,3,true));
				table.addCell(createCell( n.getF_3070020000Q03_000002009() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000Q03_000002011() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000Q03_000002014() ,textFont,Element.ALIGN_CENTER,3,true));

			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 六、用水信息
	 * (二)企业用水欠费信息（近三年）
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateWaterArrears(PdfPTable table, Map<String, Object> map) {

		List<T3070020000Q03000005> waterList = (List<T3070020000Q03000005>) map.get("waterArrears");

		table.addCell(createCell("序号",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("欠费日期",keyFont,Element.ALIGN_CENTER,5,true));
		table.addCell(createCell("欠费金额(元)",keyFont,Element.ALIGN_CENTER,5,true));

		if (null != waterList && waterList.size() > 0){
			for (int i = 0 ; i < waterList.size() ; i++) {
				T3070020000Q03000005 n = waterList.get(i);
				if (null == n) continue;
				int j = i+1;
				table.addCell(createCell( j+"" ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000Q03_000005008() ,textFont,Element.ALIGN_CENTER,5,true));
				table.addCell(createCell( n.getF_3070020000Q03_000005009() ,textFont,Element.ALIGN_CENTER,5,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}


	/**
	 * 七、政采信息
	 * (一)单一来源
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateGovernSource(PdfPTable table, Map<String, Object> map) {

		List<T3070020000012000052> governList = (List<T3070020000012000052>) map.get("governSource");

		table.addCell(createCell("采购人名称",keyFont,Element.ALIGN_CENTER,3,true));
		table.addCell(createCell("采购项目名称",keyFont,Element.ALIGN_CENTER,3,true));
		table.addCell(createCell("采购货物(或服务)的预算金额(万元)",keyFont,Element.ALIGN_CENTER,6,true));

		if (null != governList && governList.size() > 0){
			for (int i = 0 ; i < governList.size() ; i++) {
				T3070020000012000052 n = governList.get(i);
				if (null == n) continue;
				table.addCell(createCell( n.getF_3070020000012_000052003(),textFont,Element.ALIGN_CENTER,5,true));
				table.addCell(createCell( n.getF_3070020000012_000052004() ,textFont,Element.ALIGN_CENTER,5,true));
				table.addCell(createCell( n.getF_3070020000012_000052006() ,textFont,Element.ALIGN_CENTER,5,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 七、政采信息
	 * (二)合同公告
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateGovernCommon(PdfPTable table, Map<String, Object> map) {

		List<T3070020000012000007> governList = (List<T3070020000012000007>) map.get("governCommon");

		table.addCell(createCell("项目名称",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("项目编号",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("采购单位",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("合同名称",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("合同金额(万元)",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("合同签订日期",keyFont,Element.ALIGN_CENTER,2,true));

		if (null != governList && governList.size() > 0){
			for (int i = 0 ; i < governList.size() ; i++) {
				T3070020000012000007 n = governList.get(i);
				if (null == n) continue;
				table.addCell(createCell( n.getF_3070020000012_000007008() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000012_000007004() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000012_000007007() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000012_000007011() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000012_000007009() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000012_000007001() ,textFont,Element.ALIGN_CENTER,2,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 七、政采信息
	 * (三)采购结果公告
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateGovernBuy(PdfPTable table, Map<String, Object> map) {

		List<T3070020000012000065> governList = (List<T3070020000012000065>) map.get("governBuy");

		table.addCell(createCell("项目名称",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("项目编号",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("包号",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("中标(成交)金额(万元)",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("商品名称",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("采购人名称",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("采购代理机构名称",keyFont,Element.ALIGN_CENTER,2,true));

		if (null != governList && governList.size() > 0){
			for (int i = 0 ; i < governList.size() ; i++) {
				T3070020000012000065 n = governList.get(i);
				if (null == n) continue;
				table.addCell(createCell( n.getF_3070020000012_000065004() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000012_000065005() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000012_000065006() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getF_3070020000012_000065007() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getF_3070020000012_000065010() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000012_000065020() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000012_000065023() ,textFont,Element.ALIGN_CENTER,2,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 七、政采信息
	 * (四)验收信息
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateGovernCheck(PdfPTable table, Map<String, Object> map) {

		List<T3070020000012000074> governList = (List<T3070020000012000074>) map.get("governCheck");

		table.addCell(createCell("项目名称",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("项目编号",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("合同名称",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("合同金额(万元)",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("采购单位",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("中标供应商",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("验收日期",keyFont,Element.ALIGN_CENTER,1,true));

		if (null != governList && governList.size() > 0){
			for (int i = 0 ; i < governList.size() ; i++) {
				T3070020000012000074 n = governList.get(i);
				if (null == n) continue;
				table.addCell(createCell( n.getF_3070020000012_000074002() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000012_000074003() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000012_000074004() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getF_3070020000012_000074005() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getF_3070020000012_000074006() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000012_000074007() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000012_000074008() ,textFont,Element.ALIGN_CENTER,2,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 九、未结清小贷信息
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateLoans(PdfPTable table, Map<String, Object> map) {

		//List<T3070020000012000074> governList = (List<T3070020000012000074>) map.get("governCheck");

		table.addCell(createCell("序号",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("合同金额(元)",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("贷款期限",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("合同起始时间",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("合同结束时间",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("借款用途",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("借款利率",keyFont,Element.ALIGN_CENTER,1,true));

		table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));

		/*if (null != governList && governList.size() > 0){
			for (int i = 0 ; i < governList.size() ; i++) {
				T3070020000012000074 n = governList.get(i);
				if (null == n) continue;
				table.addCell(createCell( n.getF_3070020000012_000074002() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000012_000074003() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000012_000074004() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getF_3070020000012_000074005() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getF_3070020000012_000074006() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000012_000074007() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000012_000074008() ,textFont,Element.ALIGN_CENTER,2,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}*/
		return table;
	}

	/**
	 * 十、高院信息
	 * （一）高院失信企业信息
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateCourtLoseCompany(PdfPTable table, Map<String, Object> map) {
		if (null == map || map.size() < 1 ) return table;
		ZwGfDisEntinfo sub = (ZwGfDisEntinfo) map.get("courtLoseCompany");

		table.addCell(createCell("被执行企业名称",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub ) table.addCell(createCell( "-",textFont,Element.ALIGN_LEFT,2,true));
		else table.addCell(createCell( sub.getExedComName() ,textFont,Element.ALIGN_LEFT,2,true));
		table.addCell(createCell("执行案号",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,2,true));
		else table.addCell(createCell( sub.getExeCaseNumber() ,textFont,Element.ALIGN_LEFT,2,true));
		table.addCell(createCell("执行依据文号",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,2,true));
		else table.addCell(createCell( sub.getExeBasSymbol() ,textFont,Element.ALIGN_LEFT,2,true));


		table.addCell(createCell("被执行企业地址",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,9,true));
		else table.addCell(createCell( sub.getExedComAddress() ,textFont,Element.ALIGN_LEFT,9,true));

		table.addCell(createCell("组织机构代码",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getOrgCode() ,textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("法定代表人",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getLegPerson() ,textFont,Element.ALIGN_LEFT,3,true));

		table.addCell(createCell("执行案由",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getExeCase() ,textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("标的金额",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getSubAmount() ,textFont,Element.ALIGN_LEFT,3,true));

		return table;
	}


	/**
	 * 十、高院信息
	 * (二)高院失信个人信息-1) 企业法定代表人违法信息（立案）
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateCourtLosePersonBegin(PdfPTable table, Map<String, Object> map) {

		List<ZwLegalIllegalCaseStart> list = (List<ZwLegalIllegalCaseStart>) map.get("courtLosePersonBegin");

		table.addCell(createCell("序号",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("立案日期",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("案号",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("案件类别",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("案由",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("当事人姓名",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("身份证号",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("性别",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("职务",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("案件来源",keyFont,Element.ALIGN_CENTER,2,true));
		if (null != list && list.size() > 0){
			for (int i = 0 ; i < list.size() ; i++) {
				ZwLegalIllegalCaseStart n = list.get(i);
				if (null == n) continue;
				int j = i + 1;
				table.addCell(createCell( j+"" ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getCaseStartDate() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getCaseNumber() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getCaseCategory(),textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getCause() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getLitName() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getIdNumber(),textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getGender(),textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getPosition(),textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getCaseSource(),textFont,Element.ALIGN_CENTER,2,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}


	/**
	 * 十、高院信息
	 * (二)高院失信个人信息-2) 企业法定代表人违法信息（结案）
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateCourtLosePersonEnd(PdfPTable table, Map<String, Object> map) {

		List<ZwLegalIllegalCaseEnd> list = (List<ZwLegalIllegalCaseEnd>) map.get("courtLosePersonEnd");

		table.addCell(createCell("序号",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("结案日期",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("案号",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("案由",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("当事人姓名",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("身份证号",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("性别",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("职务",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("诉讼地位",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("结案标的",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("结案方式",keyFont,Element.ALIGN_CENTER,1,true));
		table.addCell(createCell("判决结果(刑事)",keyFont,Element.ALIGN_CENTER,1,true));
		if (null != list && list.size() > 0){
			for (int i = 0 ; i < list.size() ; i++) {
				ZwLegalIllegalCaseEnd n = list.get(i);
				if (null == n) continue;
				int j = i + 1;
				table.addCell(createCell( j+"" ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getCaseEndDate() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getCaseNumber() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getCause() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getLitName() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getIdNumber(),textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getGender(),textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getPosition(),textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getLitStatus(),textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getCaseEndSubject(),textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getCaseEndMode(),textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getJudResult(),textFont,Element.ALIGN_CENTER,1,true));

			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}


	/**
	 * 十、高院信息-(三)高院结案信息
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateCourtSettle(PdfPTable table, Map<String, Object> map) {
		if (null == map || map.size() < 1 ) return table;
		ZwGfCaseEndInfo sub = (ZwGfCaseEndInfo) map.get("courtSettle");

		table.addCell(createCell("案件标识",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub ) table.addCell(createCell( "-",textFont,Element.ALIGN_LEFT,2,true));
		else table.addCell(createCell( sub.getCaseId() ,textFont,Element.ALIGN_LEFT,2,true));
		table.addCell(createCell("案件区域",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,2,true));
		else table.addCell(createCell( sub.getCaseArea() ,textFont,Element.ALIGN_LEFT,2,true));
		table.addCell(createCell("案号",keyFont,Element.ALIGN_LEFT,2,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,2,true));
		else table.addCell(createCell( sub.getCaseNumber() ,textFont,Element.ALIGN_LEFT,2,true));

		table.addCell(createCell("企业名称",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getComName() ,textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("企业编号",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getComNumber() ,textFont,Element.ALIGN_LEFT,3,true));

		table.addCell(createCell("法定代表人",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell(  "-"  ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell(  sub.getLegPerson()  ,textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("法人证件号",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getLegPerIdNumber() ,textFont,Element.ALIGN_LEFT,3,true));

		table.addCell(createCell("诉讼地位",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell(  "-"  ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell(  sub.getLitStatus()  ,textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("案由",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getCause() ,textFont,Element.ALIGN_LEFT,3,true));

		table.addCell(createCell("判决结果（刑事）",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell(  "-"  ,textFont,Element.ALIGN_LEFT,9,true));
		else table.addCell(createCell(  sub.getJudResult()  ,textFont,Element.ALIGN_LEFT,9,true));

		table.addCell(createCell("结案日期",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell(  "-"  ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell(  sub.getCaseEndDate()  ,textFont,Element.ALIGN_LEFT,3,true));
		table.addCell(createCell("结案方式",keyFont,Element.ALIGN_LEFT,3,true));
		if (null == sub ) table.addCell(createCell( "-" ,textFont,Element.ALIGN_LEFT,3,true));
		else table.addCell(createCell( sub.getCaseEndMode() ,textFont,Element.ALIGN_LEFT,3,true));

		return table;
	}

	/**
	 * 十一、重要项目列入情况
	 * (1)天津市“专精特新”中小企业培育
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateEnrolList1(PdfPTable table, Map<String, Object> map) {

		List<T3070020000003000034> list = (List<T3070020000003000034>) map.get("enrolList1");

		table.addCell(createCell("行政相对人名称",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("法定代表人",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("确认日期",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("确认内容",keyFont,Element.ALIGN_CENTER,6,true));
		if (null != list && list.size() > 0){
			for (int i = 0 ; i < list.size() ; i++) {
				T3070020000003000034 n = list.get(i);
				if (null == n) continue;
				int j = i + 1;
				table.addCell(createCell( n.getF_3070020000003_000034001() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getF_3070020000003_000034003() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getF_3070020000003_000034005() ,textFont,Element.ALIGN_CENTER,1,true));
				table.addCell(createCell( n.getF_3070020000003_000034006(),textFont,Element.ALIGN_CENTER,1,true));

			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 十一、重要项目列入情况
	 * (2)天津市自然科学基金项目
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateEnrolList2(PdfPTable table, Map<String, Object> map) {

		List<T3070020000006000003> list = (List<T3070020000006000003>) map.get("enrolList2");

		table.addCell(createCell("项目名称",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("项目类型",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("申请单位名称",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("申请人姓名",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("市财政资金支出预算",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("资金预算总额",keyFont,Element.ALIGN_CENTER,2,true));
		if (null != list && list.size() > 0){
			for (int i = 0 ; i < list.size() ; i++) {
				T3070020000006000003 n = list.get(i);
				if (null == n) continue;
				table.addCell(createCell( n.getF_3070020000006_000003005(),textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000003004() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000003002() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000003000() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000003001() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000003003() ,textFont,Element.ALIGN_CENTER,2,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 十一、重要项目列入情况
	 * (3)国家科技型中小企业名录
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateEnrolList3(PdfPTable table, Map<String, Object> map) {

		List<T3070020000006000004> list = (List<T3070020000006000004>) map.get("enrolList3");

		table.addCell(createCell("企业名称",keyFont,Element.ALIGN_CENTER,3,true));
		table.addCell(createCell("注册地",keyFont,Element.ALIGN_CENTER,3,true));
		table.addCell(createCell("统一社会信用代码",keyFont,Element.ALIGN_CENTER,3,true));
		table.addCell(createCell("有效期",keyFont,Element.ALIGN_CENTER,3,true));
		if (null != list && list.size() > 0){
			for (int i = 0 ; i < list.size() ; i++) {
				T3070020000006000004 n = list.get(i);
				if (null == n) continue;
				table.addCell(createCell( n.getF_3070020000006_000004000() ,textFont,Element.ALIGN_CENTER,3,true));
				table.addCell(createCell( n.getF_3070020000006_000004002() ,textFont,Element.ALIGN_CENTER,3,true));
				table.addCell(createCell( n.getF_3070020000006_000004006() ,textFont,Element.ALIGN_CENTER,3,true));
				table.addCell(createCell( n.getF_3070020000006_000004007() ,textFont,Element.ALIGN_CENTER,3,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 十一、重要项目列入情况
	 * (四)天津市中小企业“专精特新”产品
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateEnrolList4(PdfPTable table, Map<String, Object> map) {

		//List<T3070020000006000004> list = (List<T3070020000006000004>) map.get("enrolList4");

		table.addCell(createCell("序号",keyFont,Element.ALIGN_CENTER,3,true));
		table.addCell(createCell("年份",keyFont,Element.ALIGN_CENTER,3,true));
		table.addCell(createCell("产品名称",keyFont,Element.ALIGN_CENTER,3,true));
		table.addCell(createCell("资金支持(万元)",keyFont,Element.ALIGN_CENTER,3,true));
		table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		/*if (null != list && list.size() > 0){
			for (int i = 0 ; i < list.size() ; i++) {
				T3070020000006000004 n = list.get(i);
				if (null == n) continue;
				int j = i + 1;
				table.addCell(createCell( n.getF_3070020000006_000004000() ,textFont,Element.ALIGN_CENTER,3,true));
				table.addCell(createCell( n.getF_3070020000006_000004002() ,textFont,Element.ALIGN_CENTER,3,true));
				table.addCell(createCell( n.getF_3070020000006_000004006() ,textFont,Element.ALIGN_CENTER,3,true));
				table.addCell(createCell( n.getF_3070020000006_000004007() ,textFont,Element.ALIGN_CENTER,3,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}*/
		return table;
	}

	/**
	 * 十一、重要项目列入情况
	 * (3)国家科技型中小企业名录
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateEnrolList5(PdfPTable table, Map<String, Object> map) {

		List<T3070020000006000011> list = (List<T3070020000006000011>) map.get("enrolList5");

		table.addCell(createCell("企业名称",keyFont,Element.ALIGN_CENTER,6,true));
		table.addCell(createCell("证书编号",keyFont,Element.ALIGN_CENTER,6,true));
		if (null != list && list.size() > 0){
			for (int i = 0 ; i < list.size() ; i++) {
				T3070020000006000011 n = list.get(i);
				if (null == n) continue;
				int j = i + 1;
				table.addCell(createCell( n.getF_3070020000006_000011002() ,textFont,Element.ALIGN_CENTER,6,true));
				table.addCell(createCell( n.getF_3070020000006_000011003() ,textFont,Element.ALIGN_CENTER,6,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 十一、重要项目列入情况
	 * (六)天津市自然科学基金资助项目
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateEnrolList6(PdfPTable table, Map<String, Object> map) {

		List<T3070020000006000014> list = (List<T3070020000006000014>) map.get("enrolList6");

		table.addCell(createCell("项目名称",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("项目类型",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("申请单位名称",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("申请人姓名",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("市财政资金支出预算(元)",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("资金预算总额(元)",keyFont,Element.ALIGN_CENTER,2,true));
		if (null != list && list.size() > 0){
			for (int i = 0 ; i < list.size() ; i++) {
				T3070020000006000014 n = list.get(i);
				if (null == n) continue;
				table.addCell(createCell( n.getF_3070020000006_000014002() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000014005() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000014003() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000014006() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000014004() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000014007() ,textFont,Element.ALIGN_CENTER,2,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 十一、重要项目列入情况
	 * (七)天津市技术先进型服务企业名单
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateEnrolList7(PdfPTable table, Map<String, Object> map) {

		List<T3070020000006000030> list = (List<T3070020000006000030>) map.get("enrolList7");

		table.addCell(createCell("企业名称",keyFont,Element.ALIGN_CENTER,4,true));
		table.addCell(createCell("统一社会信用代码",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("证书编号",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("发证时间",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("有效期至",keyFont,Element.ALIGN_CENTER,2,true));
		if (null != list && list.size() > 0){
			for (int i = 0 ; i < list.size() ; i++) {
				T3070020000006000030 n = list.get(i);
				if (null == n) continue;
				table.addCell(createCell( n.getF_3070020000006_000030002() ,textFont,Element.ALIGN_CENTER,4,true));
				table.addCell(createCell( n.getF_3070020000006_000030003() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000030004() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000030005() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000030006() ,textFont,Element.ALIGN_CENTER,2,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 十一、重要项目列入情况
	 * (八)国家重点研发计划项目
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateEnrolList8(PdfPTable table, Map<String, Object> map) {

		List<T3070020000006000048> list = (List<T3070020000006000048>) map.get("enrolList8");

		table.addCell(createCell("推荐项目名称",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("所属专项",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("行政相对人名称",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("法定代表人",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("行政行为发生日期",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("行政行为内容",keyFont,Element.ALIGN_CENTER,2,true));
		if (null != list && list.size() > 0){
			for (int i = 0 ; i < list.size() ; i++) {
				T3070020000006000048 n = list.get(i);
				if (null == n) continue;
				table.addCell(createCell( n.getF_3070020000006_000048007() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000048008() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000048002() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000048004() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000048005() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000006_000048006() ,textFont,Element.ALIGN_CENTER,2,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 十一、重要项目列入情况
	 * (九)天津市龙头企业基本信息
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplateEnrolList9(PdfPTable table, Map<String, Object> map) {

		List<T3070020000019000080> list = (List<T3070020000019000080>) map.get("enrolList9");

		table.addCell(createCell("企业级别",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("评定年份",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("所属区域",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("获得“三品一标”认证产品数量",keyFont,Element.ALIGN_CENTER,6,true));
		if (null != list && list.size() > 0){
			for (int i = 0 ; i < list.size() ; i++) {
				T3070020000019000080 n = list.get(i);
				if (null == n) continue;
				table.addCell(createCell( n.getF_3070020000019_000080001() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000019_000080002() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000019_000080003() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000019_000080006() ,textFont,Element.ALIGN_CENTER,6,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}

	/**
	 * 十二、专利信息
	 * @param table
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTemplatePatents(PdfPTable table, Map<String, Object> map) {

		List<T3070020000035000002> list = (List<T3070020000035000002>) map.get("patents");

		table.addCell(createCell("发明名称",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("专利类型",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("专利权人名称",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("申请号",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("申请日",keyFont,Element.ALIGN_CENTER,2,true));
		table.addCell(createCell("授权入库日",keyFont,Element.ALIGN_CENTER,2,true));
		if (null != list && list.size() > 0){
			for (int i = 0 ; i < list.size() ; i++) {
				T3070020000035000002 n = list.get(i);
				if (null == n) continue;
				table.addCell(createCell( n.getF_3070020000035_000002007() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000035_000002012() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000035_000002008() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000035_000002002() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000035_000002013() ,textFont,Element.ALIGN_CENTER,2,true));
				table.addCell(createCell( n.getF_3070020000035_000002011() ,textFont,Element.ALIGN_CENTER,2,true));
			}
		}else{
			table.addCell(createCell("暂无数据",textFont,Element.ALIGN_CENTER,12,true));
		}
		return table;
	}















   
    /**
     * 添加运营数据某一行
     * @param table		表格
     * @param i			列表占的格数
     * @param titleName	列表标题
     * @return
     */
	public PdfPTable addOperation(PdfPTable table, int i, String titleName, Object datas) throws Exception {
		String[] str = (String[]) datas;
		if (null == str || str.length == 0 ) return table;
		table.addCell(createCell(titleName,keyFont,Element.ALIGN_CENTER,i,true));
		for (String c : str){
			if ( null != c && !"".equals(c) && !"null".equals(c) ){
				table.addCell(createCell(c,textFont,Element.ALIGN_CENTER,1,true));
			}else{
				table.addCell(createCell("-",textFont,Element.ALIGN_CENTER,1,true));
			}
		}
		return table;
	}
	
	public PdfPTable addOperationMonths(PdfPTable table, int i, String titleName, Object datas) throws Exception {
		List<String> str = (List<String>) datas;
		if (null == str || str.size() == 0 ) return table;
		table.addCell(createCell(titleName,keyFont,Element.ALIGN_CENTER,i,true));
		for (String c : str){
			if ( null != c && !"".equals(c) && !"null".equals(c) ){
				table.addCell(createCell(c,textFont,Element.ALIGN_CENTER,1,true));
			}else{
				table.addCell(createCell("-",textFont,Element.ALIGN_CENTER,1,true));
			}
		}
		return table;
	}

	public void generatePDF() throws Exception{
        PdfPTable table = createTable(4); 
        //总标题
        table.addCell(createCell("北京玖富普惠信息技术有限公司档案", headFont,Element.ALIGN_MIDDLE,4,false)); 
        
        table.addCell(createCell("姓名", keyFont, Element.ALIGN_CENTER)); 
        table.addCell(createCell("年龄", keyFont, Element.ALIGN_CENTER)); 
        table.addCell(createCell("性别", keyFont, Element.ALIGN_CENTER)); 
        table.addCell(createCell("住址", keyFont, Element.ALIGN_CENTER)); 
           
        for(int i=0;i<100;i++){ 
            table.addCell(createCell("姓名"+i, textFont)); 
            table.addCell(createCell(i+15+"", textFont)); 
            table.addCell(createCell((i%2==0)?"男":"女", textFont)); 
            table.addCell(createCell("地址"+i, textFont)); 
        } 
        document.add(table); 
           
        document.close(); 
     } 






	public static void main(String[] args) throws FileNotFoundException,
			DocumentException {
		//实现A4纸页面 并且横向显示（不设置则为纵向）
		Document document = new Document(PageSize.A4.rotate());
		PdfWriter pdfWriter = PdfWriter.getInstance(document,new FileOutputStream("d:/demo.pdf"));
		// 打开文档
		document.open();
		// 创建第一页（如果只有一页的话，这一步可以省略）
		document.newPage();

		// 加入水印
		PdfContentByte waterMar = pdfWriter.getDirectContentUnder();
		// 开始设置水印
		waterMar.beginText();
		// 设置水印透明度
		PdfGState gs = new PdfGState();
		// 设置填充字体不透明度为0.4f
		gs.setFillOpacity(0.4f);
		try {
			// 设置水印字体参数及大小                                  (字体参数，字体编码格式，是否将字体信息嵌入到pdf中（一般不需要嵌入），字体大小)
			waterMar.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED), 60);
			// 设置透明度
			waterMar.setGState(gs);
			// 设置水印对齐方式 水印内容 X坐标 Y坐标 旋转角度
			waterMar.showTextAligned(Element.ALIGN_RIGHT, "tttttttttttttm" , 500, 430, 45);
			// 设置水印颜色
			waterMar.setColorFill(BaseColor.GRAY);
			//结束设置
			waterMar.endText();
			waterMar.stroke();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 加入文档内容

		document.add(new Paragraph("my first pdf demo"));
		document.add(new Paragraph("my first pdf demo"));
		document.add(new Paragraph("my first pdf demo"));



		// 关闭文档
		document.close();
		pdfWriter.close();
	}


}
