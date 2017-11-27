#!/bin/sh

# Load the version from the VERSION file
for line in $(cat VERSION)
do
  case $line in
    JBOSS_AMQ=*)  eval $line ;; # beware! eval!
    JBOSS_AMQ_BUILD=*)  eval $line ;; # beware! eval!
	CENTOS=*)  eval $line ;; # beware! eval!
	JDK=*)  eval $line ;; # beware! eval!
    *) ;;
   esac
done
