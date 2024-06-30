# IPFS Java Interface

------

## 接口概览

```java
public interface fileOperationInterface {
    // 上传文件到IPFS
    String uploadFile(String ipfsAddress, int port, String filePath) throws IOException;
    // 从IPFS下载文件
    void downloadFile(String ipfsAddress, int port, String hash, String dstFile);
}
```

## 创建fileOperation对象

```java
fileOperation fileOp = new fileOperation();
```

## 上传文件

```java
public String uploadFile(String ipfsAddress, int port, String filePath) throws IOException;
// ipfsAddress: 服务器ip, 如222.20.97.240
// port: 端口, 如5001
// filePath: 待上传的文件路径, 如: "/home/ipfs/java_cli/test/upload/uploadTest.txt"
// [返回值]: 上传的文件的CID(Hash)
// e.g: 
String serverIP = "192.168.31.51";
int serverPort0 = 5000;
String uploadFilePath = "/home/ipfs/java_cli/test/upload/uploadTest.txt";
fileOperation fileOp = new fileOperation();
String uploadHashRes = fileOp.uploadFile(serverIP, serverPort0, uploadFilePath);
System.out.println("File CID is: " + uploadHashRes);
```

## 下载文件

```java
public boolean downloadFile(String ipfsAddress, int port, String hash, String dstFile);
// ipfsAddress: 服务器ip, 如222.20.97.240
// port: 端口, 如5001
// hash: 待下载的文件CID, 如: "QmV2MRPvrkbbHTP7EkPJfoMeZG7QCey9zE5a1EHYCEZ29Q"
// dstFile: 文件下载后保存的路径, 如: "/home/ipfs/java_cli/test/download/downloadRes.txt"
// [返回值]: 是否下载成功(boolean), True则下载成功
// e.g: 
String serverIP = "192.168.31.51";
int serverPort1 = 5001;
String downloadFileHash = "QmV2MRPvrkbbHTP7EkPJfoMeZG7QCey9zE5a1EHYCEZ29Q";
String downloadDst = "/home/ipfs/java_cli/test/download/downloadRes.txt";
fileOperation fileOp = new fileOperation();
fileOp.downloadFile(serverIP, serverPort1, downloadFileHash, downloadDst);
```