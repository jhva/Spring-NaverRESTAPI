package com.example.restaurant.wishlist.repository;

import com.example.restaurant.db.MemoryDbRepositoryAbstract;
//import com.example.restaurant.db.MemoryDbRepositoryAbstract;
import com.example.restaurant.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository //db저장하는곳이다 라고명시
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {
}