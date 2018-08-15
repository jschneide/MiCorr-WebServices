FROM tomcat:8.5.32-jre8

RUN ["apt-get", "update"]
RUN ["apt-get", "install", "-y", "vim"]
RUN mkdir /usr/local/micorr

COPY ./MiCorr-WebServices.war /usr/local/tomcat/webapps/MiCorr-WebServices.war
COPY ./tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY ./context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml
COPY ./config.yaml /usr/local/micorr/config.yaml
