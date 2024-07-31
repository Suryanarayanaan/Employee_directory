package com.example.employeedirectory.service;

import com.example.employeedirectory.entity.Message;
import com.example.employeedirectory.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
}
