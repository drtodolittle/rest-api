FROM openjdk:jre-alpine

ENV KARAF_USER karaf
ENV KARAF_UID 8181
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
ENV KARAF_VERSION 4.1.1
ENV JAVA_MIN_MEM 64m
ENV JAVA_MAX_MEM 64m

RUN groupadd -r $KARAF_USER --gid=$KARAF_UID && useradd -rm -g $KARAF_USER --uid=$KARAF_UID $KARAF_USER

ADD target/drtodolittle-rest-dist-1.0-SNAPSHOT /opt/karaf

RUN sed -i 's/log4j\.rootLogger=INFO,\ out,\ osgi\:\*/log4j\.rootLogger=INFO,\ out,\ stdout,\ osgi\:\*/' /opt/karaf/etc/org.ops4j.pax.logging.cfg

# Prepare data directory
RUN mkdir -p /opt/karaf/data /opt/karaf/data/log \
    && chown -R $KARAF_USER.$KARAF_USER /opt/karaf \
    && chmod 700 /opt/karaf/data \
    && chmod a+x /opt/karaf/bin/start-rest-api.sh

EXPOSE 1099 8101 8181 44444

USER $KARAF_USER

ENTRYPOINT ["/opt/karaf/bin/start-rest-api.sh"]
