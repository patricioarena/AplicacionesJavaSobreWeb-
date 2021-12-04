package com.example.demo.Domain;

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
public class CommentDto {
    private String _id;
    private String _idScorerPerson;
    private String _idScoredPerson;
    private String _idRequirement;
    private String commentDescription;
    private Integer score;
    private Date dateTime;
}
