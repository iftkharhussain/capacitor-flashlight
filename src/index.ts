import { registerPlugin } from '@capacitor/core';

import type { FlashlightPlugin } from './definitions';

const Flashlight = registerPlugin<FlashlightPlugin>('Flashlight', {
  web: () => import('./web').then((m) => new m.FlashlightWeb()),
});

export * from './definitions';
export { Flashlight };
