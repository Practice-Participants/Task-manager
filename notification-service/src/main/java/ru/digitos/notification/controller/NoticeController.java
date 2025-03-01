package ru.digitos.notification.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.digitos.notification.model.NoticeEntity;
import ru.digitos.notification.repository.NoticeRepository;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeRepository noticeRepository;
    public NoticeController(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @GetMapping
    public List<NoticeEntity> getAllNotice() {
        return noticeRepository.findAll();
    }

}
