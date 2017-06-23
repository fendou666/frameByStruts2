package chinasofti.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {
    // 下载时默认名称
    private String downLoadFileName;

    public String getDownLoadFileName() {
        return downLoadFileName;
    }
    
    // 用于下载的文件输入流
    public InputStream getInputStream() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File("D:\\fileupload\\" + downLoadFileName));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return fis;
    }

    @Override
    public String execute() throws Exception {
        downLoadFileName = "border_1.png";
        return super.execute();
    }
    
    
}
