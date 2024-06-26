package chain.min;

import java.io.IOException;

public class Main {
    String serverIP = "192.168.31.208";
    int serverPort = 5001;
    String uploadFilePath = "D:/upload";
    String downloadFileHash = "Qmf412jQZiuVUtdgnB36FXFX7xg5V6KEbSJ4dpQuhkLyfD";
    String downloadDst = "D:/download";

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