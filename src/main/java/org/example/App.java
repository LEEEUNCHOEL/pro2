package org.example;

import org.example.Article.ArticleController;
import org.example.DB.DBConnection;
import org.example.System.SystemController;

import java.util.List;
import java.util.Map;

public class App {
    ArticleController articleController;
    SystemController systemController;


    App() {
        articleController = new ArticleController();
        systemController = new SystemController();

        DBConnection.DB_NAME = "proj1";
        DBConnection.DB_PORT = 3306;
        DBConnection.DB_USER = "root";
        DBConnection.DB_PASSWORD = "";

        Container.getDbConnection().connect();


    }

    public void run() {
        System.out.println("== 게시판 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String command = Container.getSc().nextLine().trim();
            // 커맨드에 입력한 내용을 actionCode, idx로 분류해서 필드로 저장
            Request request = new Request(command);

            if (request.getActionCode().equals("종료")) {
                systemController.exit();
                break;
            } else if (request.getActionCode().equals("등록")) {
                articleController.write();

            } else if (request.getActionCode().equals("목록")) {
                articleController.list();

            } else if (request.getActionCode().startsWith("삭제")) {
                articleController.delete(request);

            } else if (request.getActionCode().startsWith("수정")) {
                articleController.modify(request);

            }
        }
    }
}