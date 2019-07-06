package com.qln.workreserve.paper.repository;

import com.qln.workreserve.paper.dbo.PaperKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Title: $
 * @Project: csai-api
 * @Description: TODO
 * @Author 秦莉娜
 * @Date 2019/6/10$ 17:03$
 * @Version V2.0
 **/
public interface PaperKeywordsRepository extends JpaRepository<PaperKeyword,String> {
    List<PaperKeyword> findAllByKeyword(String keyword);
}
