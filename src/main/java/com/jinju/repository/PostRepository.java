package com.jinju.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jinju.togemory.model.PostEntity;
import com.jinju.togemory.model.UserEntity;

@Repository
public class PostRepository extends JpaRepository<PostEntity, Long> {
    
    List<PostEntity> findByUserEntity(UserEntity userEntity);
	

}

