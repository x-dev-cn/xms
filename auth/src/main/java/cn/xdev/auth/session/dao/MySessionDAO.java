package cn.xdev.auth.session.dao;

import cn.xdev.auth.session.model.Sessions;
import cn.xdev.auth.utils.SerializableUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * <p>User: Felix.Hsu
 * <p>Date: 14-2-8
 * <p>Version: 1.0
 */
public class MySessionDAO extends CachingSessionDAO {

    @Autowired
    private SessionsMapper sessionsMapper;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        Sessions sessions = new Sessions();
        sessions.setId(sessionId.toString());
        sessions.setSession(SerializableUtils.serialize(session));
        sessionsMapper.insert(sessions);
        return session.getId();
    }
    @Override
    protected void doUpdate(Session session) {
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return; //如果会话过期/停止 没必要再更新了
        }
        Sessions sessions = new Sessions();
        sessions.setId(session.getId().toString());
        sessions.setSession(SerializableUtils.serialize(session));
        sessionsMapper.updateByPrimaryKeySelective(sessions);
    }
    @Override
    protected void doDelete(Session session) {
        sessionsMapper.deleteByPrimaryKey(session.getId().toString());
    }
    @Override
    protected Session doReadSession(Serializable sessionId) {
        Sessions sessions;
        sessions = sessionsMapper.selectByPrimaryKey(sessionId.toString());

        if(sessions == null) return null;

        return SerializableUtils.deserialize(sessions.getSession());
    }
}
