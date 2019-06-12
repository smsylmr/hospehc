package com.ylzinfo.hospemc.filter;

import com.alibaba.dubbo.rpc.RpcContext;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "myFilter", urlPatterns = "/*")
public class MyFilter implements Filter {

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        /*String date = request.getParameter("date");
        if (CommonUtil.isEmpty(date)) {
            date = Global.year_month.format(new Date());
        }
        if (CommonUtil.isEmpty(Global.isCreatedTable.get(date.split("-")[0]))) {
            //自动修复表结构
            afts.run(Integer.parseInt(date.split("-")[0]));
            //数据库版本控制，用于处理一些无法使用注解做到的数据库数据处理
            versionControll.runVersion(Integer.parseInt(date.split("-")[0]));
            Global.isCreatedTable.put(date.split("-")[0], "1");
        }*/
        //设置追踪号 todo 设置正确的跟踪号
        MDC.put("requestId", "test");
        RpcContext.getContext().setAttachment("requestId", "test");

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

}