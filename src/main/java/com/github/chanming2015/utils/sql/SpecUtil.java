package com.github.chanming2015.utils.sql;

import java.util.Collection;
import java.util.HashSet;
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
                    Set<Predicate> predicates = new HashSet<Predicate>(4);
                    specs.forEach(spec -> predicates.add(spec.getPredicate(root, cb)));
                    result = cb.and(predicates.toArray(new Predicate[predicates.size()]));
                }
                Set<Collection<SpecCriterion>> orSpecSet = specParam.getOrSpecCriterions();
                if ((orSpecSet != null) && (orSpecSet.size() > 0))
                {
                    for (Collection<SpecCriterion> orSpecs : orSpecSet)
                    {
                        Set<Predicate> predicates = new HashSet<Predicate>(4);
                        orSpecs.forEach(spec -> predicates.add(spec.getPredicate(root, cb)));
                        Predicate orResult = cb.or(predicates.toArray(new Predicate[predicates.size()]));
                        result = cb.and(result, orResult);
                    }
                }
                return result;
            }
            return cb.conjunction();
        };
    }
}
