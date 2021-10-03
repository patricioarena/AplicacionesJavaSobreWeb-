package com.example.demo.DataAccess.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
@Getter
@Setter
public class Comment {
    private ObjectId _idComment;
    private ObjectId _idScorerPerson;
    private ObjectId _idScoredPerson;
    private ObjectId _idRequirement;
    private String commentDescription;
    private int score;
    private Date dateTime;
}
