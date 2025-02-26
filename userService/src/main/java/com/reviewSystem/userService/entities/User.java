package com.reviewSystem.userService.entities;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@EntityScan
@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    public String userId;
    public String name;
    public String about;
    public String email;
    @Transient
    public List<Rating> ratings=new ArrayList<>();
}
