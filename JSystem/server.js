var http = require("http");
const Eris = require("eris");
var bot = new Eris(
  "NzIwMDI0MDgzMzYxOTU1OTcw.Xt_-BQ.DR3Tc8kl_1ZYQr8fgPs-MoeO_Gc"
);
bot.on("ready", () => {
  console.log("System Up");
});
bot.on("messageCreate", msg => {
  if (msg.content === "!yeet") {
    bot.createMessage(msg.channel.id, "YEETing");
  }
});
bot.connect();
//create a new server object:
http
  .createServer(function(req, res) {
    res.write("Server is ok :D"); //write a response to the client
    res.end(); //end the response
  })
  .listen(8080); //the server object listens on port 8080
