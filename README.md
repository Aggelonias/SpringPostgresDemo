# SpringPostgresDemo

Demo applicatoin using SpringBoot and Postgresql as its database.
For this demo I use Omdb Web Api to call it and store movie information inside the database.

Available calls:
* __-GET-__ localhost:8080/all
* __-POST-__ localhost:8080/add   (_With request body_)
* __-GET-__ localhost:8080/search/name   (_With request body_)
* __-GET-__ localhost:8080/search/year   (_With request body_)
* __-GET-__ localhost:8080/movie/{id}
