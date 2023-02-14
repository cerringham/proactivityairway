package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
