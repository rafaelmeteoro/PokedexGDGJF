package br.com.rafael.pokedexgdgjf.data.local;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import timber.log.Timber;

/**
 * Created by rafael on 8/29/16.
 **/
public class RealmHelper {

    public static final String POKEDEX_DB_NAME = "pokedex_db.realm";
    public static final int POKEDEX_DB_VERSION = 1;

    private static Realm realm;

    public static void instanceRealm() {
        realm = Realm.getDefaultInstance();
    }

    public static void closeRealm() {
        if (realm != null) {
            realm.close();
        }
    }

    public static void beginTransaction() {
        realm.beginTransaction();
    }

    public static void commitTransaction() {
        realm.commitTransaction();
    }

    /**
     * Salva um objeto no Realm.
     *
     * @param object
     * */
    public static boolean saveObject(RealmObject object) {
        try {
            beginTransaction();
            realm.copyToRealmOrUpdate(object);
            commitTransaction();
            return true;
        } catch (Exception e) {
            Timber.e(e, "Erro ao salvar objeto");
            return false;
        }
    }

    /**
     * Retorna um objeto do Realm.
     *
     * @param T
     * @return Retorna um objeto
     * */
    public static RealmObject findFirst(Class T) {
        try {
            return (RealmObject) realm.where(T).findFirst();
        } catch (Exception e) {
            Timber.e(e, "Erro ao recuperar objeto");
            return null;
        }
    }

    /**
     * Retorna um objeto do Realm.
     *
     * @param T
     * @param field
     * @param value
     * @return Retorna um objeto
     * */
    public static RealmObject findFirstByField(Class T, String field, String value) {
        try {
            return (RealmObject) realm.where(T).equalTo(field, value).findFirst();
        } catch (Exception e) {
            Timber.e(e, "Erro ao recuperar objeto");
            return null;
        }
    }

    /**
     * Retorna um objeto do Realm.
     *
     * @param T
     * @param field
     * @param value
     * @return Retorna um objeto
     * */
    public static RealmObject findFirstByField(Class T, String field, int value) {
        try {
            return (RealmObject) realm.where(T).equalTo(field, value).findFirst();
        } catch (Exception e) {
            Timber.e(e, "Erro ao recuperar objeto");
            return null;
        }
    }

    /**
     * Retorna um objeto do Realm.
     *
     * @param T
     * @return Retorna uma cópia do objeto no banco
     * */
    public static RealmObject copyFirst(Class T) {
        try {
            RealmQuery realmQuery = realm.where(T);
            return (RealmObject) realm.copyFromRealm(realmQuery.findFirst());
        } catch (Exception e) {
            Timber.e(e, "Erro ao recuperar objeto");
            return null;
        }
    }

    public static List findAll(Class T) {
        try {
            RealmResults results = realm.where(T).findAll();
            return realm.copyFromRealm(results);
        } catch (Exception e) {
            Timber.e(e, "Erro ao recuperar lista de objetos");
            return null;
        }
    }

    public static List findAllSortedBy(Class T, String sorted) {
        try {
            RealmResults results = realm.where(T).findAllSorted(sorted);
            return realm.copyFromRealm(results);
        } catch (Exception e) {
            Timber.e(e, "Erro ao recuperar lista de objetos");
            return null;
        }
    }

    /**
     * Delete um objeto do Realm.
     *
     * @param object
     * @return true se Objeto deletado, false caso contrário
     * */
    public static boolean delete(RealmObject object) {
        try {
            beginTransaction();
            object.deleteFromRealm();
            commitTransaction();
            return true;
        } catch (Exception e) {
            Timber.e(e, "Erro ao deletar objeto");
            return false;
        }
    }
}
