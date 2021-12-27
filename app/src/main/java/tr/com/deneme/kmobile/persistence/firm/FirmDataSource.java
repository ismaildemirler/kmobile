package tr.com.deneme.kmobile.persistence.firm;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import tr.com.deneme.kmobile.models.Firms;
import tr.com.deneme.kmobile.models.request.FirmsRequest;
import tr.com.deneme.kmobile.util.TextHelper;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FirmDataSource implements IFirmDataSource {

    @Inject
    TextHelper textHelper;
    private FirmDao firmDao;

    @Inject
    public FirmDataSource(FirmDao firmDao){
        this.firmDao = firmDao;
    }

    @Override
    public long[] insertFirms(Firms... firms) {
        return firmDao.insertFirms(firms);
    }

    @Override
    public void insertFirm(Firms firm) {
        firmDao.insertFirm(firm);
    }

    @Override
    public int updateFirm(Firms firms) {
        return firmDao.updateFirm(firms);
    }

    public  LiveData<List<Firms>> buildCustomQueryByRequest(FirmsRequest request){

        String queryString = new String();

        queryString += "SELECT * FROM Firms WHERE 1=1 ";

        if(request.getFirmId()>0){
            queryString += " AND FirmId = " + request.getFirmId();
        }

        if(!textHelper.isEmpty(request.getFirmName())){
            queryString += " AND FirmName LIKE '%" + request.getFirmName() + "%' ";
        }

        if(!textHelper.isEmpty(request.getTaxNumber())){
            queryString += " AND TaxNumber = " + request.getTaxNumber();
        }

        int pi = request.getPageIndex();
        int ps = request.getPageSize();


        int count = getCountAsBackgroundThread();
        if(count < request.getPageSize()){
            pi = 0;
        }else{
            int totalPage = 0;
            if(count % request.getPageSize() == 0){
                totalPage = count / request.getPageSize();
            }
            else{
                totalPage = count / request.getPageSize() + 1;
            }
            if(totalPage <= request.getPageIndex()){
                pi = totalPage - 1;
            }
            else{
                pi = request.getPageIndex();
            }
        }


        queryString += " order by FirmId desc LIMIT " + ps + " OFFSET " + (pi * ps);

        SupportSQLiteQuery query = new SimpleSQLiteQuery(queryString);
        return getFirms(query);
    }

    @Override
    public LiveData<List<Firms>> getFirms(SupportSQLiteQuery query) {
        return firmDao.getFirms(query);
    }

    private int getCount() {
        return firmDao.getCount();
    }

    private int getCountAsBackgroundThread(){
        final AtomicInteger fcount = new AtomicInteger();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int num = getCount();
                fcount.set(num);
            }
        });
        t.setPriority(10);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return fcount.intValue();
    }
}
