package com.example.demo.DataAccess.Models;

import com.example.demo.DataAccess.Database;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Getter
@Setter
@Document(collection = Database.commentCollection)
public class Comment {

    private ObjectId _id;
    private ObjectId _idScorerPerson;
    private ObjectId _idScoredPerson;
    private ObjectId _idRequirement;
    private String commentDescription;
    private int score;
    private Date dateTime;

    public Comment() {
    }

    public Comment(ObjectId _id, ObjectId _idScorerPerson, ObjectId _idScoredPerson, ObjectId _idRequirement, String commentDescription, int score, Date dateTime) {
        super();

        this._id = _id;
        this._idScorerPerson = _idScorerPerson;
        this._idScoredPerson = _idScoredPerson;
        this._idRequirement = _idRequirement;
        this.commentDescription = commentDescription;
        this.score = score;
        this.dateTime = dateTime;
    }
}
