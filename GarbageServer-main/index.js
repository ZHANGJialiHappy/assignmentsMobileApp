const http = require("http");
const url = require("url");

const port = process.env.PORT || 3000;
const items = [];

const server = http.createServer((req, res) => {
  const parsedUrl = url.parse(req.url, true);
  const { op, what, whereC } = parsedUrl.query;

  if (op === "insert") {
    if (!items.find((item) => item.what === what)) {
      items.push({ what, whereC });
      res.writeHead(200, { "Content-Type": "text/plain" });
      res.end("Item added to the database.");
    } else {
      res.writeHead(200, { "Content-Type": "text/plain" });
      res.end(`Item '${what}' already exists in the database.`);
    }
  } else if (op === "remove") {
    const indexToRemove = items.findIndex((item) => item.what === what);
    if (indexToRemove !== -1) {
      items.splice(indexToRemove, 1);
      res.writeHead(200, { "Content-Type": "text/plain" });
      res.end("Item removed from the database.");
    } else {
      res.writeHead(404, { "Content-Type": "text/plain" });
      res.end("Item not found in the database.");
    }
  } else if (req.method === "GET" && parsedUrl.pathname === "/") {
    res.writeHead(200, { "Content-Type": "application/json" });
    res.end(JSON.stringify(items));
  } else {
    res.writeHead(404, { "Content-Type": "text/plain" });
    res.end("Endpoint not found");
  }
});

server.listen(port, () => {
  console.log(`Server running on port ${port}`);
});
