package com.scistor.develop.data.entity;

import com.scistor.develop.service.business.NetworkService;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ToServer extends Thread {

    public static Map<String,Object> map = new HashMap<String,Object>(){
        {
            this.put("company","不低于1");
            this.put("business","不超过2");
            this.put("3","3左右");
            this.put("4","固定利率4");
            this.put("5","范围5");
            this.put("6","其他");
        }
    };


    private NetworkService networkService;

    private ZwCompany zwCompany;

    private String demandId;

    private String auditId;

    private String codeKey;

    //需要什么参数就自己定义
    public ToServer(ZwCompany zwCompany,NetworkService networkService,String demandId,String auditId,String codeKey) {
        this.zwCompany = zwCompany;
        this.networkService = networkService;
        this.demandId = demandId;
        this.auditId = auditId;
        this.codeKey = codeKey;
    }
    /**
     * 执行定时请求
     */
    @Override
    public void run(){
        // 把run方法里的内容换成你要执行的内容
        /*service.doService(json);*/
        try {
            System.out.println(new Date());

            System.out.println("文件生成中.......");
            //查询数据文件
            String filePath = networkService.getGenerateTheDataList(zwCompany);
            System.out.println("文件保存地址："+filePath);

            Network network = new Network();
            network.setCheckName(zwCompany.getCheckName());
            network.setAuditId(auditId);
            network.setDemandId(demandId);
            network.setDataUrl(filePath);
            network.setCodeKey(codeKey);
            networkService.addGenerateTheData(network);
            System.out.println("生成文件爆粗入库");

        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("异常---------------");
        }
    }
}
