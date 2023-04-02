package com.anshuman.userservice.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "user_id",length = 30)
    private String userId;

    @Column(name = "user_name",length = 50,nullable = false)
    private String userName;

    @Column(name = "email_id",unique = true,nullable = false)
    private String emailId;

//    @JsonFormat(pattern = "dd/mm/yyyy",shape = JsonFormat.Shape.STRING)

    @Column(name = "data_of_birth",nullable = false)
    private String dob;

    @Column(name = "phone_number",nullable = false,length = 12)
    private String phoneNumber;

}
