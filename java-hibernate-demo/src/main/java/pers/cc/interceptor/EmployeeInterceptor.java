package pers.cc.interceptor;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import pers.cc.entity.Employee;

/**
 * 拦截器
 * 
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年7月22日
 * @Version:1.0.0
 */
public class EmployeeInterceptor extends EmptyInterceptor {

    /**  
     *   
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) {
        System.out.println("删除id:" + id);
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id,
            Object[] currentState, Object[] previousState,
            String[] propertyNames, Type[] types) {
        System.out.println("Update Operation:" + id);
        if (entity instanceof Employee) {
            System.out.println("Update Operation");
            return true;
        }
        return false;
    }

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) {
        // TODO Auto-generated method stub
        return super.onLoad(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) {
        System.out.println("更新或保存id:" + id + ",保存实体" + Object.class.getName());
        return true;
    }

    @Override
    public void postFlush(Iterator entities) {
        // TODO Auto-generated method stub
        super.postFlush(entities);
    }

    @Override
    public void preFlush(Iterator entities) {
        // TODO Auto-generated method stub
        super.preFlush(entities);
    }

    @Override
    public Boolean isTransient(Object entity) {
        // TODO Auto-generated method stub
        return super.isTransient(entity);
    }

    @Override
    public Object instantiate(String entityName, EntityMode entityMode,
            Serializable id) {
        // TODO Auto-generated method stub
        return super.instantiate(entityName, entityMode, id);
    }

    @Override
    public int[] findDirty(Object entity, Serializable id,
            Object[] currentState, Object[] previousState,
            String[] propertyNames, Type[] types) {
        // TODO Auto-generated method stub
        return super.findDirty(entity, id, currentState, previousState,
                propertyNames, types);
    }

    @Override
    public String getEntityName(Object object) {
        // TODO Auto-generated method stub
        return super.getEntityName(object);
    }

    @Override
    public Object getEntity(String entityName, Serializable id) {
        // TODO Auto-generated method stub
        return super.getEntity(entityName, id);
    }

    @Override
    public void afterTransactionBegin(Transaction tx) {
        // TODO Auto-generated method stub
        super.afterTransactionBegin(tx);
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {
        // TODO Auto-generated method stub
        super.afterTransactionCompletion(tx);
    }

    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        // TODO Auto-generated method stub
        super.beforeTransactionCompletion(tx);
    }

    @Override
    public String onPrepareStatement(String sql) {
        // TODO Auto-generated method stub
        return super.onPrepareStatement(sql);
    }

    @Override
    public void onCollectionRemove(Object collection, Serializable key)
            throws CallbackException {
        // TODO Auto-generated method stub
        super.onCollectionRemove(collection, key);
    }

    @Override
    public void onCollectionRecreate(Object collection, Serializable key)
            throws CallbackException {
        // TODO Auto-generated method stub
        super.onCollectionRecreate(collection, key);
    }

    @Override
    public void onCollectionUpdate(Object collection, Serializable key)
            throws CallbackException {
        // TODO Auto-generated method stub
        super.onCollectionUpdate(collection, key);
    }

}
