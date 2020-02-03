package io.hamzaoui.graphqldemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Hamza Ouni
 */
@Repository
interface FoodRepository extends JpaRepository<Food, Long> {
}
