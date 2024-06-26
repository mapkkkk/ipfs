package chain.min;

import java.io.IOException;

public class Main {
    String serverIP = "192.168.31.208";
    int serverPort = 5001;
    String uploadFilePath = "D:\\code\\ipfs\\ipfs\\test\\upload\\uploadtest.txt";
    String downloadFileHash = "QmRZtguPjYdrDDZ6V2BrsUKfZoptoVPqbyYxzbZD5Wj2sz";
    String downloadDst = "D:\\code\\ipfs\\ipfs\\test\\download\\downloadRes.txt";

    fileOperation fileOp = new fileOperation();

    public void main() throws IOException {
        // 连接服务器
        fileOp.initIPFS(this.serverIP, this.serverPort);
        // 上传
        String uploadHashRes = fileOp.uploadFile(this.uploadFilePath);
        System.out.println("uploadHashRes is: " + uploadHashRes);
        // 下载
        fileOp.downloadFile(this.downloadFileHash, this.downloadDst);
    }
}