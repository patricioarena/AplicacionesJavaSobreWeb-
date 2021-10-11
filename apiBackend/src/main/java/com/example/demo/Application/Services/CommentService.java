package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.ICommentService;
import com.example.demo.DataAccess.Models.Comment;
import com.example.demo.DataAccess.Repository.CommentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class CommentService extends GenericService<Comment, ObjectId> implements ICommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CrudRepository<Comment, ObjectId> getRepository() {

        return commentRepository;
    }

}
