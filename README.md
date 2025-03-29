A Capacitor plugin for controlling the device's flashlight - works on Android and iOS

## Installation

```bash
npm install @code_ai_labs/capacitor-flashlight
npx cap sync
```

## Usage

```typescript
import { Flashlight } from '@code_ai_labs/capacitor-flashlight';

// Turn on the flashlight
const turnOnFlashlight = async () => {
  try {
    await Flashlight.turnOn();
    console.log('Flashlight turned on successfully');
  } catch (error) {
    console.error('Error turning on flashlight:', error);
  }
};

// Turn off the flashlight
const turnOffFlashlight = async () => {
  try {
    await Flashlight.turnOff();
    console.log('Flashlight turned off successfully');
  } catch (error) {
    console.error('Error turning off flashlight:', error);
  }
};

// Toggle the flashlight state
const toggleFlashlight = async () => {
  try {
    await Flashlight.toggle();
    console.log('Flashlight toggled successfully');
  } catch (error) {
    console.error('Error toggling flashlight:', error);
  }
};
```

## Required Permissions

### Android
Add these to your AndroidManifest.xml:

```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-feature android:name="android.hardware.camera" />
<uses-feature android:name="android.hardware.camera.flash" />
```

### iOS
Add this to your Info.plist:

```xml
<key>NSCameraUsageDescription</key>
<string>We need access to the camera to control the flashlight</string>
```

## API

<docgen-index>

* [`turnOn()`](#turnon)
* [`turnOff()`](#turnoff)
* [`toggle()`](#toggle)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### turnOn()

```typescript
turnOn() => Promise<void>
```

Turn on the device's flashlight.

**Since:** 1.0.0

--------------------


### turnOff()

```typescript
turnOff() => Promise<void>
```

Turn off the device's flashlight.

**Since:** 1.0.0

--------------------


### toggle()

```typescript
toggle() => Promise<void>
```

Toggle the device's flashlight on/off.

**Since:** 1.0.0

--------------------

</docgen-api>

## License
MIT
