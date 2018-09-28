import java.io.*;

public class Main {

    public static void main(String[] args) {
     //   replaceFirst();
    //    replaceSecond();
        extractFiles(true);
    }

    /**
     * 替换+号
     */
    private static void replaceSecond() {
        final String PATH = "E:/一次转换/";
        final String CONVERTPATH = "E:/二次转换/";
        final String FROM = "+";
        final String ADD = " ";
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
                            replaceStr = temp.substring(temp.indexOf(FROM),temp.indexOf(FROM)+1);
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


    /**
     * 替换FROM，多行内容也会自动转换为单行。append()时候没有加换行符
     */
    static void replaceFirst(){

       final String PATH = "E:/新建文件夹/";
       final String CONVERTPATH = "E:/一次转换/";
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



    private static void extractFiles(boolean isCopy){
        final String[] fileNames = new String[]{
                "FD48.txt",
                "FD58.txt",
        };
        final String srcDirPATH = "E:/提取源文件夹/";
        final String desDirPATH = "E:/提取输出文件夹/";

        for (String fileName:fileNames) {
            String srcFilePath = srcDirPATH + fileName;
            File srcFile = new File(srcFilePath);
            if (!srcFile.exists()) {
                System.out.println(",/////////" + fileName + "is not exists!///////////");
                return;
            }
            File dirFile = new File(desDirPATH);//获取文件夹路径
            if (!dirFile.exists()) {//判断文件夹是否创建，没有创建则创建新文件夹
                dirFile.mkdirs();
            }


            if (!isCopy) {
                if (srcFile.renameTo(new File(desDirPATH + srcFile.getName()))) {
                    System.out.println(srcFile.getName()+" ,File is moved successful!");
                } else {
                    System.out.println(",----------------"+srcFile.getName()+" ,is failed to move!---------------------");
                }
            } else {
                try {
                    FileInputStream fis = new FileInputStream(srcFile);
                    FileOutputStream fos = new FileOutputStream(new File(desDirPATH + srcFile.getName()));
                    byte[] b = new byte[1024];
                    int len;
                    while ((len = fis.read(b)) != -1) {
                        fos.write(b, 0, len);
                    }
                    fos.close();
                    fis.close();
                    System.out.println(srcFile.getName() + " ,is copyed successful!");
                } catch (FileNotFoundException e) {
                    System.out.println(",-------------\"+srcFile.getName()+\" ,is failed to copy!---------------------");
                    e.printStackTrace();
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
