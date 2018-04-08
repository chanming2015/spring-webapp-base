package com.github.chanming2015.utils.sql;

/**
 * Description:
 * Create Date:2016年12月10日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class SpecProperty
{
    private final String propertyName;
    private String aliasName;
    private SpecType specType;

    private SpecProperty(String name)
    {
        propertyName = name;
        aliasName = name;
        specType = SpecType.SELECT;
    }

    public static SpecProperty forName(String name)
    {
        return new SpecProperty(name);
    }

    public SpecProperty alias(String name)
    {
        aliasName = name;
        return this;
    }

    public String getPropertyName()
    {
        return propertyName;
    }

    public String getAliasName()
    {
        return aliasName;
    }

    public SpecType getSpecType()
    {
        return specType;
    }

    public SpecProperty count()
    {
        specType = SpecType.COUNT;
        return this;
    }

    public SpecProperty sum()
    {
        specType = SpecType.SUM;
        return this;
    }
}
