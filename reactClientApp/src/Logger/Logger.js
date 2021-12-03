const Logger = (message, context) => {

  function getContext() {

    Error.stackTraceLimit = 3;

    const stack = new Error().stack
      .split("\n")
      .slice(2)
      .map((line) => line.replace(/\s+at\s+/, ""))

    const context = stack[1].split(" ")[0]

    return context;
  }

  let title = {
    body: `${getContext()}`,
    color: "#875AFB",
    size: "1vm"
  };

  let slice = {
    body: ':$',
    color: "#277DFF",
    size: "1vm"
  };


  let body = {
    body: message,
    size: "1vm"
  };


    if (process.env.NODE_ENV === "development" || process.env.NODE_ENV === undefined) {
      console.log(
        `%c${title.body}%c${slice.body} %c${body.body}`,
        `color: ${title.color}; font-weight: bold; font-size: ${title.size};`,
        `color: ${slice.color}; font-weight: bold; font-size: ${slice.size};`,
        `color: ${body.color}; font-size: ${body.size};`
      );
    }
 

}

export default Logger;
