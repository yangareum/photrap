echo "--- install docker ---"
sudo yum -y upgrade
sudo yum -y install docker

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