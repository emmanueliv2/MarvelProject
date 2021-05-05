echo 'Inicio de ejecución'
cd marvel-library-service
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8085
echo 'Ejecución completo'