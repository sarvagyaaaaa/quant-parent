package org.dt.core.parent.model;

import lombok.Data;

import java.util.List;
@Data
public class LlamaRequestTO {

    private String model;
    private List<LlamaMessageRequestTO> messages;
}
