package chain.min;

import java.io.IOException;

public class Main {

    public static void fileTest() throws IOException {
        String serverIP = "192.168.31.51";
        int serverPort0 = 5000;
        int serverPort1 = 5001;
        // int serverPort2 = 5002;

        // 创建fileOperation对象
        fileOperation fileOp = new fileOperation();
        // 上传文件路径
        String uploadFilePath = "/home/ipfs/java_cli/test/upload/uploadTest.txt";
        // 下载文件哈希值和目标路径
        String downloadFileHash = "QmV2MRPvrkbbHTP7EkPJfoMeZG7QCey9zE5a1EHYCEZ29Q";
        String downloadDst = "/home/ipfs/java_cli/test/download/downloadRes.txt";

        // 向node0上传
        String uploadHashRes = fileOp.uploadFile(serverIP, serverPort0, uploadFilePath);
        System.out.println("File CID is: " + uploadHashRes);
        // 从node1下载
        fileOp.downloadFile(serverIP, serverPort1, downloadFileHash, downloadDst);
    }

    public static void main(String[] args) {
        try {
            fileTest();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}