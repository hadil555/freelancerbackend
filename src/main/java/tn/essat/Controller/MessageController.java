package tn.essat.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import tn.essat.Dto.MessageDto;

import tn.essat.exception.MessageNotFoundException;

import tn.essat.service.MessageService;


/**
 * 
 */
@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
@Log
public class MessageController {
	private final MessageService messageService;
	
	  
    @PostMapping("/add")
    public ResponseEntity<MessageDto> addMessage(@RequestBody MessageDto messageDto) {
    	try {
    	  return new ResponseEntity<>( messageService.addMessage(messageDto),HttpStatus.CREATED);
    		
    	}catch(MessageNotFoundException ex){
    		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	
    	
    	
    	
    	
        
       
    }

    
    @GetMapping("/getById/{id}")
    public ResponseEntity<MessageDto> getMessageById(@PathVariable("id") Long id) {
    	try {
    	            return new ResponseEntity<>(messageService.getMessageById(id), HttpStatus.OK); // 200 OK
    	        } catch (MessageNotFoundException e) {
    	            log.info(String.format("Profil with id = %s not found", id));
    	            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 NOT_FOUND
    	        }
    }

    
    @GetMapping("/getAllMessages")
    public ResponseEntity<List<MessageDto>> getAllProfils() {
        return new ResponseEntity<>(messageService.getAllMessage(),HttpStatus.OK);
    }

    
    @PutMapping("/update/{id}")
    public ResponseEntity<MessageDto> updateMessage(@PathVariable Long id, @RequestBody MessageDto messageDto) {
    	try {
            MessageDto updatedMessage = messageService.updateMessageDto(id, messageDto);
            return ResponseEntity.ok(updatedMessage);
        } catch (MessageNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
    	try {
            messageService.deleteMessageById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (MessageNotFoundException e) {
            log.info(String.format("Message with id = %s not found", id));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 NOT_FOUND
        }
        
        
    }
	
	
	
	

}
