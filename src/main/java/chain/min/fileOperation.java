package chain.min;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multiaddr.MultiAddress;

import java.io.IOException;
import java.util.List;

public class fileOperation {
    private IPFS ipfsHandle;

    public void initIPFS(String ipfsAddress, int port) {
        //初始化ipfs节点
        String ipfsUrl = "http://" + ipfsAddress + ":" + port;
        //创建ipfs节点连接
        MultiAddress multiaddr = new MultiAddress(ipfsUrl);
        this.ipfsHandle = new IPFS(multiaddr);
        System.out.println("IPFS node initialized successfully.");
    }

    public String uploadFile(String filePath) throws IOException {
        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File(filePath));
        MerkleNode addResult = this.ipfsHandle.add(file).get(0);
        System.out.println("Uploading file: " + filePath);
        return addResult.hash.toString();
    }

    public void downloadFile(String hash, String dstFile) {
        System.out.println("Downloading file: " + dstFile);
    }
}
