package db;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import db.GiftDB;

import db.GiftDBDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig giftDBDaoConfig;

    private final GiftDBDao giftDBDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        giftDBDaoConfig = daoConfigMap.get(GiftDBDao.class).clone();
        giftDBDaoConfig.initIdentityScope(type);

        giftDBDao = new GiftDBDao(giftDBDaoConfig, this);

        registerDao(GiftDB.class, giftDBDao);
    }
    
    public void clear() {
        giftDBDaoConfig.getIdentityScope().clear();
    }

    public GiftDBDao getGiftDBDao() {
        return giftDBDao;
    }

}
