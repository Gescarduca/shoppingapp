package com.malsum.shoppingsessionservice.entity;

import java.util.TreeMap;

public interface SessionHandler {
    void add(Session session);

    TreeMap<Integer, Session> getPeopleInSession();

    void setPeopleInSession(TreeMap<Integer, Session> peopleInSession);

    Session getSession(Integer id);
}
