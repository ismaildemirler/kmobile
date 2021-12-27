package tr.com.deneme.kmobile.persistence.systemuser;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.Date;
import java.util.List;

import tr.com.deneme.kmobile.models.SystemUsers;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface SystemUserDao {
    @Insert(onConflict = IGNORE)
    long[] insertUser(SystemUsers... users);

    @Insert(onConflict = REPLACE)
    void insertUser(SystemUsers user);

    @Update
    int updateUser(SystemUsers user);

    @RawQuery(observedEntities = SystemUsers.class)
    LiveData<List<SystemUsers>> searchUser(SupportSQLiteQuery query);

    @Query("SELECT * FROM SystemUsers WHERE SystemUserId = :userId")
    LiveData<SystemUsers> getUser(int userId);

    @Query("SELECT * FROM SystemUsers WHERE UserName = :userName and Password = :password")
    LiveData<SystemUsers> getUser(String userName, String password);
}
