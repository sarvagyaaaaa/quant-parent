package org.dt.core.parent.model;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HealthCheckResponse {

    List<HealthCheckTO> healthCheckParameters;

}
