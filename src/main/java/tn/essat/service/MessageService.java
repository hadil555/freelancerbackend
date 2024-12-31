package tn.essat.service;

import java.util.List;

import tn.essat.Dto.MessageDto;
import tn.essat.exception.MessageNotFoundException;

public interface MessageService {
	MessageDto addMessage(MessageDto messageDto) throws  MessageNotFoundException;

	MessageDto getMessageById(Long id) throws MessageNotFoundException;

    List<MessageDto> getAllMessage();

    void deleteMessageById(Long id) throws MessageNotFoundException;

    MessageDto updateMessageDto(Long id, MessageDto messageDto)throws MessageNotFoundException;

}
