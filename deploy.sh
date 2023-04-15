JAVA_HOME=/home/perdiatmaja/.jdks/corretto-17.0.6
export JAVA_HOME
PATH=$JAVA_HOME/bin:$PATH
export PATH

mvn clean
mvn install
docker-compose up -d --build