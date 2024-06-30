package chain.min;

import java.io.IOException;

public interface fileOperationInterface {
    // 上传文件到IPFS
    public String uploadFile(String ipfsAddress, int port, String filePath) throws IOException;
    // 下载文件
    public void downloadFile(String ipfsAddress, int port, String hash, String dstFile);
}
