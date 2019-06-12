package com.ylzinfo.hospemc.util;

import com.alibaba.dubbo.rpc.*;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;


public class TraceIdFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation){
		String requestId = RpcContext.getContext().getAttachment("requestId");
		if ( !StringUtils.isEmpty(requestId) ) {
			// *) 从RpcContext里获取traceId并保存
			TraceIdUtils.setTraceId(requestId);
			MDC.put("requestId", requestId);
		} else {
			// *) 交互前重新设置traceId, 避免信息丢失
			RpcContext.getContext().setAttachment("requestId", TraceIdUtils.getTraceId());
			MDC.put("requestId", TraceIdUtils.getTraceId());
		}
		// *) 实际的rpc调用
		return invoker.invoke(invocation);
	}

}
