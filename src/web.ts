import { WebPlugin } from '@capacitor/core';

import type { FlashlightPlugin } from './definitions';

export class FlashlightWeb extends WebPlugin implements FlashlightPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async turnOn(): Promise<void> {
    console.log('Flashlight turned on (web simulation)');
  }

  async turnOff(): Promise<void> {
    console.log('Flashlight turned off (web simulation)');
  }

  async toggle(): Promise<void> {
    console.log('Flashlight toggled (web simulation)');
  }
}

export const Flashlight = new FlashlightWeb();
