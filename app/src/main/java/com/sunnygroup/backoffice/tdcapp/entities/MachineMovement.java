package com.sunnygroup.backoffice.tdcapp.entities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

@Entity(tableName = "machine_movement")
public class MachineMovement implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @NonNull
    private String boardType;

    @NonNull
    private String assetNumber;

    private String serialNumber;


    private Integer locationFromID;
    private Integer locationToID;
    private Integer maxPayout;

    private float boardPercentage;
    private float denom;

    private Integer hardMeterIN;
    private Integer hardMeterOUT;
    private Integer softMeterIN;
    private Integer softMeterOUT;

    private boolean serverResponse;

    private Integer createdByID;

    public MachineMovement(String boardType, String assetNumber, String serialNumber, Integer createdByID) {
        this.boardType = boardType;
        this.assetNumber = assetNumber;
        this.serialNumber = serialNumber;
        this.createdByID = createdByID;
    }

    public MachineMovement(JSONObject object) {
        try {
            this.locationFromID = object.getJSONObject("locationFrom").getInt("id");
            this.locationToID = object.getJSONObject("locationTo").getInt("id");
            this.createdByID = object.getJSONObject("createdBy").getInt("id");

            this.boardPercentage = Float.parseFloat(object.getString("boardPercentage"));
            this.denom = Float.parseFloat(object.getString("denom"));

            this.boardType = object.getString("boardType");
            this.assetNumber = object.getString("machineAssetNumber");
            this.serialNumber = object.getString("serialNumber");

            this.hardMeterIN = object.getInt("openingHardMeterIn");
            this.hardMeterOUT = object.getInt("openingHardMeterOut");
            this.softMeterIN = object.getInt("openingSoftMeterIn");
            this.softMeterOUT = object.getInt("openingSoftMeterOut");

            this.maxPayout = object.getInt("maxPayout");

            this.serverResponse = object.getBoolean("serverResponse");

        } catch (JSONException e) {

        }
    }

    public Integer getId() { return this.id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBoardType() { return this.boardType; }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public String getAssetNumber() { return this.assetNumber; }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public String getSerialNumber() { return this.serialNumber; }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getLocationFromID() { return this.locationFromID; }

    public void setLocationFromID(Integer locationFromID) {
        this.locationFromID = locationFromID;
    }

    public Integer getLocationToID() { return this.locationToID; }

    public void setLocationToID(Integer locationToID) {
        this.locationToID = locationToID;
    }

    public Integer getCreatedByID() { return this.createdByID; }

    public void setCreatedByID(Integer createdByID) {
        this.createdByID = createdByID;
    }

    public Integer getMaxPayout() { return this.maxPayout; }

    public void setMaxPayout(Integer maxPayout) {
        this.maxPayout = maxPayout;
    }

    public float getBoardPercentage() { return this.boardPercentage; }

    public void setBoardPercentage(float boardPercentage) {
        this.boardPercentage = boardPercentage;
    }

    public float getDenom() { return this.denom; }

    public void setDenom(float denom) {
        this.denom = denom;
    }

    public Integer getHardMeterIN() {
        return hardMeterIN;
    }

    public void setHardMeterIN(Integer hardMeterIN) {
        this.hardMeterIN = hardMeterIN;
    }

    public Integer getHardMeterOUT() {
        return hardMeterOUT;
    }

    public void setHardMeterOUT(Integer hardMeterOUT) {
        this.hardMeterOUT = hardMeterOUT;
    }

    public Integer getSoftMeterIN() {
        return softMeterIN;
    }

    public void setSoftMeterIN(Integer softMeterIN) {
        this.softMeterIN = softMeterIN;
    }

    public Integer getSoftMeterOUT() {
        return softMeterOUT;
    }

    public void setSoftMeterOUT(Integer softMeterOUT) {
        this.softMeterOUT = softMeterOUT;
    }

    public boolean isServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(boolean serverResponse) {
        this.serverResponse = serverResponse;
    }
}
