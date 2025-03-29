package com.codeailabs.plugins.flashlight;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Flashlight")
public class FlashlightPlugin extends Plugin {

    private static final String TAG = "FlashlightPlugin";
    private CameraManager cameraManager;
    private String cameraId;
    private boolean isTorchOn = false;

    @Override
    public void load() {
        Log.d(TAG, "Loading FlashlightPlugin");
        cameraManager = (CameraManager) getContext().getSystemService(Context.CAMERA_SERVICE);
        try {
            // Find camera that has a flash unit
            String[] cameraIds = cameraManager.getCameraIdList();
            for (String id : cameraIds) {
                CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(id);
                Boolean hasFlash = characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                if (hasFlash != null && hasFlash) {
                    cameraId = id;
                    Log.d(TAG, "Found camera with flash: " + cameraId);
                    break;
                }
            }
            
            if (cameraId == null && cameraIds.length > 0) {
                // Fallback to first camera if no camera with flash found
                cameraId = cameraIds[0];
                Log.d(TAG, "No camera with flash found, using first camera: " + cameraId);
            }
            
            if (cameraId == null) {
                Log.e(TAG, "No camera available on device");
            }
        } catch (CameraAccessException e) {
            Log.e(TAG, "Error accessing camera", e);
            e.printStackTrace();
        }
    }

    @PluginMethod
    public void turnOn(PluginCall call) {
        if (cameraId == null) {
            call.reject("No camera with flashlight available");
            return;
        }
        
        try {
            Log.d(TAG, "Turning flashlight ON");
            cameraManager.setTorchMode(cameraId, true);
            isTorchOn = true;
            
            JSObject ret = new JSObject();
            ret.put("value", true);
            call.resolve(ret);
        } catch (CameraAccessException e) {
            Log.e(TAG, "Failed to turn on flashlight", e);
            call.reject("Failed to turn on flashlight: " + e.getMessage(), e);
        }
    }

    @PluginMethod
    public void turnOff(PluginCall call) {
        if (cameraId == null) {
            call.reject("No camera with flashlight available");
            return;
        }
        
        try {
            Log.d(TAG, "Turning flashlight OFF");
            cameraManager.setTorchMode(cameraId, false);
            isTorchOn = false;
            
            // Add a brief delay to ensure the camera hardware has time to respond
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // Ignore interruption
            }
            
            JSObject ret = new JSObject();
            ret.put("value", false);
            call.resolve(ret);
        } catch (CameraAccessException e) {
            Log.e(TAG, "Failed to turn off flashlight", e);
            call.reject("Failed to turn off flashlight: " + e.getMessage(), e);
        }
    }

    @PluginMethod
    public void toggle(PluginCall call) {
        if (cameraId == null) {
            call.reject("No camera with flashlight available");
            return;
        }
        
        try {
            // Toggle the torch state
            isTorchOn = !isTorchOn;
            Log.d(TAG, "Toggling flashlight to: " + (isTorchOn ? "ON" : "OFF"));
            cameraManager.setTorchMode(cameraId, isTorchOn);
            
            // Add a brief delay to ensure the camera hardware has time to respond
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // Ignore interruption
            }
            
            JSObject ret = new JSObject();
            ret.put("value", isTorchOn);
            call.resolve(ret);
        } catch (CameraAccessException e) {
            Log.e(TAG, "Failed to toggle flashlight", e);
            call.reject("Failed to toggle flashlight: " + e.getMessage(), e);
        }
    }
}
