package com.qln.workreserve.paper.repository;

import com.qln.workreserve.paper.dbo.Organization;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @Title: ResearchAreaDao$
 * @Project: csai-api
 * @Description: TODO
 * @Author 秦莉娜
 * @Date 2019/6/5$ 11:01$
 * @Version V2.0
 **/
public interface OrganizationRepository extends JpaRepository<Organization, String> {
    Organization findByCnName(String orgName);
}
