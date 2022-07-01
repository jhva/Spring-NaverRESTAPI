package com.example.restaurant.wishlist.service;


import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchLocalReeq;
import com.example.restaurant.wishlist.dto.WishListDTo;
import com.example.restaurant.wishlist.entity.WishListEntity;
import com.example.restaurant.wishlist.repository.WishListRepository;
//import com.example.restaurant.wishlist.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishRepository;

    public WishListDTo search(String query) {
        //지역검색

        var searchLocalReq = new SearchLocalReeq();
        searchLocalReq.setQuery(query);


        var SearchLocalRes = naverClient.searchLocal(searchLocalReq);

        if (SearchLocalRes.getTotal() > 0) {
            var localItem = SearchLocalRes.getItems().stream().findFirst().get();

            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>", "");//괄호치고 머머머 다 없애는 정규식표현

            var searchImageReq = new SearchImageReq();
            //이미지 검색

            searchImageReq.setQuery(imageQuery);

            var SearchImageRes = naverClient.searchImageRes(searchImageReq);

            if (SearchImageRes.getTotal() > 0) {

                var imageItem = SearchImageRes.getItems().stream().findFirst().get();
                //결과를 리턴


                var result = new WishListDTo();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setImageLink(localItem.getLink());
                result.setHomePageLink(localItem.getLink());
                return result;
            }

        }
        return new WishListDTo();
    }

    public WishListDTo add(WishListDTo wishListDTo) {
        var entity = dtoToEntity(wishListDTo);
        var saveEntity = wishRepository.save(entity);
        return entityToDto(saveEntity);
    }


    private WishListEntity dtoToEntity(WishListDTo wishListDto) {
        var entity = new WishListEntity();
        entity.setIndex(wishListDto.getIndex());
        entity.setTitle(wishListDto.getTitle());
        entity.setCategory(wishListDto.getCategory());
        entity.setAddress(wishListDto.getAddress());
        entity.setRoadAddress(wishListDto.getRoadAddress());
        entity.setHomePageLink(wishListDto.getHomePageLink());
        entity.setImageLink(wishListDto.getImageLink());
        entity.setVisit(wishListDto.isVisit());
        entity.setVisitCount(wishListDto.getVisitCount());
        entity.setLastVisitDate(wishListDto.getLastVisitDate());
        return entity;
    }

    private WishListDTo entityToDto(WishListEntity wishListEntity) {
        var dto = new WishListDTo();
        dto.setIndex(wishListEntity.getIndex());
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getCategory());
        dto.setAddress(wishListEntity.getAddress());
        dto.setRoadAddress(wishListEntity.getRoadAddress());
        dto.setHomePageLink(wishListEntity.getHomePageLink());
        dto.setImageLink(wishListEntity.getImageLink());
        dto.setVisit(wishListEntity.isVisit());
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVisitDate(wishListEntity.getLastVisitDate());
        return dto;
    }


    public List<WishListDTo> findAll() {
        //엔티티TOList로 바꿔주는거  entity=>dto로 바꿔줌
        return wishRepository.findAll().stream().map(it -> entityToDto(it)).collect(Collectors.toList());
    }

    public void delete(int index){
        wishRepository.deleteById(index);
    }

    public void addVisit(int index){
        var wishItem = wishRepository.findById(index);
        if(wishItem.isPresent()){
            var item =wishItem.get();
//            item.getClass();
//            var item = wishItem.get();
//            item.set
//            item.setVisit
//            item.se
//            var item = WishListEntity;
//        itesetVisit(true);
//            item.setVisitCount(item.getVisitCount()+1);
        }
    }
}
