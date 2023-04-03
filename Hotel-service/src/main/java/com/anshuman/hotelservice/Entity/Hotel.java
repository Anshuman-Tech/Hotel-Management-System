package com.anshuman.hotelservice.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "hotel")
public class Hotel{

    @Id
    @Column(name = "hotel_id")
    private String hotelId;

    @Column(name = "hotel_name",nullable = false)
    private String hotelName;

    @Column(name = "about")
    private String about;

    @Column(name = "address")
    private String address;

}
