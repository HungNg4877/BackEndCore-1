package com.example.demo.Repository;

import com.example.demo.Entity.BoardingHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface BoardingHouseRepository extends JpaRepository<BoardingHouse, UUID> {

    @Query("SELECT b FROM BoardingHouse b WHERE b.id = :id AND b.isDelete != true ")
    BoardingHouse findBoardingHouseById(@Param("id") UUID id);

    @Query("SELECT count(b.id) FROM BoardingHouse b WHERE b.boardingHouseName = :name and b.isDelete != true ")
    int isBoardingHouseNameExists(@Param("name") String name);
    @Query("SELECT b FROM BoardingHouse b WHERE b.user.id = :id AND b.isDelete != true ")
    Optional<BoardingHouse> findByUser(@Param("id") UUID id);

}