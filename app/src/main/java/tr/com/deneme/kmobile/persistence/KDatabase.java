package tr.com.deneme.kmobile.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import tr.com.deneme.kmobile.models.Firms;
import tr.com.deneme.kmobile.models.SystemUsers;
import tr.com.deneme.kmobile.persistence.firm.FirmDao;
import tr.com.deneme.kmobile.persistence.systemuser.SystemUserDao;
import tr.com.deneme.kmobile.util.Converters;

@Database(entities = {
        Firms.class,
        SystemUsers.class
}, version = 1)
@TypeConverters({Converters.class})
public abstract class KDatabase extends RoomDatabase {

    /*
    @Inject
    private static KDatabase instance;
    */

    public abstract FirmDao getKDao();

    public abstract SystemUserDao getUserDao();
}
