zerver
======

Application with a custom HttpServer that allows multiples access at the same time (concurrency) and has 3 calls listed below:

### Login

Request: GET /<userid>/login
Response: <sessionkey>
<userid> : 31 bit unsigned integer number
<sessionkey> : A string representing session (valid for 10 minutes).
Example: http://localhost:8081/4711/login --> UICSNDK

### Register Score

Request: POST /<levelid>/score?sessionkey=<sessionkey>
Request body: <score>
Response: (nothing)
<levelid> : 31 bit unsigned integer number
<sessionkey> : A session key string retrieved from the login function.
<score> : 31 bit unsigned integer number
Example: POST http://localhost:8081/2/score?sessionkey=UICSNDK (with the post body: 1500)

### List Scores

Request: GET /<levelid>/highscorelist
Response: CSV of <userid>=<score>
<levelid> : 31 bit unsigned integer number
<score> : 31 bit unsigned integer number
<userid> : 31 bit unsigned integer number
Example: http://localhost:8081/2/highscorelist -> 4711=1500,131=1220