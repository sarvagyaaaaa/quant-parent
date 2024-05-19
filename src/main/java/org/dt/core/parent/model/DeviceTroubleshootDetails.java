package org.dt.core.parent.model;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeviceTroubleshootDetails {

    Map<Integer, String> priorityToImpactedParameterIds;

}
