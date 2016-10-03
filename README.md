# How to use:
git clone https://github.com/galosa/exerecise.git
cd exercise
mvn clean install
java -jar target\exercise-0.0.1-SNAPSHOT.jar

# API
when it's up, you can surf to the following urls:

## Events
Show all events statistics including thier words:
http://localhost:8080/stats/events/

Show the event statistics of a certain event type
http://localhost:8080/stats/events/<eventType>
i.e.:
http://localhost:8080/stats/events/foo

Show the event statistics of a certain event type and a certain word
http://localhost:8080/stats/events/foo/amet 

## Words
Show all words statistics (combined from all events)
http://localhost:8080/stats/words
Show a certain word statistics (combined from all events)
http://localhost:8080/stats/words/<word>
i.e.:
http://localhost:8080/stats/words/lorem
