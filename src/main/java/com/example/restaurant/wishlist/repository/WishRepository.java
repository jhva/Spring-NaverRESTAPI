package com.example.restaurant.wishlist.repository;

import com.example.restaurant.db.MemoryDbRepositoryAbstact;
import com.example.restaurant.wishlist.entity.WishListentity;
import org.springframework.stereotype.Repository;

@Repository //db저장하는곳이다 라고명시
public class WishRepository extends MemoryDbRepositoryAbstact<WishListentity> {
}
