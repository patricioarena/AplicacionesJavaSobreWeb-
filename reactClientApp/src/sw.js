importScripts('https://storage.googleapis.com/workbox-cdn/releases/5.1.2/workbox-sw.js')

import {registerRoute} from 'workbox-routing';
import {NetworkFirst} from 'workbox-strategies';


if (workbox) {
  console.log(`Yay! Workbox is loaded 🎉`);
} else {
  console.log(`Boo! Workbox didn't load 😬`);
}

// See https://developers.google.com/web/tools/workbox/guides/configure-workbox
workbox.setConfig({ debug: true });

self.addEventListener('install', event => event.waitUntil(self.skipWaiting()));
self.addEventListener('activate', event => event.waitUntil(self.clients.claim()));

// We need this in Webpack plugin (refer to swSrc option): https://developers.google.com/web/tools/workbox/modules/workbox-webpack-plugin#full_injectmanifest_config
workbox.precaching.precacheAndRoute(self.__WB_MANIFEST);



registerRoute(
  ({url}) => url.pathname.startsWith('/'),
  new NetworkFirst()
);



