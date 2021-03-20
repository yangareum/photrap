sudo yum -y upgrade

echo "--- install java ---"
sudo yum install java-1.8.0-openjdk
sudo yum install java-1.8.0-openjdk-devel.x86_64

echo "--- install git ---"
#sudo yum install git -y

echo "--- install docker ---"
sudo yum -y install docker

echo "--- clone source ---"
git clone git@github.com:yangareum/photrap.git

echo "--- start docker ---"
sudo service docker start

echo "--- add user ec2-user ---"
sudo usermod -aG docker ec2-user

echo "--- install docker-compose ---"
udo curl -L 'https://github.com/docker/compose/releases/download/1.25.0\
-rc2/docker-compose-`uname -s`-`uname -m` -o \
/usr/local/bin/docker-compose'

echo "--- chmod docker-compose ---"
sudo chmod +x /usr/local/bin/docker-compose

echo "--- check install docker-compose ---"
docker-compose version 1.25.0-rc2, build 661ac20e

echo "--- start docker ---"
sudo service docker start && docker-compose up -d