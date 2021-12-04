package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.ICommentService;
import com.example.demo.DataAccess.Database;
import com.example.demo.DataAccess.Models.Comment;
import com.example.demo.DataAccess.Models.Offer;
import com.example.demo.DataAccess.Repository.CommentRepository;
import com.example.demo.DataAccess.Repository.CommonResourcesRepository;
import com.example.demo.Domain.CommentDto;
import com.example.demo.Domain.OfferDto;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Configuration
public class CommentService extends GenericService<Comment, ObjectId> implements ICommentService {

    private CommentRepository commentRepository;
    private final CommonResourcesRepository commonResourcesRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommonResourcesRepository commonResourcesRepository) {
        this.commentRepository = commentRepository;
        this.commonResourcesRepository = commonResourcesRepository;
    }

    public List<CommentDto> getAllComments() {
        ModelMapper modelMapper = new ModelMapper();
        Iterable<Comment> iter = this.getRepository().findAll();
        List<CommentDto> listDtos = new ArrayList<>();
        iter.forEach(offer -> listDtos.add(modelMapper.map(offer, CommentDto.class)));
        return listDtos;
    }

    public CommentDto get(String id) {
        ModelMapper modelMapper = new ModelMapper();
        Comment comment = this.getRepository().findById(new ObjectId(id)).get();
        return modelMapper.map(comment, CommentDto.class);
    }

    public CommentDto save(CommentDto commentDto) {
        ModelMapper modelMapper = new ModelMapper();
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.set_id(new ObjectId(commentDto.get_id()));
        var temp1 = this.getRepository().save(comment);
        return modelMapper.map(temp1, CommentDto.class);
    }

    public CommentDto deleted(String id) {
        CommentDto commentDto = new CommentDto();
        var result = this.commonResourcesRepository.deleted(id, Database.commentCollection);
        return (result == 1) ? this.get(id) : commentDto;
    }

    @Override
    public CrudRepository<Comment, ObjectId> getRepository() {
        return commentRepository;
    }

}
