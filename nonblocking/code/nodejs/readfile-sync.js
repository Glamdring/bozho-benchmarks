var http = require("http");
var url = require('url');
var fs = require('fs');

var server = http.createServer(function(request, response){
    var path = url.parse(request.url).pathname;

    switch(path){
        case '/':
            response.writeHead(200, {'Content-Type': 'text/html'});
            response.write('hello world');
            response.end();
            break;
        case '/read':
            var content = fs.readFileSync(__dirname + "/largefile");
            response.writeHead(200, {"Content-Type": "text/html"});
            response.write(content);
            response.end();
            break;
        default:
            response.writeHead(404);
            response.write("oops this page doesn't exist - 404");
            response.end();
            break;
    }
}).listen(9966);
