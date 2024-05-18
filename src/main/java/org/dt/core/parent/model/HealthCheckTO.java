package org.dt.core.parent.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthCheckTO {

    String staticId;

    String paramName;

    String status;

    String actions;

}
