package com.example.employeedirectory.repository;

import com.example.employeedirectory.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
