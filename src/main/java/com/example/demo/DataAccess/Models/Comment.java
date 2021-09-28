package com.example.demo.DataAccess.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Data
@Getter
@Setter
public class Comment {
    private int _idComment;
    private int _idScorerPerson;
    private int _idScoredPerson;
    private int _idRequirement;
    private String commentDescription;
    private int score;
    private Date dateTime;
}
