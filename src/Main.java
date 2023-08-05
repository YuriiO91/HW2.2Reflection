
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Class cls = Main.class;
        SaveTo saveTo = TextConteiner.class.getAnnotation(SaveTo.class);
        String text = new TextConteiner().getText();
        Method[] methods = cls.getDeclaredMethods();
        for(Method method:methods){
            if(method.isAnnotationPresent(Saver.class)){
                try {
                    method.invoke(new Main(),text,saveTo.path());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    @Saver
    public void save(String text, String path){
        try{
            File myTextFile = new File(path);
            FileWriter fw = new FileWriter(myTextFile);
            BufferedWriter bw = new BufferedWriter(fw);
            System.out.println(text);
            bw.write(text);
            bw.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
