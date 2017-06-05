#!/bin/sh
if [ "x${CAMUNDA_URI}" != "x" ]; then
  echo "camunda_uri=${CAMUNDA_URI}" >> /opt/karaf/etc/drtodolittle.cfg
fi
/opt/karaf/bin/karaf daemon
