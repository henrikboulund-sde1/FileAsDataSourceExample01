package dk.easv.dal;

import java.io.*;
import java.util.Scanner;

public class FileData
{
    private File loadedFile = null;

    public void setLoadedFile(File file)
    {
        loadedFile = file;
    }

    public boolean fileExists(String filePath)
    {
        File file = new File(filePath);
        return file.exists();
    }

    public String getFilePath()
    {
        return loadedFile.getAbsolutePath();
    }

    public String getFilePath(String filePath)
    {
        File file = new File(filePath);
        return file.getAbsolutePath();
    }

    public boolean createNewFile(String filePath)
    {
        File file = new File(filePath);
        try {
            file.createNewFile();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean writeStringToFile(String filePath, String content)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true)))
        {
            bw.write(content);
            bw.newLine();
            bw.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFile(String filePath)
    {
        File file = new File(filePath);
        return file.delete();
    }

    public String readFileContent(String filePath)
    {
        try
        {
            String data = "";
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine())
            {
                data =  data + myReader.nextLine();
            }
            myReader.close();
            return data;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public String getFileExtension(String filePath)
    {
        File file = new File(filePath);
        int dotIndex = file.getName().lastIndexOf('.');

        return (dotIndex > 0) ? file.getName().substring(dotIndex + 1) : "";
    }
}
