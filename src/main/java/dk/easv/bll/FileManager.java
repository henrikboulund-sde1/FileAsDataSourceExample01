package dk.easv.bll;

import dk.easv.dal.FileData;

import java.io.File;

public class FileManager
{
    FileData data = new FileData();

    public void setLoadedFile(File file)
    {
        data.setLoadedFile(file);
    }

    public boolean  fileExists(String filePath)
    {
        return data.fileExists(filePath);
    }

    public String getFilePath()
    {
        return data.getFilePath();
    }

    public String getFilePath(String filePath)
    {
        return data.getFilePath(filePath);
    }

    public boolean createNewFile(String filePath)
    {
        return data.createNewFile(filePath);
    }

    public boolean writeStringToFile(String filePath, String content)
    {
        return data.writeStringToFile(filePath, content);
    }

    public boolean deleteFile(String filePath)
    {
        return data.deleteFile(filePath);
    }

    public String readFileContent(String filePath)
    {
        return data.readFileContent(filePath);
    }

    public String getFileExtension(String filePath)
    {
        return data.getFileExtension(filePath);
    }

}
