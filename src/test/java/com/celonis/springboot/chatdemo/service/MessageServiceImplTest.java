package com.celonis.springboot.chatdemo.service;

import com.celonis.springboot.chatdemo.dao.ClientRepository;
import com.celonis.springboot.chatdemo.dao.MessageRepository;
import com.celonis.springboot.chatdemo.dao.RoomRepository;
import com.celonis.springboot.chatdemo.entity.Client;
import com.celonis.springboot.chatdemo.entity.Message;
import com.celonis.springboot.chatdemo.entity.MessageHelper;
import com.celonis.springboot.chatdemo.entity.Room;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MessageServiceImplTest {
    @Mock
    private MessageRepository messageRepository;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private RoomService roomService;
    @Mock
    private ClientService clientService;

    @Autowired
    private MessageService underTest;

    @BeforeEach
    void setUp()
    {
        underTest = new MessageServiceImpl(messageRepository, clientRepository, roomRepository);
    }

    @Test
    public void shouldAddMessage()
    {
        MessageHelper messageRequest = new MessageHelper(
                "test message",
                1,
                1
        );

        String messageText = messageRequest.getMessage();
        Room room = new Room();
        Client client = new Client();
        given(roomService.findById(messageRequest.getRoomId()))
                .willReturn(room);

        underTest.save(messageRequest);

        Message expectedMessage = new Message(messageText, client, room);
        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
        verify(messageRepository).save(captor.capture());
        Message capturedMessage = captor.getValue();

        assertThat(capturedMessage).usingRecursiveComparison().isEqualTo(expectedMessage);
    }


}
