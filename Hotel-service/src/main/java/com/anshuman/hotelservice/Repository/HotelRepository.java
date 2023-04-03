package com.anshuman.hotelservice.Repository;

import com.anshuman.hotelservice.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,String> {

    Hotel findByHotelName(String name);
}
