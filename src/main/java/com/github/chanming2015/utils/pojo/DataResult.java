package com.github.chanming2015.utils.pojo;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Description: 数据库返回结果封装 <br/> 
 * Create Date:2019年5月9日 <br/> 
 * @author XuMaoSen
 */
public class DataResult<T> implements Serializable
{
    private static final long serialVersionUID = 1630635754057859987L;

    private DataResult(int code)
    {
        this.code = code;
    }

    /**
     * 接口调用成功，有返回对象
     */
    public static <T> DataResult<T> newSuccess(T object)
    {
        DataResult<T> result = new DataResult<>(0);
        result.setResult(object);
        return result;
    }

    /**
     * 接口调用失败，返回异常信息
     */
    public static <T> DataResult<T> newException(String message)
    {
        DataResult<T> result = new DataResult<>(-1);
        result.setMessage(message);
        return result;
    }

    /**
     * 接口调用失败，返回错误结果
     */
    public static <T> DataResult<T> newFailure(String errorCode, String message)
    {
        DataResult<T> result = newException(message);
        result.setError(errorCode);
        return result;
    }

    /**
     * 接口返回码：0成功 -1失败
     */
    private int code;
    /**
     * 接口返回数据对象
     */
    private T result;
    /**
     * 开发人员约定的错误代码
     */
    private String error;
    /**
     * 错误信息，通常为异常信息
     */
    private String message;

    /** 判断返回结果是否成功 */
    public boolean success()
    {
        return code == 0;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public T getResult()
    {
        return result;
    }

    public void setResult(T result)
    {
        this.result = result;
    }

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder("Result");
        if (result != null)
        {
            str.append("<");
            str.append(result.getClass().getSimpleName());
            str.append(">");
        }
        str.append(": {code=");
        str.append(code);
        if (result != null)
        {
            if (result.getClass().isArray())
            {
                str.append(", object=");
                str.append(Arrays.toString(((Object[]) result)));
            }
            else
            {
                str.append(", object=");
                str.append(result);
            }
        }
        if (error != null)
        {
            str.append(", error=");
            str.append(error);
        }
        if (message != null)
        {
            str.append(", message=");
            str.append(message);
        }
        str.append(" }");
        return str.toString();
    }
}
