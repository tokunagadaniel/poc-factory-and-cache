package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class FraudeToggleServiceImpl implements FraudeService {
    @Override
    public boolean activeWhen() {
        return true;
    }

    @Override
    public void execute() {
        System.out.println("Simulando!");
    }
}
