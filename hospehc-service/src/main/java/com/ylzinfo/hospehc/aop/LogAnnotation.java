package com.ylzinfo.hospehc.aop;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface LogAnnotation {
	//模块名
	String moduleName() default "";

	//操作内容
	String option() default "";
}
