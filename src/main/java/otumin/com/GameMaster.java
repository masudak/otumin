package otumin.com;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: masuda_kenichi
 * Date: 14/05/19
 * Time: 0:37
 * To change this template use File | Settings | File Templates.
 */
public class GameMaster {

    private UserMaster um;
    private Deck deck;
    private Terminal tm;


    public GameMaster(){
        this.um = new UserMaster();
        this.deck = new Deck();
    }


    public void gameStart(){
        deck.create();
        deck.shuffle();

        int usersNum =  um.determineUsersNum();
        System.out.println("参加ユーザの数は「" + usersNum + "」人です");

        // ユーザ作成
        um.createMultipleUser(usersNum);

        // 作成した全ユーザにカード配布
        distributeCardAllUser(usersNum);

        // ここまででユーザには配り終わってる。
        // ので、その状態の山札の情報を表示する
        System.out.println("----------------");
        System.out.println("ユーザへの配布後の山札の状態");
        System.out.println("");
        List<Card> deckobj = deck.getDeck();
        for(Card c : deckobj){
            System.out.print(c.getNumber() + ",");
        }
        System.out.println("");
        System.out.println("----------------");


        // 試験的にユーザの手札を全て表示する
        for(int i = 0; i < usersNum; i++){
            System.out.println("#########################");
            um.showHandsByUserIndex(i);
        }

        // 各レーンに残りの札を配置する（ニムトではレーン1枚ずつ計4枚配置することになっている）
        Field field = new Field();
        field.receivePutCardLane1(deck.getCardByFirst());
        deck.removeCardByFirst();

        field.receivePutCardLane2(deck.getCardByFirst());
        deck.removeCardByFirst();

        field.receivePutCardLane3(deck.getCardByFirst());
        deck.removeCardByFirst();

        field.receivePutCardLane4(deck.getCardByFirst());
        deck.removeCardByFirst();

        // ここまでで全ユーザと全列にカードの配置が終わってるので、デッキの中身を表示してみる
        System.out.println("----------------");
        System.out.println("最終的なデッキの中身:");
        System.out.println("");
        for(Card c : deckobj){
            System.out.print(c.getNumber() + ",");
        }
        System.out.println("");
        System.out.println("----------------");
    }

    private void distributeCardAllUser(int usersNum){
        for(int i = 0; i < usersNum; i++){
            User user = um.getUser(i);
            for(int j = 0; j < 10; j++){ // 10枚配布
                Card c = deck.getCardByFirst();
                user.receiveCard(c);
                deck.removeCardByFirst();
            }
        }
    }



}
