/**  

* <p>Title: ExcelService</p>  

* <p>Description: </p>  

* @author Xilong.Yang  

* @date 2018年11月2日  

*/
package com.scistor.develop.util;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

/**
* @author Xilong.Y
* @version 创建时间：2018年11月2日 下午12:06:37
* 类说明
*/
/**
 * 
 * <p>
 * Title: ExcelService
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author Xilong.Yang
 * 
 * @date 2018年11月2日
 * 
 */

public class ExportExcelUtils<T> {
	public boolean exportExcel(String sheetName, String[] headers, List<T> dataset, OutputStream out)
			throws IOException {
		return exportExcel(sheetName, headers, dataset, "yyyy-MM-dd", out);
	}

	@SuppressWarnings("rawtypes")
	public void exportExcel(String[] headers, Collection<T> dataset, OutputStream out, String sheetname,
			List<String> fieldlist, Map map, List<String> wxgshlist) {
		exportExcel(sheetname, headers, dataset, out, "yyyy-MM-dd", fieldlist, map, wxgshlist);

	}

	@SuppressWarnings("rawtypes")
	public void exportExcel(String[] headers, Collection<T> dataset, OutputStream out, String pattern, String sheetname,
			List<String> fieldlist, Map map, List<String> wxgshlist) {
		exportExcel(sheetname, headers, dataset, out, pattern, fieldlist, map, wxgshlist);
	}

	/**
	 * @Description: 反射机制映射
	 * @author Xilong.Y
	 * @Create: 2018年11月2日:下午7:17:00
	 * @param title
	 * @param headers
	 * @param dataset
	 * @param pattern
	 * @param out
	 * @return
	 * @throws IOException :boolean
	 */

	@SuppressWarnings({ "unchecked", "deprecation", "resource", "rawtypes" })
	private boolean exportExcel(String title, String[] headers, List<T> dataset, String pattern, OutputStream out)
			throws IOException {

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setAlignment(HorizontalAlignment.CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12); // 字体高度
		font.setFontName("黑体"); // 字体
		font.setBold(true);// 宽度
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setBorderBottom(BorderStyle.THIN);
		style2.setBorderLeft(BorderStyle.THIN);
		style2.setBorderRight(BorderStyle.THIN);
		style2.setBorderTop(BorderStyle.THIN);
		style2.setAlignment(HorizontalAlignment.CENTER);
		style2.setVerticalAlignment(VerticalAlignment.CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBold(false);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		int index = 0;
		HSSFFont font3 = workbook.createFont();// 内容字体
		T t;
		Field[] fields;
		HSSFCell cell;
		Field field;
		String fieldName;
		String getMethodName;
		Class tCls;
		Method getMethod;
		Object value;
		String textValue;
		Pattern p = Pattern.compile("^//d+(//.//d+)?$");
		Matcher matcher;
		HSSFRichTextString richString1;
		Collection<T> newdata = null;
		if (dataset != null && dataset.size() > 0) {
			int num = dataset.size();
			int start = 0;
			int end = 0;
			for (int i = 0; i < num;) {
				start = i;
				if ((num - i) > 60000) {
					i += 60000;
				} else {
					i = num;
				}
				end = i;
				newdata = dataset.subList(start, end);// 截取数据源
				Iterator<T> it = newdata.iterator();// 操作数据
				// 生成一个表格
				HSSFSheet sheet = workbook.createSheet(
						title + "(" + (end - start) + "条)" + (end % 60000 == 0 ? end / 60000 : end / 60000 + 1));
				HSSFRow row = sheet.createRow(0);
				row.setHeight((short) 450);
				// 产生表格标题行
				for (short k = 0; k < headers.length; k++) {
					HSSFCell cell1 = row.createCell(k);
					HSSFRichTextString text = new HSSFRichTextString(headers[k]);
					cell1.setCellValue(text);
					cell1.setCellStyle(style);// 表头单元格样式
					cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
				}
				while (it.hasNext()) {
					index++;
					row = sheet.createRow(index);
					row.setHeight((short) 300);// 目的是想把行高设置成25px
					t = (T) it.next();
					fields = t.getClass().getDeclaredFields();
					for (short j = 0; j < headers.length; j++) {
						cell = row.createCell(j);
						cell.setCellStyle(style2);// 原来用的他
						field = fields[j];
						fieldName = field.getName();
						getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
						try {
							tCls = t.getClass();
							getMethod = tCls.getMethod(getMethodName, new Class[] {});
							value = getMethod.invoke(t, new Object[] {});
							if (value == null) {
								value = "";
							}
							// 判断值的类型后进行强制类型转换
							textValue = value.toString();
							// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
							if (textValue != null) {
								matcher = p.matcher(textValue);
								if (matcher.matches()) {
									// 是数字当作double处理
									cell.setCellValue(Double.parseDouble(textValue));
								} else {
									richString1 = new HSSFRichTextString(textValue);
									richString1.applyFont(font3);
									cell.setCellValue(richString1);
								}
							}
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
				for (int j = 0; j < headers.length; j++) {
//					sheet.autoSizeColumn(j); //调整第j列宽度
					sheet.setColumnWidth(j, headers[j].getBytes().length * 512);
				}
				index = 0;
			}
			workbook.write(out);
			return true;
		} else {
		}
		return false;
	}

	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	private void exportExcel(String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern,
			List<String> fieldlist, Map map, List<String> wxgshlist) {

		// 声明一个工作薄
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook();
		try {
			// 生成一个表格
			HSSFSheet sheet = workbook.createSheet(title);
			sheet.createFreezePane(0, 1, 0, 1);// 冻结表头
			// 设置表格默认列宽度为15个字节
			// sheet.trackAllColumnsForAutoSizing();
			for (int i = 0; i < fieldlist.size(); i++) {
				sheet.autoSizeColumn(i);
				sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 20 / 10);
			}

			sheet.setDefaultColumnWidth((short) 200);
			sheet.setColumnWidth(0, 60 * 80);// 设置第一列宽度
			// 生成一个样式
			HSSFCellStyle style = workbook.createCellStyle();
			// 设置这些样式
			style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setAlignment(HorizontalAlignment.CENTER);
			// 生成一个字体
			HSSFFont font = workbook.createFont();
			font.setColor(HSSFColor.VIOLET.index);
			font.setFontHeightInPoints((short) 10);// 字体高度
			font.setBold(true);
			// 把字体应用到当前的样式
			style.setFont(font);
			// 生成并设置另一个样式
			HSSFCellStyle style2 = workbook.createCellStyle();
			style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
			style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style2.setBorderBottom(BorderStyle.THIN);
			style2.setBorderLeft(BorderStyle.THIN);
			style2.setBorderRight(BorderStyle.THIN);
			style2.setBorderTop(BorderStyle.THIN);
			style2.setAlignment(HorizontalAlignment.CENTER);
			style2.setVerticalAlignment(VerticalAlignment.CENTER);
			// 生成另一个字体
			HSSFFont font2 = workbook.createFont();
			font2.setBold(false);
			// 把字体应用到当前的样式
			style2.setFont(font2);

			// 声明一个画图的顶级管理器
			HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
			HSSFRow row = sheet.createRow(0);
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}

			// 遍历集合数据，产生数据行
			Iterator<T> it = dataset.iterator();
			int index = 0;
			HSSFFont font3 = workbook.createFont();
			while (it.hasNext()) {
				index++;
				row = sheet.createRow(index);
				T t = (T) it.next();
				String result = "";
				if (t instanceof Map) {
					if (t != null) {
						Iterator<Entry<String, String>> itor = ((Map<String, String>) t).entrySet().iterator();
						while (itor.hasNext()) {
							Entry<String, String> entry = itor.next();
							if (fieldlist.contains(entry.getKey().toLowerCase())) {
								int idf = fieldlist.indexOf(entry.getKey().toLowerCase());
								HSSFCell cell = row.createCell(idf);
								cell.setCellStyle(style2);
								cell.setCellValue(entry.getValue());
								result += entry.getKey().toLowerCase() + ",";
							}
						}
					}
				} else {
					// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
					Field[] fields = t.getClass().getDeclaredFields();

					for (short i = 0; i < fields.length; i++) {
						Field field = fields[i];
						String fieldName = field.getName();
						if (fieldlist.contains(fieldName)) {
							String res = "";
							int idf = fieldlist.indexOf(fieldName);
							HSSFCell cell = row.createCell(idf);
							cell.setCellStyle(style2);
							String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
									+ fieldName.substring(1);
							try {
								Class tCls = t.getClass();
								Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
								Object value = getMethod.invoke(t, new Object[] {});

								// 判断值的类型后进行强制类型转换
								String textValue = null;
								if (value instanceof Integer) {
									int intValue = (Integer) value;
									textValue = intValue + "";
									cell.setCellValue(textValue);
								} else if (value instanceof Float) {
									float fValue = (Float) value;
									textValue = fValue + "";
									cell.setCellValue(textValue);
								} else if (value instanceof Double) {
									double dValue = (Double) value;
									textValue = dValue + "";
									cell.setCellValue(textValue);
								} else if (value instanceof Long) {
									long longValue = (Long) value;
									cell.setCellValue(longValue);
								} else {
									// 其它数据类型都当作字符串简单处理
									if (value != null) {
										textValue = value.toString();
										res = GeneratedValue(fieldName, textValue, map);
										if (res.contains("图片url")) {
											String fileurl = res.substring(6, res.length());
											File jpgfile = new File(fileurl);// barName
											// 为统计图片在服务器上的路径
											ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();// 字节输出流，用来写二进制文件
											BufferedImage bufferImg = ImageIO.read(jpgfile);
											ImageIO.write(bufferImg, "png", byteArrayOut);
											// 有图片时，设置行高为60px;
											row.setHeightInPoints(30);
											// 设置图片所在列宽度为80px,注意这里单位的一个换算
											sheet.setColumnWidth(i, (short) (35.7 * 80));
											HSSFClientAnchor anchor = new HSSFClientAnchor(350, 50, 800, 230,
													(short) idf, index, (short) idf, index);
											// anchor.setAnchorType(2);
											patriarch.createPicture(anchor, workbook.addPicture(
													byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));// 将统计图片添加到Excel文件中
										} else if (res.contains("imageid") || res.contains("origin_photos")) {
											textValue = "http://10.37.47.49:8080/ImageUpload/imageinfo/" + textValue;

											cell.setCellValue(textValue);
										} else {
											cell.setCellValue(res);
										}
									} else if (value == null) {
										textValue = "";
										cell.setCellValue(textValue);
									}
								}
								// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
								if (textValue != null) {
									Pattern p = Pattern.compile("^//d+(//.//d+)?$");
									Matcher matcher = p.matcher(textValue);
									if (matcher.matches()) {
										// 是数字当作double处理
										cell.setCellValue(Double.parseDouble(textValue));
									} else if (!res.contains("图片url")) {
										HSSFRichTextString richString = new HSSFRichTextString(textValue);
										font3.setColor(HSSFColor.BLUE.index);
										richString.applyFont(font3);
										cell.setCellValue(richString);
									}
								}
							} catch (SecurityException e) {
								e.printStackTrace();
							} catch (NoSuchMethodException e) {
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							} finally {
								// 清理资源
							}
						}
						result += fieldName + ",";
					}
				}
				setValue(fieldlist, result, wxgshlist, row, style2);
			}

			try {
				workbook.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
		}
	}

	public void setValue(List<String> fieldlist, String result, List<String> wxgshlist, HSSFRow row,
			HSSFCellStyle style2) {
		if (wxgshlist.size() > 0) {
			for (int j = 0; j < fieldlist.size(); j++) {
				if (!result.contains(fieldlist.get(j))) {
					try {
						if (wxgshlist.contains(fieldlist.get(j))) {
							HSSFCell cell = row.createCell(5);
							cell.setCellStyle(style2);
							String star = row.getCell(3).getStringCellValue();
							String endstr = row.getCell(4).getStringCellValue();
							if (!star.equals("") && !endstr.equals("")) {
								star = star.replaceAll("-", ".");
								endstr = endstr.replaceAll("-", ".");
								cell.setCellValue(getday(star, endstr) + "");
							}
						} else {
							int idf = fieldlist.indexOf(fieldlist.get(j));
							HSSFCell cell = row.createCell(idf);
							cell.setCellStyle(style2);
						}
					} catch (Exception e) {
					}
				}
			}
		} else {
			for (int j = 0; j < fieldlist.size(); j++) {
				if (!result.contains(fieldlist.get(j))) {
					try {
						if (!wxgshlist.contains(fieldlist.get(j))) {
							int idf = fieldlist.indexOf(fieldlist.get(j));
							HSSFCell cell = row.createCell(idf);
							cell.setCellStyle(style2);
						}
					} catch (Exception e) {
					}
				}
			}
		}
	}

	public static int getday(String str, String end) {
		int day = 0;
		Calendar calendar = new GregorianCalendar();
		Calendar calendar2 = Calendar.getInstance();
		// 通过SimpleDateFormat将字符串解析为Date类型
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		try {
			sdf.parse(str);
			sdf.parse(end);
			// 将Date类型放入Calendar
			calendar.setTime(sdf.parse(str));
			calendar2.setTime(sdf.parse(end));
			calendar2.add(Calendar.DATE, 1);
			while (calendar2.compareTo(calendar) > 0) {
				// Calendar类型中的日期+1
				calendar.add(Calendar.DATE, 1);
				day++;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return day;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String GeneratedValue(String fieldName, String value, Map map) {
		String retval = "";
		// -----------------------------------值类型转换比如数据库中存的是1页面显示“是”------------------------------------------------
		if (map != null) {
			Iterator<Entry<String, Map>> itor = map.entrySet().iterator();
			while (itor.hasNext()) {
				Entry<String, Map> entry = itor.next();
				if (fieldName.equals(entry.getKey().toString())) {
					Map<String, String> secd = entry.getValue();
					Iterator<Entry<String, String>> secditor = secd.entrySet().iterator();
					while (secditor.hasNext()) {
						Entry<String, String> secdentry = secditor.next();
						if (value != null) {
							if (secdentry.getKey().equals(value)) {
								retval = secdentry.getValue();
							}
							if (retval.equals("") && !secdentry.getKey().equals(value)) {
								retval = secdentry.getKey();
							}
						}
					}
				}
			}
		} else {
			retval = value;
		}
		// -----------------------------------------------------------------------------------
		return retval;
	}

	public static OutputStream getout(HttpServletResponse response, String filename) {
		OutputStream out = null;
		try {
			try {
				filename = new String((filename + ".xls").getBytes(), "iso-8859-1");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // 中文文件名必须使用此句话
			response.setContentType("application/octet-stream");
			response.setContentType("application/OCTET-STREAM;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);
			out = new BufferedOutputStream(response.getOutputStream());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}

}
