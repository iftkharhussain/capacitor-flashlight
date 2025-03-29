import AVFoundation

@objc(FlashlightPlugin)
public class FlashlightPlugin: CAPPlugin {

    @objc func turnOn(_ call: CAPPluginCall) {
        guard let device = AVCaptureDevice.default(for: .video), device.hasTorch else {
            call.reject("Torch is not available on this device")
            return
        }
        do {
            try device.lockForConfiguration()
            device.torchMode = .on
            device.unlockForConfiguration()
            call.resolve()
        } catch {
            call.reject("Failed to turn on flashlight", error)
        }
    }

    @objc func turnOff(_ call: CAPPluginCall) {
        guard let device = AVCaptureDevice.default(for: .video), device.hasTorch else {
            call.reject("Torch is not available on this device")
            return
        }
        do {
            try device.lockForConfiguration()
            device.torchMode = .off
            device.unlockForConfiguration()
            call.resolve()
        } catch {
            call.reject("Failed to turn off flashlight", error)
        }
    }

    @objc func toggle(_ call: CAPPluginCall) {
        guard let device = AVCaptureDevice.default(for: .video), device.hasTorch else {
            call.reject("Torch is not available on this device")
            return
        }
        do {
            try device.lockForConfiguration()
            device.torchMode = device.torchMode == .on ? .off : .on
            device.unlockForConfiguration()
            call.resolve()
        } catch {
            call.reject("Failed to toggle flashlight", error)
        }
    }
}
