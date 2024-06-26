package chain.min;

import java.io.IOException;

public interface fileOperationInterface {
    // 初始化IPFS节点
    public void initIPFS(String ipfsAddress, int port);
    // 上传文件到IPFS
    public String uploadFile(String filePath) throws IOException;
    // 下载文件
    public void downloadFile(String hash, String dstFile);
}
