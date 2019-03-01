package com.sunnygroup.backoffice.tdcapp.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.sunnygroup.backoffice.tdcapp.entities.MachineMovement;
import com.sunnygroup.backoffice.tdcapp.repositories.MachineMovementRepository;

import java.util.List;

public class MachineMovementViewModel extends AndroidViewModel {

    private MachineMovementRepository machineMovementRepository;

    private LiveData<List<MachineMovement>> allMachineMovements;

    public MachineMovementViewModel (Application application) {
        super(application);
        machineMovementRepository = new MachineMovementRepository(application);
        allMachineMovements = machineMovementRepository.getAllMachineMovements();
    }

    public LiveData<List<MachineMovement>> getAllMachineMovements() { return allMachineMovements; }

    public void insert(MachineMovement machineMovement) { machineMovementRepository.insert(machineMovement); }
}
