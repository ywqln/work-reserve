package com.qln.workreserve.paper.repository;

import com.qln.workreserve.paper.dbo.PaperAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述：待描述
 * </p>
 *
 * @author QinLiNa
 * @data 2019/6/6
 */
public interface PaperAuthorRepository extends JpaRepository<PaperAuthor, String> {
    List<PaperAuthor> findAllByPaperIdIn(List<String> paperIds);
    List<PaperAuthor> findAllByPersonIdIn(List<String> personIds);

    List<PaperAuthor> findAllByPersonId(String personIds);
}
