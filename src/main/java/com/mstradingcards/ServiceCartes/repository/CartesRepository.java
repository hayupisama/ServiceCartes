package com.mstradingcards.ServiceCartes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mstradingcards.ServiceCartes.enums.Rarity;
import com.mstradingcards.ServiceCartes.models.Cartes;

public interface CartesRepository extends JpaRepository<Cartes, Long> {

	List<Cartes> findByName(String name);

	List<Cartes> findByRarity(Rarity rarity);

	List<Cartes> findByAttackGreaterThan(int attackValue);

	List<Cartes> findByHealthLessThan(int healthValue);
	
	@Query("SELECT c FROM Cartes c WHERE c.name LIKE %:keyword% OR c.description LIKE %:keyword%")
    List<Cartes> findByNameOrDescription(@Param("keyword") String keyword);
}
