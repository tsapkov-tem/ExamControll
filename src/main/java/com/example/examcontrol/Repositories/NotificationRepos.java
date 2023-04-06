package com.example.examcontrol.Repositories;

import com.example.examcontrol.Models.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepos extends CrudRepository<Notification, String> {
    List<Notification> findAllNotificationByViewedAndIdCamIn(Boolean viewed,List<String> ids);
}
