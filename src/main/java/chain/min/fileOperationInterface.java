package chain.min;

public interface fileOperationInterface {
    // 上传文件到IPFS
    String uploadFile(String ipfsAddress, int port, String filePath);
    // 从IPFS下载文件
    boolean downloadFile(String ipfsAddress, int port, String hash, String dstFile);
}
