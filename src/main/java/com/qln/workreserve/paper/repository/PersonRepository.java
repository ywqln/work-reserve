package com.qln.workreserve.paper.repository;

import com.qln.workreserve.paper.dbo.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述：待描述
 * </p>
 *
 * @author QinLiNa
 * @data 2019/6/7
 */
public interface PersonRepository extends JpaRepository<Person,String> {
    List<Person> findAllByChineseName(String name);
    Person findAByChineseName(String name);
}
