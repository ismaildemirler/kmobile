package tr.com.deneme.kmobile.persistence.firm;

import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.util.List;

import tr.com.deneme.kmobile.models.Firms;
import tr.com.deneme.kmobile.models.request.FirmsRequest;

public interface IFirmDataSource {

    long[] insertFirms(Firms... firms);

    void insertFirm(Firms firm);

    int updateFirm(Firms firms);

    LiveData<List<Firms>> getFirms(SupportSQLiteQuery query);

    LiveData<List<Firms>> buildCustomQueryByRequest(FirmsRequest request);
}
