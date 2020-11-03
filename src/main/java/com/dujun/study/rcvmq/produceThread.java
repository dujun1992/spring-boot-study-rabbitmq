package com.dujun.study.rcvmq;

import com.dujun.study.javaapi.IOTest;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dujun
 * @version 1.0
 * @date 2020-11-3 16:47
 */
public class produceThread extends Thread{
    private TadsHksFileProducer tadsHksFileProducer;
    private String scanDir;
    private String bakDir;
    private String exchangeName;

    public produceThread(TadsHksFileProducer tadsHksFileProducer,String bakDir , String scanDir,String exchangeName) {
        this.tadsHksFileProducer = tadsHksFileProducer;
        this.bakDir=bakDir;
        this.scanDir=scanDir;
        this.exchangeName=exchangeName;
    }

    @Override
    public void run() {
        File dir = new File(scanDir);
        try{
            while(true){
                if(dir.listFiles().length > 0){
                    for(File f : dir.listFiles()){
                        //System.out.println(f.getAbsolutePath());
                        tadsHksFileProducer.send(exchangeName,coverMessage(f.getAbsolutePath()));
                        File toFile = new File(bakDir + File.separator + f.getName());
                        System.out.println(toFile.getAbsolutePath());
                        if (toFile.exists()) {
                            toFile.delete();
                        }
                        f.renameTo(toFile);
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public  Message coverMessage(String filePath){
        byte[] body = IOTest.fileToByteArray(filePath);
        Map<String, Object> headers = new HashMap();
        MessageProperties mp = new MessageProperties();
        mp.setHeader("filename", getFileName(filePath));
        return new Message(body,mp);
    }

    public  String getFileName(String filePath){
        String[] arr = filePath.split("\\\\");
        System.out.println(arr[arr.length-1]);
        return arr[arr.length-1];
    }
}
