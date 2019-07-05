package com.qln.workreserve.repository;

import com.qln.workreserve.dbo.DictionaryMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryMappingRepository extends JpaRepository<DictionaryMapping, String> {
}
