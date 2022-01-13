package com.github.chanming2015.utils.log;

import com.alibaba.fastjson.JSON;

/**
 * Create Date:2015年12月4日 下午7:32:19 <br/> 
 * Description: 日志格式化输出工具 <br/> 
 * Version:1.0.0 <br/> 
 * @author XuMaoSen
 */
public class SimpleLogFormater {

    /**
     * 将方法调用的参数格式化输出
     * @param params
     * @return
     */
    public static String formatParams(Object... params) {
        int paramsLength = params.length;

        StringBuilder formated = new StringBuilder("Parameters :");
        if (paramsLength == 0) {
            formated.append(" NO PARAM!");
        } else {
            for (int i = 0; i < paramsLength; i++) {
                formated.append(" PARAM");
                formated.append(i + 1);
                formated.append(":");
                formated.append(JSON.toJSON(params[i]));
                formated.append(";");
            }
        }
        return formated.toString();
    }

    /**
     * Description: 将方法调用的返回结果格式化输出 <br/> 
     * Create Date:2022年1月7日 <br/> 
     * @author XuMaoSen
     * @return
     */
    public static String formatResult(String methodName, Object result)
    {
        return String.format("Result : %s(); %s.", methodName, JSON.toJSON(result));
    }

    /**
     * 将捕捉到的异常格式化输出
     * @param result
     * @return
     */
    public static String formatException(String bizErrorInfo, Exception e){
        StringBuilder formated = new StringBuilder("Error : ");
        formated.append(bizErrorInfo);
        formated.append("\nException message : ");
        formated.append(e.getMessage());
        formated.append("\nException trace : \n");
        for (StackTraceElement elment : e.getStackTrace()) {
            formated.append(elment.toString());
            formated.append("\n");
        }
        return formated.toString();
    }
}