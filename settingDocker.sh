sudo yum -y upgrade

echo "--- install java ---"
sudo yum install java-11-openjdk
sudo yum install java-11-openjdk-devel

echo "--- install gradle ---"
sudo wget https://services.gradle.org/distributions/gradle-5.6.4-bin.zip
#설치 디렉토리 생성
sudo mkdir /opt/gradle
#압축 해제
sudo unzip -d /opt/gradle gradle-4.10.2-bin.zip
export PATH=$PATH:/home/gradle/gradle-5.6.4/bin

echo "--- install git ---"
sudo yum install git -y

echo "--- install docker ---"
sudo yum -y install docker

echo "--- clone source ---"
git clone git@github.com:yangareum/photrap.git

echo "--- start docker ---"
sudo service docker start

echo "--- add user ec2-user ---"
sudo usermod -aG docker ec2-user

echo "--- install docker-compose ---"
curl -s https://get.docker.com/ | sudo sh
sudo wget -O /usr/local/bin/docker-compose https://github.com/docker/compose/releases/download/1.23.2/docker-compose-Linux-x86_64

echo "--- chmod docker-compose ---"
sudo chmod +x /usr/local/bin/docker-compose

echo "--- check install docker-compose ---"
docker-compose version 1.23.3, build 661ac20e

wget https://services.gradle.org/distributions/gradle-6.3-bin.zip
sudo mkdir /opt/gradle
sudo unzip -d /opt/gradle gradle-6.3-bin.zip
export PATH=$PATH:/opt/gradle/gradle-6.3/bin

echo "--- src root ---"
gradle wrap

echo "--- build ---"
./gradlew bootWar


echo "--- start docker ---"
sudo service docker start && docker-compose up -d