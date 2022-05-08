package com.openclassrooms.dataLayer.repository;

import com.openclassrooms.dataLayer.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

    //La recherche des commentaires : dont le contenu contient le mot %?1
    @Query("FROM Comment where content LIKE %?1")
    Iterable<Comment> findByContentLike(String content);

    //La recherche des commentaires : dont le contenu contient le mot xxx using "findByContentContaining"
    Iterable<Comment> findByContentContaining(String content);

}
