package chinasofti.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport {

    // 描述
    private String desc;

    private File   file1;
    private String file1FileName;
    private String file1ContentType;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public File getFile1() {
        return file1;
    }

    public void setFile1(File file1) {
        this.file1 = file1;
    }

    public String getFile1FileName() {
        return file1FileName;
    }

    public void setFile1FileName(String file1FileName) {
        this.file1FileName = file1FileName;
    }

    public String getFile1ContentType() {
        return file1ContentType;
    }

    public void setFile1ContentType(String file1ContentType) {
        this.file1ContentType = file1ContentType;
    }

    @Override
    public String execute() throws Exception {

        System.out.println(this.file1FileName + "\t" + this.file1ContentType + "\t" + this.desc);
        
        FileOutputStream fos = new FileOutputStream("D:\\fileupload\\" + this.file1FileName);
        FileInputStream fis = new FileInputStream(this.file1);

        byte[] bs = new byte[1024];
        int real = fis.read(bs);
        while (real > 0) {
            fos.write(bs, 0, real);
            real = fis.read(bs);
        }

        fos.close();
        fis.close();

        addActionMessage(" upload success !!");
        return Action.SUCCESS;
    }

}
