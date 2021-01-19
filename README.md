# Registration-App
Registration App using Spring Boot Framework

## Stack
  - Spring Boot
  - Spring Boot JPA
  - Spring Boot Security
  - Spring Boot Mail
  - Postgresql
  - Lombok

## How it works
This project has two endpoints; the POST is used for signing up users, and the GET is used for confirming a *token* passed as parameter. <br />

## Maildev
In order to run this project properly, you'll need to install Maildev(SMTP Local Server) <br />
That server can be downloaded via npm, as following: <br />
 `npm i -g maildev` <br />
 **To run the server**: `maildev --hide-extensions STARTTLS`
 

## Resources
[Amigos Code](https://www.youtube.com/watch?v=QwQuro7ekvc&t=4309s): Java Tutorial - Complete User Login and Registration Backend + Email Verification
