package com.springframework.io.repository;


import com.springframework.io.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    UserEntity findByUserId(String userId);

    UserEntity findUserByEmailVerificationToken(String token);

//    Page<UserEntity> findAllByEmailVerificationStatusTrue(Pageable pageableRequest);
    @Query(value = "select * from Users u where u.EMAIL_VERIFICATION_STATUS = 'true'",
            countQuery = "select count(*) from Users u where u.EMAIL_VERIFICATION_STATUS = 'true'",
            nativeQuery = true)
    Page<UserEntity> findAllUsersWithConfirmedEmailAddress(Pageable pageableRequest);

    @Query(value = "select * from Users u where u.first_name = ?1 and u.last_name=?2", nativeQuery = true)
    List<UserEntity> findUserByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "select * from Users u where u.first_name= :firstName and u.last_name= :lastName", nativeQuery = true)
    List<UserEntity> findUserByFirstNameAndLastNameWithNamedParams(
            @Param("firstName")String firstName, @Param("lastName")String lastName);

    // nativeQuery
    @Query(value = "select * from Users u where first_name LIKE %:keyword% or last_name LIKE %:keyword%", nativeQuery = true)
    List<UserEntity> findUserByKeyword(@Param("keyword")String keyword);

    // JPA alternative
    List<UserEntity> findByFirstNameContainingOrLastNameContaining(String partialFirstName, String partialLastName);


}






