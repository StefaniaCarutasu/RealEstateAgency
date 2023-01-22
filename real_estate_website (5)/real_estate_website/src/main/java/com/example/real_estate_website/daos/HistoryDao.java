package com.example.real_estate_website.daos;

import com.example.real_estate_website.models.JP_AUDIT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryDao extends JpaRepository<JP_AUDIT, Long> {
}
