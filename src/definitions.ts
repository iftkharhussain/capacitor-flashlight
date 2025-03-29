export interface FlashlightPlugin {
  /**
   * Turn on the device's flashlight.
   * 
   * @returns A promise that resolves when the flashlight is turned on
   * @since 1.0.0
   */
  turnOn(): Promise<void>;

  /**
   * Turn off the device's flashlight.
   * 
   * @returns A promise that resolves when the flashlight is turned off
   * @since 1.0.0
   */
  turnOff(): Promise<void>;

  /**
   * Toggle the device's flashlight on/off.
   * 
   * @returns A promise that resolves when the flashlight state is toggled
   * @since 1.0.0
   */
  toggle(): Promise<void>;
}
