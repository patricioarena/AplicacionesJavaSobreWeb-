package com.example.demo.controllers;

import com.example.demo.Application.IServices.ICommentService;
import com.example.demo.DataAccess.Models.Comment;
import com.example.demo.Domain.CommentDto;
import com.example.demo.Domain.Datawrapper;
import com.example.demo.Domain.RoleDto;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comment")
@CrossOrigin("*")
public class CommentController {

    private ICommentService _commentService;

    public CommentController(ICommentService commentService) {
        this._commentService = commentService;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Datawrapper<List<CommentDto>>> getAll(){
        List<CommentDto> list = _commentService.getAllComments();
        Datawrapper<List<CommentDto>> response = new Datawrapper<>(list);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Datawrapper<CommentDto>> find(@PathVariable String id){
        CommentDto commentDto = _commentService.get(id);
        Datawrapper<CommentDto> response = new Datawrapper<>(commentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Datawrapper<CommentDto>> save(@RequestBody CommentDto commentDto) {
        CommentDto resultCommentDto= _commentService.save(commentDto);
        Datawrapper<CommentDto> response = new Datawrapper<>(resultCommentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Datawrapper<CommentDto>> delete(@PathVariable String id){
        CommentDto resultCommentDto = _commentService.deleted(id);
        Datawrapper<CommentDto> response = new Datawrapper<>(resultCommentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
