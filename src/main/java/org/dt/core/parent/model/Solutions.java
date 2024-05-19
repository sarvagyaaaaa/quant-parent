package org.dt.core.parent.model;

import lombok.*;

import java.lang.reflect.Parameter;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Solutions {
    List<ParameterSpecificSolution> parameterSpecificSolutions;
}
