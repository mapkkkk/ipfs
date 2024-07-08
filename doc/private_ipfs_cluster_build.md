# 私有ipfs cluster搭建

------

## 安装`docker`和`docker-compose`

- 注意，由于近期`docker`被墙，可能需要魔法

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
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin docker-compose
sudo docker run hello-world
```

## 下载`ipfs-cluster-ctl`

```bash
wget https://dist.ipfs.tech/ipfs-cluster-ctl/v1.1.1/ipfs-cluster-ctl_v1.1.1_linux-amd64.tar.gz
tar xvzf ipfs-cluster-ctl_v1.1.1_linux-amd64.tar.gz
cd ipfs-cluster-ctl
wget https://raw.githubusercontent.com/ipfs/ipfs-cluster/v1.1.1/docker-compose.yml
# 修改docker-compose.yml, 取消节点0的port的注释
# 具体内容见项目config/docker-compose.yml
nano docker-compose.yml
# 启动
sudo docker-compose up
# 现在有3个节点, 分别是0, 1, 2, ipfs端口分别是5000, 5001, 5002
```


## 暴露端口

```bash
# 本机端口转发
su root
echo 1 > /proc/sys/net/ipv4/ip_forward
iptables -t nat -A PREROUTING -p tcp --dport 5000 -j REDIRECT --to-ports 5000
iptables -t nat -A PREROUTING -p tcp --dport 5001 -j REDIRECT --to-ports 5001
iptables -t nat -A PREROUTING -p tcp --dport 5002 -j REDIRECT --to-ports 5002
```

