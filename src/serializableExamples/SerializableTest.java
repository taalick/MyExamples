package serializableExamples;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by алла on 05.08.15.
 */
public class SerializableTest {
    public static boolean equalsHuman(Human human, Human newHuman) {
        boolean equal = false;
        if (human.name.equals(newHuman.name)) {
            if (human.assets.size() == newHuman.assets.size()) {
                int count = human.assets.size();
                for (int i = 0; i < human.assets.size(); i++) {
                    if (human.assets.get(i).getName().equals(newHuman.assets.get(i).getName()) && human.assets.get(i).getPrice() == newHuman.assets.get(i).getPrice()) {
                        count--;
                    }
                }
                if (count == 0) {
                    equal = true;
                }
            }
        }
        return equal;
    }

    public static void main(String[] args) {
        //you can find myFile.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        try {


            File myFile = File.createTempFile("myFile", null);
            OutputStream outputStream = new FileOutputStream(myFile);
            InputStream inputStream = new FileInputStream(myFile);

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson1 = new Human();
            somePerson1.load(inputStream);

            Human petrov = new Human("Petrov", new Asset("home"), new Asset("car"));
            petrov.save(outputStream);
            outputStream.flush();

            Human somePerson2 = new Human();
            somePerson2.load(inputStream);


            System.out.println(equalsHuman(ivanov, somePerson1));
            System.out.println(equalsHuman(petrov, somePerson2));

            inputStream.close();
            outputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter printWriter = new PrintWriter(outputStream);
            String isName = name != null ? "yesName" : "noName";
            printWriter.println(isName);
            if (name != null) {
                printWriter.println(name);
            }
            for (int i = 0; i < assets.size(); i++) {
                String isAsset = assets.get(i) != null ? "yes" : "no";
                printWriter.println(isAsset);
                if (assets.get(i) != null) {
                    printWriter.println(assets.get(i).getName());
                }
            }
            printWriter.flush();


        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String isName = reader.readLine();
            if (isName.equals("yesName")) {
                name = reader.readLine();
            }
            while (true) {
                String isAsset = reader.readLine();
                if (isAsset == null) {
                    break;
                }
                if (isAsset.equals("yes")) {
                    String asset = reader.readLine();
                    if (asset != null) {
                        assets.add(new Asset(asset));
                    }
                } else {
                    break;
                }

            }
        }
    }
}
