package com.qln.workreserve.paper.repository;

import com.qln.workreserve.paper.dbo.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述：待描述
 * </p>
 *
 * @author QinLiNa
 * @data 2019/6/6
 */
public interface PaperRepository extends JpaRepository<Paper, String> {
    List<Paper> findAllByChineseTitle(String chineseTitle);

    List<Paper> findAllByAchievementId(String achievementId);
    Paper findByChineseTitle(String chineseTitle);
    Paper findByAchievementId(String achievementId);
}
