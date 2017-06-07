FROM openjdk:jre-alpine

ENV KARAF_USER karaf
ENV KARAF_UID 8181
ENV JAVA_HOME /usr/lib/jvm/default-jvm
ENV KARAF_VERSION 4.1.1
ENV JAVA_MIN_MEM 64m
ENV JAVA_MAX_MEM 64m

RUN addgroup -g $KARAF_UID -S $KARAF_USER && adduser -S -g $KARAF_USER -u $KARAF_UID $KARAF_USER

ADD target/drtodolittle-rest-dist-1.0-SNAPSHOT /opt/karaf

# Prepare data directory
RUN mkdir -p /opt/karaf/data /opt/karaf/data/log \
    && chown -R $KARAF_USER.$KARAF_USER /opt/karaf \
    && chmod 700 /opt/karaf/data \
    && chmod a+x /opt/karaf/bin/start-rest-api.sh

EXPOSE 1099 8101 8181 44444

USER $KARAF_USER

ENTRYPOINT ["/opt/karaf/bin/start-rest-api.sh"]
