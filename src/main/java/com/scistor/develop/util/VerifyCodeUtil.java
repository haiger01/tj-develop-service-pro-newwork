package com.scistor.develop.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;


public class VerifyCodeUtil {

	private int w=70;
	private int h=35;
	private static Random r=new Random();
	//宋体，华文楷书，黑体，华文新魏，华文隶书，微软雅黑，楷体_GB2312,Times New Roman
	private String [] fontNames={"宋体","华文楷书","黑体","华文新魏","微软雅黑","华文隶书","楷体_GB2312","Times New Roman"};
	//可选的字符
	private static  String codes="23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
	//背景色
	private Color bgColor=new Color(255,255,255);
	//验证码上的文本
	private String text;

	//生成随机颜色
	private Color randomColor(){
		 int red=r.nextInt(150);
		 int green=r.nextInt(150);
		 int blue=r.nextInt(150);
		 return new Color(red,green,blue);
	}

	//生成随机字体
	private Font randomFont(){
		int index=r.nextInt(fontNames.length);//获取下标
		String fontName=fontNames[index];//生成随机的字体名称
		int style=r.nextInt(4);//获取随机样式，0表示无样式，1表示粗体，2表示斜体，3表示粗体加斜体
		int size=r.nextInt(5)+24;//生成随机字号，24~28
		return new Font(fontName,style,size);
	}

	//画干扰线
	private void drawLine(BufferedImage image){
		int num=3;//一共画3条
		Graphics2D g2=(Graphics2D) image.getGraphics();
		for(int i=0;i<num;i++){//随机生成坐标，即4个值
			int x1=r.nextInt(w);
			int y1=r.nextInt(h);
			int x2=r.nextInt(w);
			int y2=r.nextInt(h);
			g2.setStroke(new BasicStroke(1.5F));//设置笔画的宽度
			   g2.setColor(Color.blue);//干扰线颜色
			   g2.drawLine(x1, y1, x2, y2);//画线
		}
	}

	//随机生成一个字符
	private static char randomChar(){
		int index=r.nextInt(codes.length());
		return codes.charAt(index);
	}

	//创建BufferedImage
	private BufferedImage createImage(){
		BufferedImage image=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2=(Graphics2D) image.getGraphics();
		g2.setColor(this.bgColor);
		g2.fillRect(0, 0, w, h);
		return image;
	}

	//外界调用这个方法得到验证码
	public BufferedImage getImage(){
		BufferedImage image=createImage();//创建图片缓冲区
		Graphics2D g2=(Graphics2D)image.getGraphics();//得到绘制环境
		StringBuilder sb=new StringBuilder();//用来装载生成的验证码文本
		//向图中画4个字符
		for(int i=0;i<4;i++){//循环4次
			String s=randomChar()+"";//随机生成一个字符
			sb.append(s);//把字母添加到sb中
			double x=i*1.0*w/4;//设置当前字符的x轴坐标
			g2.setFont(randomFont());//设置随机字体
			g2.setColor(randomColor());//设置随机颜色
			g2.drawString(s, (int)x, h-5);
		}
		this.text=sb.toString();//把生成的字符串赋给了this.text
		drawLine(image);//添加干扰线
		return image;
	}

	//返回验证码图片上面的文本
	public String getText(){
		return text;
	}

	//保存图片到指定的输出流
	public static void output(BufferedImage image,OutputStream out) throws IOException{
		ImageIO.write(image, "JPEG", out);
	}


	/**
	 * 获取几位数的验证码
	 * @param num	几位数 （默认6位）(不能小于4位)
	 * @return
	 */
	public static String getRandomCode(int num){
		if (num <= 4 ) num = 6;
		String code = "";
		for (int i = 0 ; i < num ; i++ ) {
			code += randomChar();
		}
		return code;
	}


}
