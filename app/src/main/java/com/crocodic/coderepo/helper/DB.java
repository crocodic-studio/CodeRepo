package com.crocodic.coderepo.helper;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.crocodic.coderepo.model.Fruit;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by yzzzd crocodic on 9/18/17.
 */

public class DB {

    private static DB instance;
    private final Realm realm;

    public DB(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static DB with(Fragment fragment) {
        if (instance == null) {
            instance = new DB(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static DB with(Activity activity) {
        if (instance == null) {
            instance = new DB(activity.getApplication());
        }
        return instance;
    }

    public static DB with(Application application) {
        if (instance == null) {
            instance = new DB(application);
        }
        return instance;
    }

    public static DB getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }

    public void refresh() {
        realm.refresh();
    }

    //// TODO: 10/11/17 BEGIN CRUD

    //// TODO: 10/11/17 CREATE
    public void saveObject(List<? extends RealmObject> object){
        realm.beginTransaction();
        realm.copyToRealm(object);
        realm.commitTransaction();
    }

    public void saveFruit(Fruit object){
        realm.beginTransaction();
        realm.copyToRealm(object);
        realm.commitTransaction();
    }

    //// TODO: 10/11/17 READ
    public List<Fruit> getFruits(){
        return realm.copyFromRealm(realm.where(Fruit.class).findAll());
    }

    public Fruit getFruit(Integer id){
        return realm.copyFromRealm(realm.where(Fruit.class).equalTo("id", id).findFirst());
    }

    //// TODO: 10/11/17 UPDATE
    public void updateObject(List<? extends RealmObject> object){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(object);
        realm.commitTransaction();
    }

    public void updateFruit(Fruit object){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(object);
        realm.commitTransaction();
    }

    public void updateFruit(Integer id, String warna) {
        realm.beginTransaction();
        Fruit object = realm.where(Fruit.class).equalTo("id", id).findFirst();
        object.setWarna(warna);
        realm.commitTransaction();
    }

    //// TODO: 10/11/17 DELETE
    public void deleteFruits() {
        realm.beginTransaction();
        realm.delete(Fruit.class);
        realm.commitTransaction();
    }

    public void deleteFruit(Integer id) {
        realm.beginTransaction();
        Fruit object = realm.where(Fruit.class).equalTo("id", id).findFirst();
        object.deleteFromRealm();
        realm.commitTransaction();
    }

    //// TODO: 10/11/17 CEK JUMLAH DATA
    public long dataSize(Class c){
        return realm.where(c).count();
    }
}
