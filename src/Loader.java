import javax.swing.*;

/**
 * Created by HerrSergio on 31.07.2016.
 */
public class Loader /*implements Runnable, ActionListener */{

    public static void main(String[] args) throws Exception {
        DataMenu dataMenu = new DataMenu();
        JFrame frame = new JFrame();
        frame.setSize(800,600);
        frame.add(dataMenu.getMainPanel());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


//        Scanner scanner = new Scanner(System.in);
//
//        String url = scanner.nextLine().trim();

//        Data data = new Data(Runtime.getRuntime().availableProcessors() * 2, url);
//
//        SwingUtilities.invokeLater(new Loader(data, url));
//
//        while (true) {
//            String command = scanner.nextLine().trim();
//            switch (command) {
//                case "pause":
//                    SwingUtilities.invokeAndWait(new Runnable() {
//                        @Override
//                        public void run() {
//                            data.setPaused(true);
//                        }
//                    });
//                    System.out.println("Paused!");
//                    break;
//                case "resume":
//                    SwingUtilities.invokeAndWait(new Runnable() {
//                        @Override
//                        public void run() {
//                            data.setPaused(false);
//                        }
//                    });
//                    System.out.println("Resumed!");
//                    break;
//                case "stop":
//                    SwingUtilities.invokeAndWait(new Runnable() {
//                        @Override
//                        public void run() {
//                            data.close();
//                            System.out.println("Stopped!");
//                            System.exit(0);
//                        }
//                    });
//                    return;
//                default:
//                    System.out.println("Unknown command!");
//            }
//        }
//
//    }

//    private Timer timer;
//    private Data data;
//    String url;
//
//    public Loader(Data data, String url) {
//        this.data = data;
//        this.url = url;
//    }
//
//    @Override
//    public void run() {
//        timer = new Timer(15000, this);
//        timer.start();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent actionEvent) {
//        State state = data.getState();
//        System.out.println("-------------------------");
//        System.out.println("Найдено ссылок: " + state.getLinksDetectedCount());
//        System.out.println("Ссылок обрабатывается: " + state.getLinksInProcessCount());
//        System.out.println("Ссылок обработано: " + (state.getLinksDetectedCount() - state.getLinksInProcessCount()));
//        System.out.println("Остановлено: " + (state.isPaused() ? "да" : "нет"));
//        System.out.println("Времени в работе: " + state.getTimeElapsed()/1000.0 + " сек." );
//        System.out.println("*************************");
//        if(state.getLinksInProcessCount() == 0) {
//            System.out.println("ЗАВЕРШЕНО!!!");
//            printUrl(url,"");
//            data.close();
//            System.exit(0);
//        }
//    }
//    public void printUrl(String url, String tab){
//        Set<String> urls = data.getUrlMap().get(url);
//        data.getUrlMap().remove(url);
//        tab = tab + "   ";
//        for (String url1:urls){
//            System.out.println(tab + url1);
//            if (data.getUrlMap().containsKey(url1))
//                if (url1 != null)
//                    printUrl(url1,tab);
//        }
//
    }
}
