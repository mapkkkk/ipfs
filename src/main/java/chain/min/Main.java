package chain.min;

import java.io.IOException;

public class Main {

    public static void fileTest() throws IOException {
        String serverIP = "101.200.162.128";
        int serverPort0 = 5000;
        int serverPort1 = 5001;
        // int serverPort2 = 5002;

        // 创建fileOperation对象
        fileOperation fileOp = new fileOperation();
        // 上传文件路径
        String uploadFilePath = "D:\\code\\ipfs\\ipfs\\test\\upload\\uploadTest.txt";
        // 下载文件哈希值和目标路径
        String downloadFileHash = "QmV2MRPvrkbbHTP7EkPJfoMeZG7QCey9zE5a1EHYCEZ29Q";
        String downloadDst = "D:\\code\\ipfs\\ipfs\\test\\download\\downloadRes.txt";

        // 向node0上传
        String uploadHashRes = fileOp.uploadFile(serverIP, serverPort0, uploadFilePath);
        System.out.println("Upload File CID is: " + uploadHashRes);
        // 从node1下载
        boolean ifDownload = fileOp.downloadFile(serverIP, serverPort1, downloadFileHash, downloadDst);
        System.out.println("Download Result: " + ifDownload);
    }

    public static void main(String[] args) {
        try {
            fileTest();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}