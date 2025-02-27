package ru.digitos.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.digitos.notification.model.NoticeEntity;

public interface NoticeRepository  extends JpaRepository<NoticeEntity, Long> {
}
