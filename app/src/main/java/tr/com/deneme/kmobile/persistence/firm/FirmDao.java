package tr.com.deneme.kmobile.persistence.firm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

import tr.com.deneme.kmobile.models.Firms;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface FirmDao {
    @Insert(onConflict = IGNORE)
    long[] insertFirms(Firms... firms);

    @Insert(onConflict = REPLACE)
    void insertFirm(Firms firm);

    @Update
    int updateFirm(Firms firms);

    @Query("SELECT COUNT(*) FROM Firms")
    int getCount();

    @RawQuery(observedEntities = Firms.class)
    LiveData<List<Firms>> getFirms(SupportSQLiteQuery query);
}
