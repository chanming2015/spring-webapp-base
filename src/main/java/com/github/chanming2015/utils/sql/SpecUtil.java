package com.github.chanming2015.utils.sql;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

/**
 * Description: <br/> 
 * Create Date: 2016年12月7日 <br/> 
 * Version:1.0.0 <br/> 
 * @author XuMaoSen
 */
public class SpecUtil
{
    public static <T> Specification<T> spec(final SpecParam<T> specParam)
    {
        return (root, query, cb) ->
        {
            if (specParam != null)
            {
                Predicate result = null;
                Collection<SpecCriterion> specs = specParam.getCriterions();
                if (specs.size() > 0)
                {
                    Set<Predicate> predicates = new LinkedHashSet<>(4);
                    specs.forEach(spec -> predicates.add(spec.getPredicate(root, cb)));
                    result = cb.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                Set<Collection<SpecCriterion>> orSpecSet = specParam.getOrSpecCriterions();
                if ((orSpecSet != null) && (orSpecSet.size() > 0))
                {
                    for (Collection<SpecCriterion> orSpecs : orSpecSet)
                    {
                        Set<Predicate> predicates = new LinkedHashSet<>(4);
                        orSpecs.forEach(spec -> predicates.add(spec.getPredicate(root, cb)));
                        result = cb.and(result, cb.or(predicates.toArray(new Predicate[predicates.size()])));
                    }
                }
                return result;
            }
            return cb.conjunction();
        };
    }
}
