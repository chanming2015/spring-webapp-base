package com.github.chanming2015.utils.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

/**
 * Description: <br/> 
 * Create Date:2016年12月10日 <br/> 
 * Version:1.0.0 <br/> 
 * @author XuMaoSen
 */
@Repository
public class TupleRepository
{
    @Autowired
    private EntityManager em;

    public <T> List<Tuple> findAll(Class<T> domainClass, SpecProperty... properties)
    {
        return findAll(domainClass, null, null, null, properties);
    }

    public <T> List<Tuple> findAll(Class<T> domainClass, Collection<SpecProperty> properties)
    {
        return findAll(domainClass, null, null, null, properties);
    }

    public <T> List<Tuple> findAll(Class<T> domainClass, Specification<T> spec, SpecProperty... properties)
    {
        return findAll(domainClass, spec, null, null, properties);
    }

    public <T> List<Tuple> findAll(Class<T> domainClass, Specification<T> spec, Collection<SpecProperty> properties)
    {
        return findAll(domainClass, spec, null, null, properties);
    }

    public <T> List<Tuple> findAll(Class<T> domainClass, Sort sort, SpecProperty... properties)
    {
        return findAll(domainClass, null, sort, null, properties);
    }

    public <T> List<Tuple> findAll(Class<T> domainClass, Sort sort, Collection<SpecProperty> properties)
    {
        return findAll(domainClass, null, sort, null, properties);
    }

    public <T> List<Tuple> findAll(Class<T> domainClass, Specification<T> spec, Sort sort, Collection<String> groupBys, SpecProperty... properties)
    {
        return findAll(domainClass, spec, sort, groupBys, Arrays.asList(properties));
    }

    public <T> List<Tuple> findAll(Class<T> domainClass, Specification<T> spec, Sort sort, Collection<String> groupBys, Collection<SpecProperty> properties)
    {
        if ((properties != null) && (properties.size() > 0))
        {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Tuple> query = cb.createTupleQuery();
            Root<T> root = query.from(domainClass);
            List<Selection<?>> selections = new ArrayList<Selection<?>>(properties.size());
            properties.forEach(specProperty ->
            {
                switch (specProperty.getSpecType())
                {
                case COUNT:
                    selections.add(cb.count(root.get(specProperty.getPropertyName())).alias(specProperty.getAliasName()));
                    break;
                case SUM:
                    selections.add(cb.sum(root.get(specProperty.getPropertyName())).alias(specProperty.getAliasName()));
                    break;
                default:
                    selections.add(root.get(specProperty.getPropertyName()).alias(specProperty.getAliasName()));
                    break;
                }
            });
            query.multiselect(selections);
            if (spec != null)
            {
                query.where(spec.toPredicate(root, query, cb));
            }
            if ((groupBys != null) && (groupBys.size() > 0))
            {
                query.groupBy(groupBys.stream().map(groupBy -> root.get(groupBy)).collect(Collectors.toList()));
            }
            if (sort != null)
            {
                query.orderBy(QueryUtils.toOrders(sort, root, cb));
            }
            return em.createQuery(query).getResultList();
        }
        return new ArrayList<Tuple>(0);
    }
}
