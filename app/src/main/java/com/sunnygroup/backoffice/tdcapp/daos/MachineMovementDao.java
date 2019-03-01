package com.sunnygroup.backoffice.tdcapp.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.sunnygroup.backoffice.tdcapp.entities.MachineMovement;

import java.util.List;

@Dao
public interface MachineMovementDao {

    @Insert
    void insert(MachineMovement machineMovement);

    @Query("DELETE FROM machine_movement")
    void deleteAll();

    @Query("SELECT * FROM machine_movement ORDER BY assetNumber DESC")
    LiveData<List<MachineMovement>> getAllMachineMovements();

    @Query("SELECT * FROM machine_movement WHERE createdByID = :id ORDER BY assetNumber ASC")
    LiveData<List<MachineMovement>> getAllMachineMovementsByCreator(Integer id);
}
