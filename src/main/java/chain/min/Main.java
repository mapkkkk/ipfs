package chain.min;

import java.io.IOException;

public class Main {

    public static void fileTest() throws IOException {
        fileOperation fileOp = new fileOperation();
        String serverIP = "192.168.31.208";
        int serverPort = 5001;
        String uploadFilePath = "D:\\code\\ipfs\\ipfs\\test\\upload\\uploadtest.txt";
        // String downloadFileHash = "QmRZtguPjYdrDDZ6V2BrsUKfZoptoVPqbyYxzbZD5Wj2sz";
        String downloadFileHash = "QmV2MRPvrkbbHTP7EkPJfoMeZG7QCey9zE5a1EHYCEZ29Q";
        String downloadDst = "D:\\code\\ipfs\\ipfs\\test\\download\\downloadRes.txt";
        // 连接服务器
        fileOp.initIPFS(serverIP, serverPort);
        // 上传
        String uploadHashRes = fileOp.uploadFile(uploadFilePath);
        System.out.println("uploadHashRes is: " + uploadHashRes);
        // 下载
        fileOp.downloadFile(downloadFileHash, downloadDst);
    }
    public static void main(String[] args) {
        try {
            fileTest();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}