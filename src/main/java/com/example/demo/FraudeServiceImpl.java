package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class FraudeServiceImpl implements FraudeService {
    @Override
    public boolean activeWhen() {
        return false;
    }

    @Override
    public void execute() {
        System.out.println("Real!");
    }
}
