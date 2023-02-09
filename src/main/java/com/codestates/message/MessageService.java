package com.codestates.message;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message message) {
        // MessageRepository 인터페이스가 상속받은 CrudRepository 인터페이스에 save() 메서드가 정의되어 있다
        return messageRepository.save(message);
    }
}
