package com.openclassrooms.dataLayer.service;

import com.openclassrooms.dataLayer.model.Comment;
import com.openclassrooms.dataLayer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Iterable<Comment> getComments(){
        return commentRepository.findAll();
    }
    public Optional<Comment> getCommentById(Integer id){
        return commentRepository.findById(id);
    }
    public void deleteCommentById(Integer id){
        commentRepository.deleteById(id);
    }

    //Requête JPQL
    public Iterable<Comment> getCommentsByContentLike(String containing){
        return commentRepository.findByContentLike(containing);
    }
    //Requêtes dérivées
    //La recherche des commentaires : dont le contenu contient le mot xxx using "findByContentContaining"
    public Iterable<Comment> getCommentsByContentContaining(String containing){
        return commentRepository.findByContentContaining(containing);
    }

}
