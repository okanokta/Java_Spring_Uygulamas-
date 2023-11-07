# Java_Spring_Uygulamas-
Java Spring Boot ile bir RestApi Projesi
## Proje için Gerekli Teknolojiler
## 1. Spring-Boot projesi için Temel Dosyalar
Bunun İçin [Spring.io](https://start.spring.io/) Adresine gidip Asagıdaki gibi eklentileri eklememiz ve Generate gerekiyor.
![İnitializr](https://github.com/okanokta/Java_Spring_Uygulamas-/assets/100216485/dcfe7bc6-767a-4a17-878e-3ce5eddd93c3)
## 2. PostgreSQL Veritabanı
PostgreSQL'in kurulu olması lazım Bende Daha önceden Kurulu oldugundan sizde yoksa PostgreSQL'in adresinden ilgili işletim sisteminize göre kurulum yapabilirsiniz.
## Java Uygulamasını Ayaga Kaldırma
Spring.io da Generate ettigimiz dosyayı zip'den iligili klasöre çıkartıp Eclipse'den asagıdaki ekran goruntusunde oldugu gibi import ettikten sonra proje ayaga kalkacaktır.
![Ayağa_Kaldırma](https://github.com/okanokta/Java_Spring_Uygulamas-/assets/100216485/f4deef53-5cff-4527-8b6d-cb7ab59bf707)
## Spring Uygulamamıza Veritabanı Baglantısı
#### . VeriTabanı Bağlantısı ve Port Seçimi -main/resources/application.properties
```
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.show-sql=true
spring.datasource.url=jdbc:postgresql://localhost:5432/softwareCompany
spring.datasource.username=postgres
spring.datasource.password=12345
spring.jpa.properties.javax.persistence.validation.mode = none
server.port=8081
```


