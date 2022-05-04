package com.openclassrooms.dataLayer.repository;

import com.openclassrooms.dataLayer.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
