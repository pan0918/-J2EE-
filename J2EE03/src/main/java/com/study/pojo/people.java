package com.study.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class people {
    private Integer id;
    private String number;
    private String name;
    private String classNumber;
    private String phoneNumber;
    private String email;
}
