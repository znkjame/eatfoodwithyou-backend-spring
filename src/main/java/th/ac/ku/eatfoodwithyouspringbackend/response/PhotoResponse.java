package th.ac.ku.eatfoodwithyouspringbackend.response;

public class PhotoResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public PhotoResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
}
