# this is an image hosted by redhat that can be used temporarily to get something started fast
# FROM registry.access.redhat.com/jboss-amq-6/amq62-openshift

# AMQ needs redhat enterprise linux
#FROM registry.access.redhat.com/rhel7/rhel
FROM centos
MAINTAINER Ravi Palakodeti <ravi.palakodeti@ge.com>


ENV AMQ_PARENT /opt/app/jboss
ENV AMQ_HOME $AMQ_PARENT/jboss-a-mq


RUN yum update -y && \
    yum install -y wget unzip java-1.7.0-openjdk java-1.7.0-openjdk-devel && \
    yum clean all

COPY redhat/amq/jboss-a-mq-6.3.0.redhat-187.zip $AMQ_PARENT/
ADD redhat/amq/resources/ $AMQ_PARENT/
# ADD VERSION $AMQ_PARENT/VERSION
ADD redhat/amq/loadenv.sh $AMQ_PARENT/loadenv.sh

WORKDIR $AMQ_PARENT

# unzip the installation
RUN unzip jboss-a-mq-6.3.0.redhat-187.zip

# Install amq
RUN echo A | ./install.sh

# move setup scripts to home directory
RUN mv init.sh $AMQ_HOME/ && \
	mv setup_ssl.sh $AMQ_HOME/ && \
	mv activemq_ssl.xml $AMQ_HOME/

# Add script to start/stop instance
RUN mv $AMQ_PARENT/jboss-amq63 $AMQ_HOME/bin/ && \
	mv $AMQ_PARENT/jboss-amq63.conf $AMQ_HOME/bin/

# set user accounts and custom config
RUN mv $AMQ_PARENT/credentials-enc.properties $AMQ_HOME/etc/ && \
	mv $AMQ_PARENT/users.properties $AMQ_HOME/etc/

### Create A-MQ User - to get an encrypted password, use jasypt
RUN sed -i "s/#encryption.enabled = false/encryption.enabled = true/" $AMQ_HOME/etc/org.apache.karaf.jaas.cfg && \
	sed -i "s/#encryption.algorithm = MD5/encryption.algorithm = SHA-256/" $AMQ_HOME/etc/org.apache.karaf.jaas.cfg && \
	sed -i "s/#activemq.jmx.user/activemq.jmx.user/" $AMQ_HOME/etc/system.properties && \
	sed -i "s/#activemq.jmx.password/activemq.jmx.password/" $AMQ_HOME/etc/system.properties

#Added security - not really necessary when there is only one user, but matches expect client systems
RUN chmod 700 $AMQ_HOME/bin/jboss-amq63.conf

### Open Ports
# SSH, Karaf-ssh, Web, rmiServerPort, rmiRegistry, ActiveMQ
EXPOSE 22 8101 8181 44444 1099 61616

### Start A-MQ
# ENTRYPOINT ["./jboss-a-mq/init.sh"]