package com.github.chanming2015.utils.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * Description: 默认日志APO实现 <br/> 
 * Create Date:2015年12月4日 下午7:41:50 <br/> 
 * Version:1.0.0 <br/> 
 * @author XuMaoSen
 */
public class DefaultAopLogger implements LoggerAop
{
    /** @author XuMaoSen
     */
    @Override
    public void beforeAdvice(String param)
    {
    }

    /** @author XuMaoSen
     */
    @Override
    public void afterReturningAdvice(Object retVal)
    {
    }

    /** @author XuMaoSen
     */
    @Override
    public void afterThrowingAdvice(Exception exception)
    {
    }

    /** @author XuMaoSen
     */
    @Override
    public void afterFinallyAdvice()
    {
    }

    /** @author XuMaoSen
     */
    @Override
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable
    {
        return aroundAdvice(joinPoint, true);
    }

    @Override
    public Object aroundAdvice(ProceedingJoinPoint joinPoint, boolean isPrintResponse) throws Throwable
    {
        return aroundAdvice(joinPoint, isPrintResponse, Integer.MAX_VALUE);
    }

    @Override
    public Object aroundAdvice(ProceedingJoinPoint joinPoint, boolean isPrintResponse, int maxResultLength) throws Throwable
    {
        // 获取被代理的对象名
        String targetName = joinPoint.getSignature().getDeclaringTypeName();
        // 获取被调用的方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取方法参数
        Object[] arguments = joinPoint.getArgs();
        Logger logger = LoggerFactory.getLogger(targetName);
        logger.info(SimpleLogFormater.formatParams(methodName + "()", arguments));

        Object proceed = null;
        try
        {
            // 调用被代理对象方法
            proceed = joinPoint.proceed();
        }
        catch (Exception e)
        {
            String responseInfo = SimpleLogFormater.formatException(methodName + "()", e);
            logger.error(responseInfo);
            // 不处理，抛出异常
            throw e;
        }

        if (isPrintResponse)
        {
            if (proceed instanceof JSON)
            {
                String responseInfo = SimpleLogFormater.formatResult(methodName, proceed);
                if (responseInfo.length() > maxResultLength)
                {
                    responseInfo = responseInfo.substring(0, maxResultLength);
                }
                logger.info(responseInfo);
            }
            else
            {
                String responseInfo = proceed != null ? proceed.toString() : "Result is null";
                if (responseInfo.length() > maxResultLength)
                {
                    responseInfo = responseInfo.substring(0, maxResultLength);
                }
                logger.info("Result : " + responseInfo);
            }
        }
        return proceed;
    }
}
