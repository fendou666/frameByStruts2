package com.qst.action.upload;  
  
import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.util.ArrayList;  
import java.util.List;  
  
import org.apache.struts2.ServletActionContext;  
  
import com.opensymphony.xwork2.ActionSupport;  
  
/** 
 * �����ļ��ϴ� 
 *  
 * ������һ��ǳ���Ҫ�����Ƿ���ҳ���ϵ�file�ı����name=xxx�ģ� 
 * ��ôAction���������Ա���Ϊxxx,xxxContentType,xxxFileName 
 *  
 *  
 */  
public class BatchFileUploadAction extends ActionSupport {  
      
    private static final long serialVersionUID = -7001482935770262132L;  
      
    private List<File> up;   
    private List<String> upContentType;  
    private List<String> upFileName;  
    private String destPath;  
  
    public String execute() throws Exception{  
          
        //System.out.println("contentType==" + this.upContentType);  
        //System.out.println("fileName==" + this.upFileName);  
          
        File basePath = new File(ServletActionContext.getServletContext().getRealPath(destPath));  
          
        if(!basePath.exists()){  
            basePath.mkdirs();  
        }  
          
        int size = up == null ? 0 : up.size();  
        for(int i = 0; i < size; i++){  
            /* 
             * �����Ҫ�����ϴ��ļ������ͣ���ô���Խ⿪��ע�� 
             * if(isAllowType(upContentType.get(i))){ 
                copy(up.get(i), new File(basePath.getCanonicalPath()+"/" + upFileName.get(i))); 
            }else{ 
                upFileName.remove(i); 
            }*/  
            copy(up.get(i), new File(basePath.getCanonicalPath()+"/" + upFileName.get(i)));  
        }  
        return SUCCESS;  
    }  
  
      
    public void copy(File srcFile, File destFile) throws IOException{  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
          
        try{  
            bis = new BufferedInputStream(new FileInputStream(srcFile));  
            bos = new BufferedOutputStream(new FileOutputStream(destFile));  
            byte[] buf = new byte[8192];  
              
            for(int count = -1; (count = bis.read(buf))!= -1; ){  
                bos.write(buf, 0, count);  
            }  
            bos.flush();  
        }catch(IOException ie){  
            throw ie;  
        }finally{  
            if(bis != null){  
                bis.close();  
            }  
            if(bos != null){  
                bos.close();  
            }  
        }  
    }  
  
  
    public List<File> getUp() {  
        return up;  
    }  
  
  
    public void setUp(List<File> up) {  
        this.up = up;  
    }  
  
  
    public List<String> getUpContentType() {  
        return upContentType;  
    }  
  
  
    public void setUpContentType(List<String> upContentType) {  
        this.upContentType = upContentType;  
    }  
  
  
    public List<String> getUpFileName() {  
        return upFileName;  
    }  
  
  
    public void setUpFileName(List<String> upFileName) {  
        this.upFileName = upFileName;  
    }  
  
  
    public String getDestPath() {  
        return destPath;  
    }  
  
    public void setDestPath(String destPath) {  
        this.destPath = destPath;  
    }  
  
    public static boolean isAllowType(String type){  
        List<String> types = new ArrayList<String>();  
        types.add("image/pjpeg");  
        types.add("text/plain");  
        types.add("image/gif");  
        types.add("image/x-png");  
          
        if(types.contains(type)){  
            return true;  
        }else{  
            return false;  
        }  
    }  
}