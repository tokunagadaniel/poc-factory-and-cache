package com.example.demo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Metadata {

    private boolean fraudeActive;

    private boolean contaActive;

    public Metadata() {

    }

    public Metadata(boolean fraudeActive, boolean contaActive) {
        this.fraudeActive = fraudeActive;
        this.contaActive = contaActive;
    }
}
