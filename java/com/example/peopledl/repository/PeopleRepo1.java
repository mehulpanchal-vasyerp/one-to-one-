package com.example.peopledl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.peopledl.model.People;

@Repository
public interface PeopleRepo1 extends JpaRepository<People, Long> {

}
