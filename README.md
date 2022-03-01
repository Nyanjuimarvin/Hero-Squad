# HERO SQUAD
## Description
THis is an application that implements the use of Postgresql with Java Database Connectivity(JDBC) and sessions in Spark for data persistence
## Technologies used
- Postgresql
- SparkJava
- Handlebars
- Intelli j
- Gradle
- Junit


## Behavior Driven Development
This application implements Create Read Update Delete ( CRUD ) operations in its entirety which rely on the basic database 
 
| Behavior                      | Input                                                                            | Expected Output                                                                                   |
|-------------------------------|----------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------|
| Add squad(s)                  | A squad name,preferred squad size and squad cause                                | Squads are added to the squad table using INSERT and are retrieved using SELECT                   |
| Add Hero(es)                  | Hero details such as name,age and a squadId to place the hero in a certain squad | Details are added in the hero column using INSERT and are retrieved by using the SELECT operation |   
| Update Hero                   | New details that replace previous details                                        | Hero details are updated in the respective columns using UPDATE and retrieved using SELECT        |
| Update Squad                  | New Squad details to replace previous                                            | Squad details are updated using UPDATE and retrieved using SELECT                                 |
| Delete squad and heroes in it | Delete button is clicked                                                         | The specific squad is deleted with the respective Heroes it houses using DELETE                   |
| Delete a hero                 | Delete Button for a hero is clicked                                              | The specific Hero is deleted and removed from the hero table using DELETE                         |

## Test Driven Development
Each database operation method has been tested using Junit to make sure the correct operations take place without errors.

## Setup
### Prerequisites
- Postgresql( 14.2 )
- JDBC
- Gradle

### Install

Clone the repository using the following command:  
```
git clone https://github.com/Nyanjuimarvin/Hero-Squad.git
```

### Run
Open the project in Intellij Idea and refresh gradle to download dependencies.  
After downloading dependencies, run
```
gradle build
```  
and thereafter
```
gradle run
```
to ignite a Spark server.The project can now be viewed at
```
localhost:4567
```

## Live Link

## Contact
For any issues, additional requests or compliments, reach out to me using:
* E-mail - marnyanjui@gmail.com



## License and Copyright

Copyright 2022 Marvin Nyanjui

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.