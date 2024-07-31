package com.example.employeedirectory.service;

import com.example.employeedirectory.entity.Message;

import java.util.List;

public interface MessageService {

    List<Message> getAllMessages();
    void saveMessage(Message message);
}
