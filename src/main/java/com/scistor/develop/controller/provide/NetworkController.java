package com.scistor.develop.controller.provide;

import com.scistor.develop.controller.ParentController;
import com.scistor.develop.data.entity.LoanApplication;
import com.scistor.develop.data.entity.Network;
import com.scistor.develop.data.entity.ToServer;
import com.scistor.develop.data.entity.ZwCompany;
import com.scistor.develop.service.business.LoanApplicationService;
import com.scistor.develop.service.business.NetworkService;
import com.scistor.develop.util.DateTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static com.scistor.develop.error.ExceptionCode.*;
import static com.scistor.develop.util.ObjectUtil.ckStrIsNotnull;

@RestController
@RequestMapping(value = "/tianjinDevelop/Network/")
public class NetworkController extends ParentController{


    //创建线程池,这个线程池类型可以自己定
    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

    @Autowired
    LoanApplicationService loanApplicationService;

    @Autowired
    NetworkService networkService;

    /**
     * 企业政务数据----添加企业信息和该条数据的keyCode
     * @param number        当前页
     * @param size          每页条数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getEnterpriseGovernmentDataList")
    public Map getEnterpriseGovernmentDataList(LoanApplication loanApplication, int number, int size) throws Exception {

        //根据银行ID查询企业申请该产品的审核列表---受理银行ID不传就不返回数据
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isEmpty(loanApplication.getAcceptCompanyId()))
            return createCommonPack(code301, "请检查参数");
        map = loanApplicationService.getEnterpriseGovernmentDataList(loanApplication, number * size, size);
        return createCommonPack(code200, map);
    }

    /**
     * 测试请求接口
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getTest")
    public Map getTest() throws Exception {
        System.out.println("接收到请求.......天津政务网测试接口.......");
        return createCommonPack(code200,"接收到请求.......天津政务网测试接口.......");
    }

    //******************************************************以下接口需要部署到天津政务网---本地只是调试***************************************************************





    /**
     * 生成文件
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "generateTheFile")
    public Map generateTheFile(ZwCompany zwCompany,String demandId,String auditId,String keyCode,String creditTime,String dateofcredit) throws Exception {

        if(!ckStrIsNotnull(demandId,auditId,keyCode,creditTime,dateofcredit)){
            return createCommonPack(code301, "参数错位，有的值为空值");
        }
        //判断授信时间是否在有效期内
        String beginTime = DateTool.dateTimeSec2Str(new Date());//当前时间
        String endTime = DateTool.getLastMonthDate(creditTime,Integer.parseInt(dateofcredit));//某个时间的未来时间
        if(beginTime.compareTo(endTime) > 0){//说明当前时间大于某个时间的未来时间，说明keyCode已经过期
            return createCommonPack(code202, "参数已过期");
        }
        //方法加入到线程池中去执行
        executor.execute(new ToServer(zwCompany,networkService,demandId,auditId,keyCode));
        //生成完之后保存文件地址
        return createCommonPack(code200, "文件生成中......");
    }


    /**
     * 下载PTF文件
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "generateTheDataDown")
    public Map generateTheDataDown(Network network) throws Exception {

        //根据需求ID和申请ID查询下载路径
        Map<String,Object> map = networkService.generateTheDataDown(network);
        if(CollectionUtils.isEmpty(map) || StringUtils.isEmpty(map.get("data_url")+"") || !"null".equals(map.get("data_url")+"")){
            return createCommonPack(code203, "请先生成文件");
        }
        return createCommonPack(code200, networkService.generateTheDataDown(network).get("data_url"));
    }




}
