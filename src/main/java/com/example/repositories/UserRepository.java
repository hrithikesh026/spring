package com.example.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.catalina.User;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.entities.UserRecord;



@Repository
public interface UserRepository extends JpaRepository<UserRecord, Integer>{
//	@Query(value ="SELECT * FROM user_record u WHERE u.name LIKE %:name%", nativeQuery=true)
//	@Query(value ="SELECT * FROM user_record u WHERE u.name LIKE ?1", nativeQuery=true)
	@Query(value ="SELECT u FROM UserRecord u WHERE u.name LIKE %?1%")
	List<UserRecord> findAllUsersWithNameLike(@Param("name") String name);
	
	@Query(value = "SELECT u FROM UserRecord u") //not possible to sort with nativeQuery
	List<UserRecord> findAllUsers(Sort sort);
	
	@Query(value = "SELECT u FROM UserRecord u ORDER BY id") //pagination JPQL
	Page<UserRecord> findAllUsersWithPaginationJPQL(Pageable pageable);
	
	@Query(value = "SELECT * FROM user_record ORDER BY id", //pagination with nativeQuery
			  countQuery = "SELECT count(*) FROM User_Record", 
			  nativeQuery = true)
	Page<UserRecord> findAllUsersWithPaginationNative(Pageable pageable);
	
	List<UserRecord> findAllUsersWithIdGreaterThan(int id);
	
	List<UserRecord> findAllUsersWithIdLessThan(int id);
	
}
