# 多节点IPFS搭建

------

## 开启ipv4转发

```bash
sudo nano /etc/sysctl.conf
net.ipv4.ip_forward = 1
sudo sysctl -p
# sudo免密
sudo nano /etc/sudoers
ipfs ALL=(ALL) NOPASSWD: ALL

# node0
192.168.31.51
# node1
192.168.31.169 
# node2
192.168.31.215
```

## 安装docker

```bash
# Add Docker's official GPG key:
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

# Add the repository to Apt sources:
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
# 测试成功
sudo docker run hello-world
```

## 创建网桥

```bash
# 将登陆用户加入到docker用户组中
sudo gpasswd -a $USER docker
# 更新用户组
newgrp docker
# 创建网络
docker network create --subnet=192.180.0.0/24 docker-br0
# 拉取镜像
docker pull ubuntu:22.04
# 创建node1-3
docker run -itd --net docker-br0 --ip 192.180.0.11 --name node1 ubuntu:22.04 /bin/bash
docker run -itd --net docker-br0 --ip 192.180.0.12 --name node2 ubuntu:22.04 /bin/bash
docker run -itd --net docker-br0 --ip 192.180.0.13 --name node3 ubuntu:22.04 /bin/bash
# 打开终端
docker exec -it node1 /bin/bash
docker exec -it node2 /bin/bash
docker exec -it node3 /bin/bash
# 换源
sed -i 's/security.ubuntu.com/mirrors.ustc.edu.cn/g' /etc/apt/sources.list
apt update
# 安装一些工具
apt install iputils-ping net-tools wget git curl build-essential sudo vim nano golang ssh -y
# 设置密码
passwd
# 密码这里统一为ipfs
ipfs
```

## 安装kubo

```bash
wget https://dist.ipfs.tech/kubo/v0.29.0/kubo_v0.29.0_linux-amd64.tar.gz
tar -xvzf kubo_v0.29.0_linux-amd64.tar.gz
cd kubo
./install.sh
ipfs --version
ipfs init
ipfs config --json API.HTTPHeaders.Access-Control-Allow-Methods '["PUT", "GET", "POST", "OPTIONS"]'
ipfs config --json API.HTTPHeaders.Access-Control-Allow-Origin '["*"]'
ipfs config --json API.HTTPHeaders.Access-Control-Allow-Credentials '["true"]'
ipfs config --json API.HTTPHeaders.Access-Control-Allow-Headers '["Authorization"]'
ipfs config --json API.HTTPHeaders.Access-Control-Expose-Headers '["Location"]'
```

## 配置kubo

```bash
nano /root/.ipfs/config
# 将地址里的127.0.0.1改成自身ip, 例如node1: 192.180.0.11
# 删除默认公网 bootstrap 连接节点
ipfs bootstrap rm --all
# 获取节点之间的私有密钥, 仅在主节点(node1上执行)
go install github.com/Kubuxu/go-ipfs-swarm-key-gen/ipfs-swarm-key-gen@latest
cd $GOPATH
/root/go/bin/ipfs-swarm-key-gen > ~/.ipfs/swarm.key
# 发送到其他节点
cd /root/.ipfs
cat swarm.key
# 粘贴到其他节点
nano swarm.key
# 查看本节点信息
ipfs id
# 添加ipfs连接节点
# node0
ipfs bootstrap add  /ip4/192.168.31.51/tcp/4001/ipfs/12D3KooWEz8i3MfjFLQq4CjEFAbGoCPVWaEpztpCM1tzB78wN7VW
# node1
ipfs bootstrap add  /ip4/192.168.31.169/tcp/4001/ipfs/12D3KooWRQKT1j6RGPpeyxt528kuUpxPq9KQMeHoJytMFTJSD6AC
# node2
ipfs bootstrap add  /ip4/192.168.31.215/tcp/4001/ipfs/12D3KooWEz8i3MfjFLQq4CjEFAbGoCPVWaEpztpCM1tzB78wN7VW

ipfs swarm connect /ip4/192.168.31.169/tcp/4001/ipfs/12D3KooWRQKT1j6RGPpeyxt528kuUpxPq9KQMeHoJytMFTJSD6AC

ipfs swarm connect /ip4/192.168.31.169/tcp/4001/ipfs/12D3KooWEz8i3MfjFLQq4CjEFAbGoCPVWaEpztpCM1tzB78wN7VW
# 启动IPFS
ipfs daemon
# 动IPFS，并且使他后台运行
ipfs daemon & 
# 关闭ipfs
ipfs shutdown
```

