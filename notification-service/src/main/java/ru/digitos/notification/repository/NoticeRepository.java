package ru.digitos.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitos.notification.entity.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
