import java.io.*;

public class Main {

    public static void main(String[] args) {

        final String PATH = "E:/新建文件夹/";
        final String CONVERTPATH = "E:/转换后/";
        final String FROM = "FROM";
        final String ADD = ",PbocFeeCrncyCode FROM";
        File file = new File(PATH);
        if(file.isDirectory()){
            File[] fArray = file.listFiles();
            for (File f:fArray) {
                try {
                    String replaceStr = null;
                    StringBuffer strBuffer = new StringBuffer();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                    for(String temp=null;(temp = bufferedReader.readLine())!=null;temp=null){
                        temp.toUpperCase();
                        if(temp.indexOf(FROM)!=-1){
                          replaceStr = temp.substring(temp.indexOf(FROM),temp.indexOf(FROM)+4);
                          temp = temp.replace(replaceStr,ADD);
                        }
                        strBuffer.append(temp);
                    }
                    bufferedReader.close();
                    PrintWriter printWriter = new PrintWriter(CONVERTPATH+f.getName());
                    printWriter.write(strBuffer.toString());
                    printWriter.flush();
                    printWriter.close();
                    System.out.println(f.getName()+"文件操作成功!\n");
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    return;
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    return;
                }
            }
            System.out.println("---------- 所有文件操作完毕 ----------");
        }
    }
}
