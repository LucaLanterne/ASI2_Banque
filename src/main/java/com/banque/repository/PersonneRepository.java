package com.banque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.banque.model.Personne;

@NoRepositoryBean
public interface PersonneRepository <T extends Personne> extends JpaRepository<T, Long>
{
}