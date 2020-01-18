package com.bhis.database;

import com.bhis.model.Bicycle;
import com.bhis.model.HireRecord;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class HireRecordDatabase {
    private static HireRecordDatabase mInstance;

    @Getter
    @Setter
    private List<HireRecord> hireRecordList = null;

    public static HireRecordDatabase getInstance(){
        if(mInstance == null)
            mInstance = new HireRecordDatabase();

        return mInstance;
    }

    private HireRecordDatabase(){
        hireRecordList = new ArrayList<>();
    }

    public boolean addHireRecord(HireRecord hireRecord){
        if(!hireRecordList.contains(hireRecord)) {
            hireRecordList.add(hireRecord);
            return true;
        }
        return false;
    }
}
