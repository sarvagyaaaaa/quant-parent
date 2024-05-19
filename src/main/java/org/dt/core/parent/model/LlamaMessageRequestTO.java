package org.dt.core.parent.model;

import lombok.Data;

@Data
public class LlamaMessageRequestTO {
    private String role;
    private String content;
}
