package com.examples.listeners;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.example.entities.CarRecord;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarListener {
	@PrePersist
    void onPrePersist(CarRecord carRecord) {
        log.info("BookListener.onPrePersist(): " + carRecord);
    }
    @PostPersist
    void onPostPersist(CarRecord carRecord) {
        log.info("BookListener.onPostPersist(): " + carRecord);
    }
    @PostLoad
    void onPostLoad(CarRecord carRecord) {
        log.info("BookListener.onPostLoad(): " + carRecord);
    }
    @PreUpdate
    void onPreUpdate(CarRecord carRecord) {
        log.info("BookListener.onPreUpdate(): " + carRecord);
    }
    @PostUpdate
    void onPostUpdate(CarRecord carRecord) {
        log.info("BookListener.onPostUpdate(): " + carRecord);
    }
    @PreRemove
    void onPreRemove(CarRecord carRecord) {
        log.info("BookListener.onPreRemove(): " + carRecord);
    }
    @PostRemove
    void onPostRemove(CarRecord carRecord) {
        log.info("BookListener.onPostRemove(): " + carRecord);
    }
}
