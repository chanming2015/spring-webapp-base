package com.github.chanming2015.utils.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Description: <br/> 
 * Create Date:2016年12月10日 <br/> 
 * Version:1.0.0 <br/> 
 * @author XuMaoSen
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T>
{

}
