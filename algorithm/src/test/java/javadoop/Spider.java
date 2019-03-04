package javadoop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Spider {
    public static void main(String[] args) {

        try {
            URL url = new URL("http://www.shicimingju.com/chaxun/list/29694.html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String readString = "";
            boolean isContent = false;
            StringBuilder content = new StringBuilder();
            while ((readString = reader.readLine()) != null) {
                if (readString.contains("shici-content") || isContent) {
                    isContent = true;
                    content.append(readString);
                    if (readString.contains("</div>")) {
                        break;
                    }
                }
            }
            // 把br变成换行符后，再正则过滤html标签
            String content2 = content.toString().replaceAll("<br>", System.lineSeparator());
            String content3 = content2.replaceAll("<([^>]*)>|[ ]", "");
            System.out.println(content3);
            File file = new File("sici-content.txt");
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(content2);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
