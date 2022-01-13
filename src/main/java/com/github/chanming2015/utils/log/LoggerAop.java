package com.github.chanming2015.utils.log;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Description: AOP日志接口 <br/> 
 * Create Date:2015年12月4日 下午7:55:19 <br/> 
 * Version:1.0.0 <br/> 
 * @author XuMaoSen
 */
public interface LoggerAop {
    /**
     * Description 前置通知 <br/> 
     * Create Time:2015年8月13日 上午10:49:43 <br/> 
     * @param param
     * @author XuMaoSen
     */
    void beforeAdvice(String param);
    
    /**
     * Description 后置返回通知 <br/> 
     * Create Time:2015年8月13日 上午10:40:35 <br/> 
     * @param retVal
     * @author XuMaoSen
     */
    void afterReturningAdvice(Object retVal);
    
    /**
     * Description 后置异常通知 <br/> 
     * Create Time:2015年8月13日 上午10:41:06 <br/> 
     * @param exception
     * @author XuMaoSen
     */
    void afterThrowingAdvice(Exception exception);
    
    /**
     * Description 后置最终通知 <br/> 
     * Create Time:2015年8月13日 上午10:41:40 <br/> 
     * @author XuMaoSen
     */
    void afterFinallyAdvice();
    
    /**
     * Description 环绕通知 <br/> 
     * Create Time:2015年8月13日 上午10:42:51 <br/> 
     * @param pjp
     * @author XuMaoSen
     */
    Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable;
    /**
     * Description: 环绕通知 <br/> 
     * Create Date:2021年12月24日 <br/> 
     * @author XuMaoSen
     * @param isPrintResponse 是否打印结果日志
     * @return
     */
    Object aroundAdvice(ProceedingJoinPoint pjp, boolean isPrintResponse) throws Throwable;

    /**
     * Description: 环绕通知 <br/> 
     * Create Date:2021年12月24日 <br/> 
     * @author XuMaoSen
     * @param isPrintResponse 是否打印结果日志
     * @param maxResultLength 结果日志最大长度
     * @return
     */
    Object aroundAdvice(ProceedingJoinPoint pjp, boolean isPrintResponse, int maxResultLength) throws Throwable;
}
