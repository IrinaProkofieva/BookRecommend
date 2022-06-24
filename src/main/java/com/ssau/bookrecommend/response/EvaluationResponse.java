package com.ssau.bookrecommend.response;

import com.ssau.bookrecommend.model.Evaluation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EvaluationResponse {
    Evaluation evaluation;
}
