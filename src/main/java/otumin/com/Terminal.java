package otumin.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/11
 * Time: 14:52
 * To change this template use File | Settings | File Templates.
 */
public class Terminal {

    private String inputUsersNum() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }

    public Integer determineUsersNum(){
        System.out.print("参加ユーザーの数を入力してください: ");
        int n = 0;
        try{
            n = Integer.valueOf(inputUsersNum());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return n;
    }
}
