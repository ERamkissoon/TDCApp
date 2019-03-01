package com.sunnygroup.backoffice.tdcapp.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.sunnygroup.backoffice.tdcapp.daos.MachineMovementDao;
import com.sunnygroup.backoffice.tdcapp.database.AppDatabase;
import com.sunnygroup.backoffice.tdcapp.entities.MachineMovement;

import java.util.List;

public class MachineMovementRepository {

    private MachineMovementDao machineMovementDao;
    private LiveData<List<MachineMovement>> allMachineMovements;

    public MachineMovementRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        machineMovementDao = db.machineMovementDao();
        allMachineMovements = machineMovementDao.getAllMachineMovements();
    }

    public LiveData<List<MachineMovement>> getAllMachineMovements() {
        return allMachineMovements;
    }

    public void insert(MachineMovement machineMovement) {
        new insertAsyncTask(machineMovementDao).execute(machineMovement);
    }

    private static class insertAsyncTask extends AsyncTask<MachineMovement, Void, Void> {

        private MachineMovementDao mAsyncTaskDao;

        insertAsyncTask(MachineMovementDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MachineMovement... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
