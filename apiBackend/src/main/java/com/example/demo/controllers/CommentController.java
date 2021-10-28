package com.example.demo.controllers;

import com.example.demo.Application.IServices.ICommentService;
import com.example.demo.DataAccess.Models.Comment;
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
    public List<Comment> getAll() {
        return _commentService.getAll();
    }

    @GetMapping(value = "/find/{id}")
    public Comment find(@PathVariable ObjectId id) {
        return _commentService.get(id);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Comment> save(@RequestBody Comment comment) {
        Comment obj = _commentService.save(comment);
        return new ResponseEntity<Comment>(obj, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<Comment> delete(@PathVariable ObjectId id) {
        Comment comment = _commentService.get(id);
        if (comment != null) {
            _commentService.delete(id);
        } else {
            return new ResponseEntity<Comment>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }
}
