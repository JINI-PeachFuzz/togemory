package org.jinju.member.repositories;

import org.jinju.member.entities.Authorities;
import org.jinju.member.entities.AuthoritiesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AuthoritiesRepository extends JpaRepository<Authorities, AuthoritiesId>, QuerydslPredicateExecutor<Authorities> {
}
