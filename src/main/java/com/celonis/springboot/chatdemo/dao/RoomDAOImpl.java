package com.celonis.springboot.chatdemo.dao;

import com.celonis.springboot.chatdemo.entity.Room;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoomDAOImpl implements RoomDAO{

    private EntityManager entityManager;

    @Autowired
    public RoomDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Room> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery
                ("from Room ",Room.class);

        List<Room> roomList = theQuery.getResultList();
        return roomList;
    }

    @Override
    public Room findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Room room = currentSession.get(Room.class,id);
        return room;
    }

    @Override
    public void save(Room room) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(room);
    }

    @Override
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery(
                "delete Room where id=:roomId");
        theQuery.setParameter("roomId",id);
        theQuery.executeUpdate();
    }
}
