package by.dmitrui98.service.dao.implementation;

import by.dmitrui98.dao.ImageDao;
import by.dmitrui98.entity.Image;
import by.dmitrui98.service.dao.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Администратор on 09.05.2017.
 */
@Service
public class ImageServiceImpl implements ImageService {
    private static final String dir = "/server/file_storage/images/";
    private static final String defaultImageName = "default.jpg";
    private static final String serverDir = "/images/";


    @Autowired
    private ImageDao imageDao;

    public Image save(byte[] bytes) {

        Image image = null;
        if (bytes.length != 0) {
            File folder = createFolder(dir);

            SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
            String imageName = format.format(new Date()) + ".jpg";

            try {
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(folder, imageName)));
                stream.write(bytes);
                stream.close();

                image = imageDao.save(serverDir + imageName);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return image;
    }

    @Override
    public Image getDefaultImage() {
        return new Image(serverDir + defaultImageName);
    }

    private File createFolder(String directory) {
        File folder = new File(directory);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }

    @Override
    public List<Image> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Image getById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(Image entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Long aLong) {
        throw new UnsupportedOperationException();
    }
}
