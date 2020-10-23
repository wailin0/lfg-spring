package com.lfg.spring.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {

    private String description;
    private String name;
    private String type;
    private String topic;

}
