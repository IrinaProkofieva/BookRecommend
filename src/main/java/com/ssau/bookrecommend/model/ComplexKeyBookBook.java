package com.ssau.bookrecommend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplexKeyBookBook implements Serializable {
    private Long idBook1;
    private Long idBook2;
}
