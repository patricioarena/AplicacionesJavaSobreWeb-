/** utils. put it in different file */
const isLocalhost = Boolean(
  window.location.hostname === "localhost" ||
    // [::1] is the IPv6 localhost address.
    window.location.hostname === "[::1]" ||
    // 127.0.0.1/8 is considered localhost for IPv4.
    window.location.hostname.match(
      /^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/
    )
);

// Check that service workers are supported
if ("serviceWorker" in navigator) {
  // Use the window load event to keep the page load performant
  window.addEventListener("load", () => {
    if (isLocalhost) {  // loading custom service worker from local relative path to serviceWorker.js from localhost
      navigator.serviceWorker.register("./sw.js");
    } else {
      navigator.serviceWorker.register("/sw.js");
    }
  });
}