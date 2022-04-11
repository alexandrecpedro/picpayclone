<div align = "center">
    <h1> PicPay Clone </h1>
</div>
<br>

| | | | |
|:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:|
|<img width="1604" alt="login" src="./Android/assets/picpay-screen1.png"> | <img width="1604" alt="activities" src="./Android/assets/picpay-screen2.png">|<img width="1604" alt="contacts" src="./Android/assets/picpay-screen3.png">|<img width="1604" alt="profile" src="./Android/assets/picpay-screen4.png"> |
<br>

<p align="center">
 <a href="#target">Target</a> •
 <a href="#technologies">Technologies</a> •
 <a href="#route">Route</a>
</p>
<br>
<br>

<div id="target">
<h2> 💡 Target </h2>
Clone of PicPay application, using a robust and testable architecture, from app to servidor side. 
For the Android, using the MVVM structural pattern. 
For the servidor side, building an API RESTful with Spring Boot structure to simulate PicPay features.
</div>
<br>

<div id="technologies">
<h2> 🛠 Technologies </h2>
The following tools were used in building the project:<br><br>

|                  Type                  |                Tools                |                                 References                                  |
| :------------------------------------: | :---------------------------------: | :-------------------------------------------------------------------------: |
|       Programming Language (App)       |              KOTLIN                 |              https://kotlinlang.org/                                        | 
|                   IDE                  |          ANDROID STUDIO             |              https://developer.android.com/studio                           |
|          Injection Framework           |                KOIN                 |              https://insert-koin.io/                                        |
|                 Testing                |                JUNIT5               |              https://junit.org/junit5/                              |
|            HTTP API Library            |              RETROFIT2              |              https://square.github.io/retrofit/                             |
|          HTTP Client Library           |              OkHTTP3                |              https://square.github.io/okhttp/                               |
|           API RESTful (JSON)           |               SWAGGER2              |              https://swagger.io/                                            |
|   Programming Language (Server Side)   |                JAVA                 |              https://docs.oracle.com/javase/tutorial/                       |
|               Spring Boot              |            SPRING BOOT              |              https://start.spring.io/                                       |
|                 Database               |            POSTGRE SQL              |              https://www.postgresql.org/                                    |
|          In-memory data store          |                 REDIS               |              https://redis.io/                                    |
|                 Security               |                 JWT                 |              https://jwt.io/                                          |

<br>
<br>

<div align = 'center'>
  <h3>Frontend</h3>
  <img width =' 100px ' src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kotlin/kotlin-original.svg" />
  <img width =' 100px ' src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/androidstudio/androidstudio-original.svg" />
  <img width =' 100px ' src="https://insert-koin.io/img/koin_new_logo.png" />
  <img height =' 100px ' src="https://junit.org/junit5/assets/img/junit5-logo.png" />
  <img height=' 100px ' src="./Android/assets/Retrofit.jpeg" />
  <img height =' 100px ' src="./Android/assets/OkHttp.png" />
  <br>
  <h3>Backend | API</h3>
  <img height =' 100px ' src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" />
  <img height =' 100px ' src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" />
  <img height =' 100px ' src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg" />
  <br>
  <img height =' 100px ' src="https://static1.smartbear.co/swagger/media/assets/images/swagger_logo.svg" />
  <br>
  <img height =' 100px ' src="https://jwt.io/img/logo-asset.svg" />
  <br>
  <img height =' 100px ' src="./Android/assets/redis.png" />
</div>

<div id="route">
<h2> 🔎 Route </h2>

<ol>
    <li>Step 1 - Setting up the environment</li>
    <br>
    <li>Step 2 - Building our API RESTful</li>
    API Architecture
    <img src="./assets/api-architecture.png" alt="API Architecture">
    <br>
    Classes Diagram for Database
    <img src="./assets/classes-diagram.png" alt="Classes diagram">
    <br>
    The API is based on RESTful model and contains:
    <ul>
      <li> Spring Boot </li>
      <li> Spring Security and JWT </li>
      <li> Spring Data with PostgreSQL </li>
      <li> Cache with Redis </li>
      <li> Documentation with Swagger 2 </li>
      <li> Managing with Spring Actuator </li>
    </ul>
    <li>Step 3 - Building our Android app</li>
    The app follows MVVM pattern e shows the technologies of:
    <ul>
      <li>Retrofit2</li>
      <li>ViewModel</li>
      <li>LiveData</li>
      <li>Coroutines</li>
      <li>Koin</li>
      <li>Navigation</li>
      <li>Room</li>
    </ul>
    <br>
    <li>Step 4 - Creating our interface</li>
    <br>
    <li>Step 5 - Consuming a RestFULL API</li>
</ol>
</div>
<br>
<br>