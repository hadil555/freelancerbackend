package tn.essat.Dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.essat.entities.Message;
import tn.essat.entities.User;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MessageDto {
	private Long id;
	private Long idUsersender;
	private Long iduserrecipient;
	
	private String content;
	private Date sentAt;
	public static Message toEntity(MessageDto messagedto) {
        return Message.builder()
               
                .content(messagedto.getContent())
                .sentAt(messagedto.getSentAt())
                .build();
    };
    public static MessageDto toDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .content(message.getContent())
                .sentAt(message.getSentAt())
                
                .build();
    };
    

	
	

}
