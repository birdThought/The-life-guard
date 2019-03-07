package com.lifeshs.support.plantform.security.sessionmgr;

import com.lifeshs.pojo.client.Client;
import com.lifeshs.pojo.client.LoginUser;

import org.apache.mina.core.session.IoSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 *   对在线用户的管理
 *  @author duosheng.mo
 *  @DateTime 2016年5月3日 下午4:37:52
 */
public class ClientManager {
    private static ClientManager instance = new ClientManager();
    private Map<String, Client> map = new HashMap<String, Client>();
    private Map<String, IoSession> mianSession = new HashMap<String, IoSession>();
    private Map<String, IoSession> LCHBSession = new HashMap<String, IoSession>();

    private ClientManager() {
    }

    public static ClientManager getInstance() {
        return instance;
    }

    /**
     *
     * @param sessionId
     * @param client
     */
    public void addClinet(String sessionId, Client client) {
        map.put(sessionId, client);
    }

    /**
     * sessionId
     */
    public void removeClinet(String sessionId) {
        map.remove(sessionId);
    }

    /**
     *
     * @param sessionId
     * @return
     */
    public Client getClient(String sessionId) {
        return map.get(sessionId);
    }

    /**
     *
     * @return
     */
    public Collection<Client> getAllClient() {
        return map.values();
    }

    /**
     *  @author duosheng.mo
     *        @DateTime 2016年5月3日 下午4:51:56
     *  @serverCode 服务代码
     *  @serverComment 获取 Session 中的用户信息
     *
     *  @return
     */
    public static final LoginUser getSessionUser() {
        Client client = getClient();

        if (client != null) {
            return client.getUser();
        }

        return null;
    }

    /*
    
        */

    /**
    *  @author zhiguo.lin
    *        @DateTime 2016年8月31日 上午10:06:44
    *  @serverComment 计算用户年龄
    *
    *  @param birth
    *  @return
    */

    /*
     public static Integer getAge(Date birth) {
             Calendar calendar = Calendar.getInstance();
             // 获取现在的年份
             int yearNow = calendar.get(Calendar.YEAR);
             // 将日期设置为生日那一天
             Integer age = null;
             if(birth != null){
                     calendar.setTime(birth);
                     int yearBirth = calendar.get(Calendar.YEAR);
                     // 通过年龄区间判断获取min值与max值
                     age = yearNow - yearBirth; // 计算年龄
             }
             return age;
     }*/

    /**
     *  @author duosheng.mo
     *        @DateTime 2016年5月3日 下午5:03:48
     *  @serverCode 服务代码
     *  @serverComment 获取在用户
     *
     *  @return
     */
    public static final Client getClient() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        if (session != null) {
            String sessionId = (String) session.getId();

            if (ClientManager.getInstance().getClient(sessionId) != null) {
                return ClientManager.getInstance().getClient(sessionId);
            }
        }

        return null;
    }

    /**
     * 移除在线用户
     */
    public static final void removeClient() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        if (session != null) {
            String sessionId = (String) session.getId();

            if (ClientManager.getInstance().getClient(sessionId) != null) {
                ClientManager.getInstance().removeClinet(sessionId);
            }
        }
    }

    public static Client getCurrentClient() {
        return getClient();
    }

    /**
     *  @author duosheng.mo
     *        @DateTime 2016年7月7日 上午11:48:45
     *  @serverCode 服务代码
     *
     *  @param terminalType 终端类型 1：LCH-B , 2:HL-031
     *  @param key imei
     *  @param session
     */
    public void addMianSession(String terminalType, String key,
        IoSession session) {
        if ("1".equals(terminalType)) {
            if (!LCHBSession.containsKey(key)) {
                LCHBSession.put(key, session);
            }
        } else if ("2".equals(terminalType)) {
            if (!mianSession.containsKey(key)) {
                mianSession.put(key, session);
            }
        }
    }

    /**
     *  @author duosheng.mo
     *        @DateTime 2016年7月7日 上午11:49:34
     *  @serverCode 删除mina会话
     *
     *  @param terminalType 终端类型 1：LCH-B  , 2:HL-031
     *  @param mianSessionId
     */
    public void removeMianSession(String terminalType, long mianSessionId) {
        if ("1".equals(terminalType)) {
            for (String key : LCHBSession.keySet()) {
                IoSession session = LCHBSession.get(key);

                if (mianSessionId == session.getId()) {
                    LCHBSession.remove(key);

                    break;
                }
            }
        } else if ("2".equals(terminalType)) {
            for (String key : mianSession.keySet()) {
                IoSession session = mianSession.get(key);

                if (mianSessionId == session.getId()) {
                    mianSession.remove(key);

                    break;
                }
            }
        }
    }

    /**
     *  @author duosheng.mo
     *        @DateTime 2016年7月7日 上午11:50:06
     *  @serverCode 获取mina 会话
     *
     *  @param terminalType 终端类型 1：LCH-B  , 2:HL-031
     *  @param key
     *  @return
     */
    public IoSession getMianSession(String terminalType, String key) {
        IoSession session = null;

        if ("1".equals(terminalType)) {
            session = LCHBSession.get(key);
        } else if ("2".equals(terminalType)) {
            session = mianSession.get(key);
        }

        return session;
    }
}
