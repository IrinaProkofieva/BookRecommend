package com.ssau.bookrecommend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplexKeyUserBook implements Serializable {
    private Long userId;
    private Long bookId;
}
