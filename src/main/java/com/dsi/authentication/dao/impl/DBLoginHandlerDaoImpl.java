package com.dsi.authentication.dao.impl;

import com.dsi.authentication.dao.DBLoginHandlerDao;
import com.dsi.authentication.model.Login;
import com.dsi.authentication.util.PasswordSaltUtil;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created by sabbir on 6/15/16.
 */
public class DBLoginHandlerDaoImpl extends BaseDao implements DBLoginHandlerDao {

    private static final Logger logger = Logger.getLogger(DBLoginHandlerDaoImpl.class);

    @Override
    public Login getLoginInfo(String email, String password) {
        Session session = null;
        Login login = null;
        try {
            session = getSession();
            Query query = session.createQuery("FROM Login l WHERE l.email =:email");
            query.setParameter("email", email);

            login = (Login) query.uniqueResult();
            if(login != null){
                String hash = PasswordSaltUtil.hash(password, login.getSalt());
                if(!hash.equals(login.getPassword())){
                    login = null;
                }
            }
        } catch (Exception e) {
            logger.error("Database error occurs when get: " + e.getMessage());
        } finally {
            if(session != null) {
                close(session);
            }
        }
        return login;
    }
}
