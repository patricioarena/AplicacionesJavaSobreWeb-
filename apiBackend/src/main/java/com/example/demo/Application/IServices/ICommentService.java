package com.example.demo.Application.IServices;

import com.example.demo.DataAccess.Models.Comment;
import com.example.demo.Domain.CommentDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface ICommentService extends IGenericService<Comment, ObjectId>{
    List<CommentDto> getAllComments();
    CommentDto get(String id);
    CommentDto save(CommentDto commentDto);
    CommentDto deleted(String id);
}
