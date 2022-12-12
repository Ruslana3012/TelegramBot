package com.company;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Database implements TelegramBotInterface {

    @Override
    public void addChat_idAndGroup_name(Long chat_id, String group_name) {
        session(sessionFactory()).persist(group_name, chat_id);

        closeSession(session(sessionFactory()));
        closeSessionFactory(sessionFactory());
    }

    @Override
    public void updateGroupInDatabase(Long chat_id, String group_name) {
        Transaction transaction = session(sessionFactory()).beginTransaction();
        TelegramBot telegramBot = new TelegramBot();
        telegramBot.setChat_id(chat_id);
        telegramBot.setGroup_name(group_name);

        session(sessionFactory()).saveOrUpdate(telegramBot);
        transaction.commit();

        closeSession(session(sessionFactory()));
        closeSessionFactory(sessionFactory());
    }

    @Override
    public String getGroupFromDatabaseByKey(Long chat_id) {
        String groupName = session(sessionFactory()).get(TelegramBot.class, chat_id).getGroup_name();
        closeSession(session(sessionFactory()));
        closeSessionFactory(sessionFactory());
        return groupName;
    }

    private Session session(SessionFactory sessionFactory) {
        return sessionFactory.openSession();
    }

    private void closeSession(Session session) {
        session.close();
    }

    private SessionFactory sessionFactory() {
        return new Configuration()
                .configure()
                .buildSessionFactory();
    }

    private void closeSessionFactory(SessionFactory sessionFactory) {
        sessionFactory.close();
    }
}