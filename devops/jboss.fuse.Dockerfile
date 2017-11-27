# Use latest jboss/base-jdk:8 image as the base
FROM jboss/base-jdk:8
MAINTAINER Ravi Palakodeti <ravi.palakodeti@ge.com>

ENV DEPLOY_LOCAL_STORAGE=install

# ENV DEPLOY_CLOUD_STORAGE=https://your-cloud-storage-with-prepared-artifacts
ENV FUSE_VERSION 6.2.1.redhat-084
# RUN curl $DEPLOY_CLOUD_STORAGE/jboss-fuse-karaf-$FUSE_VERSION.zip > /opt/jboss/jboss-fuse-karaf.zip
COPY redhat/fuse/jboss-fuse-full-$FUSE_VERSION.zip /opt/jboss/jboss-fuse-full.zip

RUN unzip jboss-fuse-full.zip -d /opt/jboss && rm *.zip
RUN ln -s "jboss-fuse-$FUSE_VERSION" jboss-fuse

#RUN cd jboss-fuse && pwd

## We turn on the default admin user.
RUN cd jboss-fuse && pwd && sed -i 's/#admin/admin/' etc/users.properties

## We install components that we needed
#RUN bin/fuse server & \
#sleep 30 && \
#bin/client log:clear && \
#bin/client 'osgi:install -s mvn:xom/xom/1.2.5'  && \
#bin/client features:install camel-jetty  && \
#bin/client features:install camel-xmljson  && \
#sleep 10 && \
#bin/client log:display && \
#bin/client 'shutdown -f' && \
#sleep 5
#
## !Usually it is more affordable to use inheritance of Docker Containers and here will be a split point
#WORKDIR /opt/jboss/jboss-fuse
#COPY $DEPLOY_LOCAL_STORAGE/*.jar /opt/deploy/
#
## We deploy our service – we do it in different step to save time for building of Docker Image
#RUN bin/fuse server & \
#sleep 30 && \
#bin/client log:clear && \
#bin/client 'osgi:install -s file:/opt/deploy/some-service.jar'  && \
#sleep 10 && \
#bin/client log:display && \
#bin/client 'shutdown -f' && \
#sleep 5
#
## Add ports of your services
#EXPOSE 8181 8101 1099 44444 61616 1883 5672 61613 61617 8883 5671 61614
#CMD /opt/jboss/jboss-fuse/bin/fuse server