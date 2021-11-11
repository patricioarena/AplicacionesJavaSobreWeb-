package com.example.demo.DataAccess.Repository;

import com.example.demo.DataAccess.Models.Comment;
import com.example.demo.Domain.CommentDto;
import com.example.demo.Domain.RoleDto;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Configuration
public interface CommentRepository extends MongoRepository<Comment, ObjectId> {
}
