# IPFS

------

## 配置服务端

```bash
# 下载, 我是arm64 linux, 如果是amd64请替换掉arm64
wget https://dist.ipfs.tech/kubo/v0.29.0/kubo_v0.29.0_linux-arm64.tar.gz
tar -xvzf kubo_v0.29.0_linux-arm64.tar.gz
cd kubo_v0.29.0_linux-arm64
# 安装
sudo ./install.sh
ipfs init
ipfs config --json API.HTTPHeaders.Access-Control-Allow-Methods '["PUT", "GET", "POST", "OPTIONS"]'
ipfs config --json API.HTTPHeaders.Access-Control-Allow-Origin '["*"]'
ipfs config --json API.HTTPHeaders.Access-Control-Allow-Credentials '["true"]'
ipfs config --json API.HTTPHeaders.Access-Control-Allow-Headers '["Authorization"]'
ipfs config --json API.HTTPHeaders.Access-Control-Expose-Headers '["Location"]'
# 设置编辑器
export EDITOR=/usr/bin/nano
ipfs config edit
# 5001从127.0.0.1变为0.0.0.0, 8080变为自身ip 192.168.31.208
# 启动服务
ipfs daemon
# web访问
http://127.0.0.1:5001/webui
# 我服务器ip是192.168.31.208
http://192.168.31.208:5001/webui
```

