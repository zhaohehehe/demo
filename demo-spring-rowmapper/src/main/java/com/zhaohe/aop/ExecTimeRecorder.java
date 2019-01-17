package com.zhaohe.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * service方法执行时间监控
 * 参见：http://blog.csdn.net/aishenkuxing/article/details/46775015
 * @author ZH
 *
 */
@Aspect
@Component
public class ExecTimeRecorder {
	private static Logger logger = Logger.getLogger(ExecTimeRecorder.class);
	public static final long TIMEOUT = 60000;

	//================pointcut 1======================================================================
	@Pointcut(value = "execution(* com.zhaohe.dao.impl.*.*(..)) && args(param)",argNames = "param")
	private void pointcut(String param) {
		System.out.println("切入点pointcut()参数：" + param);
	}
	@Before(value = "pointcut(param)",argNames = "param")
	private void beforePointcut(String param) {
		System.out.println("beforePointcut()参数:" + param);
	}
	//================pointcut 2======================================================================
	//方法体不会执行
	@Pointcut("within(com.zhaohe.dao.impl.*)")
	public void inWebLayer() {
		System.out.println("不执行方法体：切入点inWebLayer()");
	}

	@Before(value = "inWebLayer()")
	private void beforeinWebLayer() {
		System.out.println("Before inWebLayer()");
	}
	@AfterReturning(pointcut = "inWebLayer()", returning = "retVal")
	public void doAccessCheck(Object retVal) {
		System.out.println("AfterReturning inWebLayer() : doAccessCheck() = " + retVal);
	}
	@AfterThrowing("inWebLayer()")
    public void doException() {
        System.out.println("AfterThrowing inWebLayer()");
    }
	//================pointcut 3======================================================================
	@Around(value = "execution(* com.zhaohe.dao.impl.*.*(..))")
	private Object aroundLayer(ProceedingJoinPoint pjp) throws Throwable {
		Object obj = null;
		Object[] args = pjp.getArgs();
		long startTime = System.currentTimeMillis();
		// 结束监控
		obj = pjp.proceed(args);
		long endTime = System.currentTimeMillis();
		// 获取执行的方法名
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
		// 打印耗时的信息
		this.printExecTime(methodName, startTime, endTime);
		return obj;
	}
	@AfterReturning(value = "execution(* com.zhaohe.dao.impl.*.*(..))")
	private void logReceiver() {
		System.out.println("切入点logReceiver...");
	}

	private void printExecTime(String methodName, long startTime, long endTime) {
		long exeTime = endTime - startTime;
		if (exeTime > TIMEOUT) {
			logger.warn("\n=========" + methodName + " 方法,耗时: " + exeTime + " ms================");
		} else {
			logger.info("\n=========" + methodName + " 方法,耗时: " + exeTime + " ms================");
		}

	}
}
