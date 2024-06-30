package chain.min;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class fileOperation implements fileOperationInterface {

    public String uploadFile(String ipfsAddress, int port, String filePath) throws IOException {
        try {
            IPFS upHandle = new IPFS(ipfsAddress, port);
            // System.out.println("IPFS node initialized successfully!");
            // System.out.println("Uploading file: " + filePath);
            NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File(filePath));
            MerkleNode addResult = upHandle.add(file).getFirst();
            System.out.println("[INFO] Upload finished!" + " File Name: " + filePath + " File Hash: " + addResult.hash.toString());
            return addResult.hash.toString();
        } catch (IOException e) {
            System.out.println("[ERROR] File Upload Failed!");
            return null;
        }
    }

    public boolean downloadFile(String ipfsAddress, int port, String hash, String dstFile) {
        IPFS downHandle = new IPFS(ipfsAddress, port);
        // System.out.println("IPFS node initialized successfully!");
        // System.out.println("Downloading file: " + dstFile);
        byte[] data = null;
        try {
            data = downHandle.cat(Multihash.fromBase58(hash));
        } catch (IOException e) {
            System.out.println("[ERROR] Hash Search Failed!");
            return false;
        }
        if (data != null && data.length > 0) {
            File file = new File(dstFile);
            if (file.exists()) {
                boolean delFlag = file.delete();
                if (!delFlag) {
                    System.out.println("[ERROR] File Delete Failed!");
                    return false;
                }
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                fos.write(data);
                fos.flush();
            } catch (IOException e) {
                System.out.println("[ERROR] File Write Failed!");
                return false;
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    System.out.println("[ERROR] Download Task Close Failed!");
                    return false;
                }
            }
        }
        System.out.println("Download finished!" + " File Name: " + dstFile + " File Hash: " + hash);
        return true;
    }
}
