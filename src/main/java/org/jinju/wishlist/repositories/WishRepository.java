package org.jinju.wishlist.repositories;

import org.jinju.wishlist.entities.Wish;
import org.jinju.wishlist.entities.WishId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface WishRepository extends JpaRepository<Wish, WishId>, QuerydslPredicateExecutor<Wish> {

}
