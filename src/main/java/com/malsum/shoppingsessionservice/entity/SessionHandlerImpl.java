package com.malsum.shoppingsessionservice.entity;

import org.springframework.stereotype.Component;

import java.util.TreeMap;

@Component
public class SessionHandlerImpl implements SessionHandler {

    private TreeMap<Integer,Session> peopleInSession;//personId inside each session

    public SessionHandlerImpl() {
        peopleInSession = new TreeMap<>();
    }

    @Override
    public void add(Session session){
        this.peopleInSession.putIfAbsent(session.getClientId(), session);
    }

    @Override
    public TreeMap<Integer, Session> getPeopleInSession() {
        return peopleInSession;
    }

    @Override
    public void setPeopleInSession(TreeMap<Integer, Session> peopleInSession) {
        this.peopleInSession = peopleInSession;
    }

    @Override
    public Session getSession(Integer id){
        return this.peopleInSession.getOrDefault(id,null);
    }

//    @PostConstruct
//    public void testing(){
//        Person person = new Person();
//        person.setId(new Random().nextInt());
//    }
}
