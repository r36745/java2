package rosemak.formapplication;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

/**
 * Steven Roseman
 */
public class UIHelper {

    public UIHelper() {}

    public void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        //stream.write();

    }

    public void writeToFile(Activity main, String _fileName, String ideaName, String description, String priority) {
        // MainActivity mainActivity = new MainActivity();
        File external = main.getExternalFilesDir(null);
        File file = new File(external, _fileName);

        try {
            FileOutputStream outPutStream = new FileOutputStream(file);
            outPutStream.write(ideaName.getBytes());
            outPutStream.write(description.getBytes());
            outPutStream.write(priority.getBytes());

            outPutStream.close();
        } catch (FileNotFoundException e) {
            // Log.e(TAG, "File Not Found", e);
        } catch (IOException e) {
            // Log.e(TAG, "IOException= ", e);
        }
    }

    public String readFromFile(Activity main, String _fileName) {
        // MainActivity mainActivity = new MainActivity();
        File external = main.getExternalFilesDir(null);
        File file = new File(external, _fileName);
        if (!file.exists()) {
            return null;
        }

        try {
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inPutReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inPutReader);

            StringBuffer buffer = new StringBuffer();
            String bufferString = null;
            while ((bufferString = bufferedReader.readLine()) !=null ){
                buffer.append(bufferString +"\n");
            }
            bufferedReader.close();
            return buffer.toString().trim();
        } catch (FileNotFoundException e) {
            // Log.e(TAG, "Input File Not Found", e);
        } catch (IOException e) {
            //  Log.e(TAG, "Input IOException= ", e);
        }
        return null;
    }

}
