function start() {
  try {
    log(window.kovalinAndroid.postMessage(["foo", "bar"]));
  } catch (ex) {
    log(ex)
  }

  function log(text) {
    const div = document.createElement("div");
    div.innerText = text;
    document.body.append(div);
  }
}
