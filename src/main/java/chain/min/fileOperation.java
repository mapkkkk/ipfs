package chain.min;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multiaddr.MultiAddress;
import io.ipfs.multihash.Multihash;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class fileOperation implements fileOperationInterface {
    public IPFS ipfsHandle;

    public void initIPFS(String ipfsAddress, int port) {
        //初始化ipfs节点
        this.ipfsHandle = new IPFS(ipfsAddress, port);
        System.out.println("IPFS node initialized successfully.");
    }

    public String uploadFile(String filePath) throws IOException {
        System.out.println("Uploading file: " + filePath);
        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File(filePath));
        MerkleNode addResult = this.ipfsHandle.add(file).get(0);
        System.out.println("Upload finished");
        return addResult.hash.toString();
    }

    public void downloadFile(String hash, String dstFile) {
        System.out.println("Downloading file: " + dstFile);
        byte[] data = null;
        try {
            data = this.ipfsHandle.cat(Multihash.fromBase58(hash));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (data != null && data.length > 0) {
            File file = new File(dstFile);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                fos.write(data);
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Download finished");
    }
}
