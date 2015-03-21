var http = require("http");
var url = require('url');
var fs = require('fs');

var server = http.createServer(function(request, response){
    //console.log('Connection');
    var path = url.parse(request.url).pathname;

    switch(path){
        case '/':
            response.writeHead(200, {'Content-Type': 'text/html'});
            response.write('hello world');
            response.end();
            break;
        case '/read':
            fs.readFile(__dirname + "/largefile", function(error,data){
                if (error){
                    response.writeHead(404);
                    response.write("oops problem loading this page - 404");
                }
                else{
                    response.writeHead(200, {"Content-Type": "text/html"});
                    response.write(data);
                }

                response.end();
            });
            break;
        default:
            response.writeHead(404);
            response.write("oops this page doesn't exist - 404");
            response.end();
            break;
    }
}).listen(9966);
