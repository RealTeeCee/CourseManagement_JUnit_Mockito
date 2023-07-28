package com.swaggercodegen.swaggercodegenapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swaggercodegen.swaggercodegenapp.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findTagByName(String name);

}
