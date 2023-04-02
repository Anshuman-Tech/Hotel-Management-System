package com.anshuman.userservice.Repository;

import com.anshuman.userservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

   User findByUserName(String userName);

   @Transactional
   @Modifying
   @Query(value = "update user u set phone_number= :PhoneNumber where u.user_id= :userId",nativeQuery = true)
   void updateUserPhoneNumber(String userId,String PhoneNumber);
}
