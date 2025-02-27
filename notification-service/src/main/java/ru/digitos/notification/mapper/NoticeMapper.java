package ru.digitos.notification.mapper;

import org.springframework.stereotype.Component;
import ru.digitos.notification.model.NoticeEntity;

import java.util.HashMap;

@Component
public class NoticeMapper {
    public HashMap<String, String> toMap(NoticeEntity notice){
        HashMap<String, String> taskMap = new HashMap<>();
        //taskMap.put("userId", String.valueOf(notice.getReporterID()));
        //taskMap.put("title", String.valueOf(notice.getTitle()));
        //taskMap.put("status", String.valueOf(notice.getStatus()));
        return taskMap;
    }
}
