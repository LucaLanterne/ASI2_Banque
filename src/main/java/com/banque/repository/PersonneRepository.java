package com.banque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.banque.model.Personne;

/**
 * Repository JPA générique pour toutes les entités héritant de {@link Personne}.
 * Permet de définir des méthodes communes pour les entités PersonnePhysique et PersonneMorale.
 *
 * @param <T> type d'entité étendant {@link Personne}
 */
@NoRepositoryBean
public interface PersonneRepository<T extends Personne> extends JpaRepository<T, Long> {
}
