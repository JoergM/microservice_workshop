## Materials for MicroService Workshop

* Booster 2014 in Bergen, Norway
* GOTO Chicago 2014
* GOTO Berlin 2015

Docker was used to run RabbitMQ and the various services (Uganda class)

* Boot2Docker used
* rabbitmq:management image used
	* docker pull rabbitmq:management
	* docker run -d -p 5672:5672 -p 15672:15672 --name="rabbitmq" rabbitmq:management
	* for restarting: docker start rabbitmq
	* for console dumping: docker logs rabbitmq
