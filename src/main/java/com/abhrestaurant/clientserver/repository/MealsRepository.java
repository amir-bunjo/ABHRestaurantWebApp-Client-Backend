package com.abhrestaurant.clientserver.repository;

        import com.abhrestaurant.clientserver.model.Meal;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface MealsRepository extends JpaRepository<Meal, Long> {
}
