#!/bin/sh
if [ "x${CAMUNDA_URI}" != "x" ]; then
  echo "camunda-uri=${CAMUNDA_URI}" >> /opt/karaf/etc/drtodolittle.cfg
fi
/opt/karaf/bin/karaf daemon
