package tn.essat.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tn.essat.Dto.MessageDto;
import tn.essat.Repository.MessageRepository;
import tn.essat.Repository.UserRepository;
import tn.essat.entities.Message;
import tn.essat.entities.User;
import tn.essat.service.MessageService;

@Service
@RequiredArgsConstructor

public class MessageServiceImpl implements MessageService{
	
	
	private final MessageRepository messageRepository;
	private final UserRepository userRepository;
	
	@Override
	public MessageDto addMessage(MessageDto messageDto) {
		Message message=MessageDto.toEntity(messageDto);
		
		Optional<User>userOptional=userRepository.findById(messageDto.getIdUsersender());
		Optional<User>userrecipientOptional=userRepository.findById(messageDto.getIduserrecipient());
	    


		if(userOptional.isPresent()) {
		
			message.setSender(userOptional.get());
			message.setUserrecipient(userrecipientOptional.get());
			Message messagesaved=messageRepository.save(message);
			return MessageDto.toDto(messagesaved);
		}else {
			throw new RuntimeException("notfound");
		}
		
		
	}

	@Override
	public MessageDto getMessageById(Long id) {
		Optional<Message>message = messageRepository.findById(id);
		if(message.isPresent()) {
			return MessageDto.toDto(message.get());
		}else {
			throw new RuntimeException("notfound");
		}
		
		
	}

	@Override
	public List<MessageDto> getAllMessage() {
		
		return messageRepository.findAll().stream()
				.map(MessageDto::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteMessageById(Long id) {
		getMessageById(id);
		messageRepository.deleteById(id);
		
	}

	@Override
	public MessageDto updateMessageDto(Long id, MessageDto messageDto) {
		       Message messageExisted = messageRepository.findById(id)
			 .orElseThrow(RuntimeException:: new);
		       
		       messageExisted.setContent(messageDto.getContent());
		       messageExisted.setSentAt(messageDto.getSentAt());
		       
			 Message updatedMessage = messageRepository.save(messageExisted);
			 return MessageDto.toDto(updatedMessage);
			
			 
				 
				 
	
	}

}
