package com.qln.workreserve.tree.repository;

import com.qln.workreserve.tree.dbo.DictionaryMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryMappingRepository extends JpaRepository<DictionaryMapping, String> {
}
