package com.faceboot.notification_service.dto;


import lombok.*;


public record Email (
        String To,
        String subject,
        String content
){

}
