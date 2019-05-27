package com.github.chanming2015.utils.sql;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Description: <br/> 
 * Create Date:2016年12月7日 <br/> 
 * Version:1.0.0 <br/> 
 * @author XuMaoSen
 */
@FunctionalInterface
public interface SpecCriterion
{
    Predicate getPredicate(Root<?> root, CriteriaBuilder cb);
}
