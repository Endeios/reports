# Basic reports module

A basic reports module, just api no GUI

![Basic deployment schema](docs/deployment.png)

## The reports database

The reports database stores the calculation events

| Datetime                                    | Origin of the calculation             | name of the calculation                          | calculated value |
|---------------------------------------------|---------------------------------------|--------------------------------------------------|------------------|
| 2022-10-29T10:20:52-CEST (in utc of course) | calculation module "myWeatherStation" | current degrees in my flat ("temperature")       | 19               |
| 2022-10-29T10:23:28-CEST (in utc of course) | calculation module "myWeatherStation" | current humidity in my flat ("humidity_percent") | 56               |

this way one can do calculations and visualize the list of possible things that one can observe, ask for statistics
bla bla bla



# Links
- https://stackoverflow.com/questions/41960588/liquibase-migration-on-startup-doesnt-work-using-spring-boot
- https://dbeaver.io/download/
- https://eliedhr.medium.com/evolving-your-database-in-a-spring-boot-application-with-liquibase-709aad8336c8
- https://github.com/abdalrhmanAlkraien/Liquibase-and-springboot/blob/main/src/main/resources/db/changelog/changelog-master.yml
- https://medium.com/codex/liquibase-tutorial-3ea08691a7a9
- https://docs.liquibase.com/concepts/introduction-to-liquibase.html
- https://docs.liquibase.com/tools-integrations/springboot/springboot.html
-  https://juliuskrah.com/tutorial/2017/02/26/database-migration-with-liquibase-hikaricp-hibernate-and-jpa/